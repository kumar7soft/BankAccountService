package com.thrivefuse.bas.service;


import com.thrivefuse.bas.dto.LoanInfoDto;
import com.thrivefuse.bas.entity.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;
import com.thrivefuse.bas.repository.LoanRepository;
@Service
public class LoanService {

    @Autowired
    LoanRepository loanRepository;

    public ResponseEntity<?> processNewLoan(LoanInfoDto loanInfoDto){

        try {
           // String loanAccountNumber = generateAccountId();
            Loan loan = new Loan();
            loan.setLoanAmount(new BigDecimal(loanInfoDto.getLoanAmount()));
            loan.setAccountId(Long.valueOf(loanInfoDto.getUserId()));
            loan.setLoanEndDate(loanInfoDto.getLoanEndDate());
            loan.setLoanStartDate(loanInfoDto.getLoanStartDate());
            loan.setAmountPaid(new BigDecimal(loanInfoDto.getLoanAmount()));
            loan.setInterestRate(new BigDecimal(loanInfoDto.getIntrestRate()));
            loan.setStatus("Active");
            loan.setMonthlyInstallment(new BigDecimal(loanInfoDto.getMonthlyInstallment()));
            loanRepository.save(loan);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server error. Please contact help desk");
        }
        return ResponseEntity.ok("Loan is successfully placed");
    }

    public ResponseEntity<?> getAllTheTransactionInfoOfLoan(String loanNumber){

        Loan loan =null;
        try {
            loan = loanRepository.findById(Long.valueOf(loanNumber))
                    .orElseThrow(() -> new RuntimeException("Loan not found: " + loanNumber));
        }catch (Exception ex){
           ex.printStackTrace();
        }

       return ResponseEntity.ok(loan);

    }

    public  String generateAccountId() {
        long currentTimeMillis = System.currentTimeMillis();
        int randomNum = ThreadLocalRandom.current().nextInt(100, 1000);
        return "AC" + currentTimeMillis + "-" + randomNum;
    }
}
