// package com.codeplayground.FundsTransfer.controller;

// import java.util.List;

// import org.springframework.http.MediaType;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.util.MultiValueMap;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.codeplayground.FundsTransfer.model.Account;
// import com.codeplayground.FundsTransfer.model.FundsTransfer;
// import com.codeplayground.FundsTransfer.repository.AccountRepository;

// @RestController
// @RequestMapping("/")
// public class AccountRestController {

//     @Autowired
//     AccountRepository accRepo;

//     @GetMapping("/api/accounts")
//     public ResponseEntity<List<Account>> findAll() {
//         List<Account> accounts = accRepo.findAll();
//         return new ResponseEntity<List<Account>>(accounts, HttpStatus.OK);
//     }
    
    // @PostMapping(path="/transfer", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    // public ResponseEntity<Boolean> postForm(@RequestBody MultiValueMap<String, String> form) {
    //     String accFromId = form.getFirst("accFrom");
    //     String accToId = form.getFirst("accTo");
    //     Float amount = Float.parseFloat(form.getFirst("amount"));
    //     String comments = form.getFirst("comments");

        
        
    // }



    
    // Obtain RSVP data from form, send to API and return json
    // @PostMapping(path="/confirm", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    // public ResponseEntity<Boolean> postForm(@RequestBody MultiValueMap<String, String> form) {
    //     String name = form.getFirst("fullName");
    //     String email = form.getFirst("email");
    //     String phone = form.getFirst("phone");
    //     String comments = form.getFirst("comments");

    //     RSVP rsvp = new RSVP(name, email, phone, comments);
    //     Boolean saved = rsvpSvc.save(rsvp);
    //     if (!saved) {
    //         return ResponseEntity.noContent().build();
    //     } else {
    //         return ResponseEntity.ok().body(saved);
    //     }
    // }
// }
