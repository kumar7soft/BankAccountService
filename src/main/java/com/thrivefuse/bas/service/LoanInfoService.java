package com.thrivefuse.bas.service;

import com.thrivefuse.bas.dto.LoanInfoDto;
import com.thrivefuse.bas.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanInfoService {

    @Autowired
    LoanRepository loanRepository;
    public void retriveUserLoanInfoAndTransactionInfo(String loanId){
        loanRepository.findById(Long.valueOf(loanId));
    }

}
