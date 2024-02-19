package com.thrivefuse.bas.controller;

import com.thrivefuse.bas.dto.TransactionDto;
import com.thrivefuse.bas.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loan")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/transaction")
    public ResponseEntity<?> performTransaction(@RequestBody TransactionDto transactionDto){
       return transactionService.performTransaction(transactionDto);
    }
}
