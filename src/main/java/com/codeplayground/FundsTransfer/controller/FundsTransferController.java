package com.codeplayground.FundsTransfer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codeplayground.FundsTransfer.model.Account;
import com.codeplayground.FundsTransfer.model.FundsTransfer;
import com.codeplayground.FundsTransfer.model.Transaction;
import com.codeplayground.FundsTransfer.service.FundsTransferService;
import com.codeplayground.FundsTransfer.service.LogAuditService;

import jakarta.validation.Valid;

@Controller
@RequestMapping(path = "/", produces = MediaType.TEXT_HTML_VALUE)
public class FundsTransferController {
    
    private static final Logger logger = LoggerFactory.getLogger(FundsTransferController.class);

    @Autowired
    FundsTransferService ftService;

    @Autowired
    LogAuditService laService;

    @GetMapping
    public String listAccounts(Model model) {
        logger.info(">>>> Request for landing page");
        List<Account> accounts = ftService.findAll();
        model.addAttribute("accounts", accounts);
        FundsTransfer ft = new FundsTransfer();
        model.addAttribute("ft", ft);
        return "index";
    }


    @PostMapping(path = "/transfer", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE) 
    public String postForm (@Valid @ModelAttribute("ft") FundsTransfer ftForm, BindingResult result, Model model) {

        logger.info(">>>> Post request for funds transfer in process");
        logger.info(">>>> Transfer form received: " + ftForm);

        if (result.hasErrors()) {
            logger.error("---- Invalid binding results");
            List<Account> accounts = ftService.findAll();
            model.addAttribute("accounts", accounts); // to return to index page with keyed in values
            return "index";
        } 
        
        if (!ftService.performValidation(ftForm, ftForm.getAmount())){  // Is this validation correct???
            String errorMessage = "Insufficient funds from withdrawal account for the transfer";
            FieldError error = new FieldError("ft", "amount", ftForm.getAmount(), false, null, null, errorMessage);
            result.addError(error);

            List<Account> accounts = ftService.findAll();
            model.addAttribute("accounts", accounts);
            model.addAttribute("ft", ftForm);
            return "index";
        } else if (!ftService.performTransaction(ftForm, ftForm.getAmount())) {  // Is this validation correct???
            List<Account> accounts = ftService.findAll();
            model.addAttribute("accounts", accounts);
            model.addAttribute("ft", ftForm);
            return "index";
        } else {
            Transaction transaction = laService.createTransaction(ftForm.getAccFromId(), ftForm.getAccToId(), ftForm.getAmount());
            // laService.logToRedis(transaction);
            model.addAttribute("transaction", transaction);
            return "success";
        }

    }
}


