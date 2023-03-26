package com.codeplayground.FundsTransfer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

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
import com.codeplayground.FundsTransfer.repository.AccountRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping(path = "/", produces = MediaType.TEXT_HTML_VALUE)
public class AccountController {
    
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    AccountRepository accRepo;

    @GetMapping
    public String listAccounts(Model model) {
        logger.info(">>>> Request for landing page");
        List<Account> accounts = accRepo.findAll();
        model.addAttribute("accounts", accounts);
        FundsTransfer ft = new FundsTransfer();
        model.addAttribute("ft", ft);
        return "index";
    }


    @PostMapping(path = "/transfer", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE) 
    public String postForm (@Valid @ModelAttribute("ftf") FundsTransfer ftForm, BindingResult result, Model model) {

        logger.info(">>>> Post request for funds transfer in process");
        logger.info(">>>> Transfer form received: " + ftForm);

        if (result.hasErrors()) {
            logger.error("---- Invalid binding results");
            List<Account> accounts = accRepo.findAll();
            model.addAttribute("accounts", accounts);
            return "index";
        } 
        model.addAttribute("ftf", ftForm);
        return "success";
    }
}


