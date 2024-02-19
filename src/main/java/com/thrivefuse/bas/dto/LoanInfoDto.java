package com.thrivefuse.bas.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class LoanInfoDto {

    private String userId;

    private String panCardNumber;

    private String loanAmount;

    private LocalDateTime loanStartDate;

    private LocalDateTime loanEndDate;

    private String intrestRate;

    private String monthlyInstallment;


}
