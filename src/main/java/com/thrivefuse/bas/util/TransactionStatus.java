package com.thrivefuse.bas.util;

public enum TransactionStatus {

    INPROGRESS("inprogress"),
    SUCCESS("success"),
    HOLD("hold"),
    FAILURE("failure");


    private final String displayName;

    TransactionStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
