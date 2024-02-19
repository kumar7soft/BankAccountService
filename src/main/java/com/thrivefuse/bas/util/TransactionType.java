package com.thrivefuse.bas.util;

public enum TransactionType {

    DEPOSIT("Deposit"),
    WITHDRAWAL("Withdrawal"),
    STANDARDREPAYMENTPLAN("StandardRepaymentPlan"),
    PREPAYMENT("Prepayment"),
    PARTIALPAYMENT("PartialPayment"),

    FULLANDFINALSETTLEMENT("FullAndFinalSettlement"),
    TRANSFER("Transfer");

    private final String displayName;

    TransactionType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
