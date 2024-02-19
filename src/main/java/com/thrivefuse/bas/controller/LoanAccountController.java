package com.thrivefuse.bas.controller;

import com.thrivefuse.bas.dto.LoanInfoDto;
import com.thrivefuse.bas.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loan")
public class LoanAccountController {

    @Autowired
    LoanService loanService;
    @PostMapping("/new")
    public ResponseEntity<?> createNewLoan(@RequestBody LoanInfoDto loanInfoDto){
        return  loanService.processNewLoan(loanInfoDto);
    }
}
