package com.thrivefuse.bas.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "loans")
@Data
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    @Column(name = "account_id", nullable = false)
    private Long accountId;

    @Column(name = "loan_amount", precision = 15, scale = 2, nullable = false)
    private BigDecimal loanAmount;

    @Column(name = "amount_paid", precision = 15, scale = 2, nullable = false)
    private BigDecimal amountPaid;

    @Column(name = "interest_rate", precision = 5, scale = 2, nullable = false)
    private BigDecimal interestRate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "loan_start_date")
    private LocalDateTime loanStartDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "loan_end_date")
    private LocalDateTime loanEndDate;

    @Column(name = "monthly_installment", precision = 15, scale = 2, nullable = false)
    private BigDecimal monthlyInstallment;

    @Column(name = "status", length = 50)
    private String status;

    @OneToMany(mappedBy = "loan")
    private List<Transaction> transactions;
    // Getters and setters
}
