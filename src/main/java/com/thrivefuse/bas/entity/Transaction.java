package com.thrivefuse.bas.entity;

import com.thrivefuse.bas.util.TransactionStatus;
import com.thrivefuse.bas.util.TransactionType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "transactions")
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column(name = "account_id", nullable = false)
    private Long accountId;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TransactionType type; // e.g., deposit, withdrawal, transfer

    @Column(name = "amount", precision = 15, scale = 2)
    private BigDecimal amount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @Column(name = "description")
    private String description;

    // Relationship with the loans table
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_id")
    private Loan loan;

    // Getters and Setters


}