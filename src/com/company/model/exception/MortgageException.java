package com.company.model.exception;

public class MortgageException extends RuntimeException{

    public MortgageException() {
        super("Case not handled");
    }
}
