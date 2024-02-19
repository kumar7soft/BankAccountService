package com.thrivefuse.bas.controller;

import com.thrivefuse.bas.dto.LoanInfoDto;
import com.thrivefuse.bas.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loan")
public class LoanInfoController {

    @Autowired
    LoanService loanService;

    @GetMapping("/info")
    public ResponseEntity<?> fetchUserLoanInfo(@RequestParam String loanNumber) {

      return  loanService.getAllTheTransactionInfoOfLoan(loanNumber);

    }
}
