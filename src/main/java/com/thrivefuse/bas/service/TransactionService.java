package com.thrivefuse.bas.service;

import com.thrivefuse.bas.dto.TransactionDto;
import com.thrivefuse.bas.entity.Loan;
import com.thrivefuse.bas.entity.Transaction;
import com.thrivefuse.bas.exception.UnSupportedTransactionException;
import com.thrivefuse.bas.repository.LoanRepository;
import com.thrivefuse.bas.repository.TransactionRepository;
import com.thrivefuse.bas.util.TransactionStatus;
import com.thrivefuse.bas.util.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransactionService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public ResponseEntity<?> performTransaction(TransactionDto transactionDto){


        Loan loan =null;
        Transaction transaction =null;
        transaction =mapTransactionDtoToTransaction(transactionDto);
        try {
            if (transactionDto.getLoanNumber() != null) {
                // Load the Loan entity by ID
                loan = loanRepository.findById(Long.valueOf(transactionDto.getLoanNumber()))
                        .orElseThrow(() -> new RuntimeException("Loan not found: " + transactionDto.getLoanNumber()));
            }

            if (loan.getLoanAmount().compareTo(transactionDto.getAmount()) < 0) {

                throw new UnSupportedTransactionException("Transaction amount should not be less than the loan amount");
            }
            if (transactionDto.getTransactionType() == TransactionType.STANDARDREPAYMENTPLAN) {
                if (loan.getMonthlyInstallment().compareTo(transactionDto.getAmount()) == 0) {
                    transactionRepository.save(transaction);


                }
            } else if (transactionDto.getTransactionType() == TransactionType.PARTIALPAYMENT) {
                if (loan.getMonthlyInstallment().compareTo(transactionDto.getAmount()) < 0) {
                    BigDecimal monthlyInstallment = loan.getMonthlyInstallment();
                    BigDecimal transactionAmt = transactionDto.getAmount();
                    BigDecimal remainingInstallmentAmt = monthlyInstallment.subtract(transactionAmt);
                    transaction.setAmount(remainingInstallmentAmt);
                    transactionRepository.save(transaction);
                    loan.setMonthlyInstallment(remainingInstallmentAmt);
                    BigDecimal loanAmount = loan.getLoanAmount();
                    loanAmount = loanAmount.subtract(transactionAmt);
                    loan.setLoanAmount(loanAmount);
                    loanRepository.save(loan);
                }
            } else if (transactionDto.getTransactionType() == TransactionType.FULLANDFINALSETTLEMENT) {

                BigDecimal loanAmount = loan.getLoanAmount();
                BigDecimal transactionAmount = transactionDto.getAmount();
                if (loanAmount.compareTo(transactionAmount) == 0) {
                    loan.setStatus("Loan Repayment Completed");

                    transaction.setAmount(transactionAmount);
                    transaction.setType(TransactionType.FULLANDFINALSETTLEMENT);

                }
            }
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception occured while performing transaction . Please contact customer service");
        }
        return ResponseEntity.ok("Transaction Successfully Completed");
    }

    private Transaction mapTransactionDtoToTransaction(TransactionDto transactionDto){

        Transaction transaction = new Transaction();
        if (transactionDto.getLoanNumber() != null) {
            // Load the Loan entity by ID
            Loan loan = loanRepository.findById(Long.valueOf(transactionDto.getLoanNumber()))
                    .orElseThrow(() -> new RuntimeException("Loan not found: " + transactionDto.getLoanNumber()));
            transaction.setLoan(loan); // Set the loaded Loan on the Transaction
        }
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setAmount(transactionDto.getAmount());
        transaction.setStatus(TransactionStatus.INPROGRESS);
        transaction.setDescription(transactionDto.getDescription());
        transaction.setAccountId(Long.valueOf(transactionDto.getAccountId()));
        transaction.setTransactionDate(LocalDateTime.now());

        return transaction;


    }
}
