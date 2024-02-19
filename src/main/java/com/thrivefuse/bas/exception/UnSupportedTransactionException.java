package com.thrivefuse.bas.exception;

public class UnSupportedTransactionException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public UnSupportedTransactionException(String message) {
        super(message);
    }

    // You can also add constructors for throwable or other specific needs
    public UnSupportedTransactionException(String message, Throwable cause) {
        super(message, cause);
    }
}
