package com.thrivefuse.bas.dto;


import com.thrivefuse.bas.util.TransactionType;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
@Data
public class TransactionDto {

    private BigDecimal amount;
    private LocalDateTime transactionDate;
    private String status;
    private String description;
    private String loanNumber;
    private String accountId;

    private TransactionType transactionType;
}
