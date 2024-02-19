package com.thrivefuse.bas.controller;

import com.thrivefuse.bas.service.LoanElgibleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user/checkElgible")
public class LoanElgibilityCheckController {

    @Autowired
    LoanElgibleService loanElgibleService;
    @GetMapping("/maxLoanAmount")
    public ResponseEntity<?> checkUserLoanAmountElgible(@RequestParam String userId){
        Double maxElgibleLoanAmt = loanElgibleService.getMaxElgibleLoanAmountOfUser(userId);
        if(maxElgibleLoanAmt ==  null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Exception");
        }
        return ResponseEntity.ok(maxElgibleLoanAmt);
    }

}
