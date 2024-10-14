package com.crms.hrms_backend.CustomException;

public class UnauthoriseException extends Exception {

    private static final long serialVersionUID = 4336138446385716141L;
    private String message;

    public UnauthoriseException() {
        super();
    }

    public UnauthoriseException(String message) {
        super(message);
        this.message = message;

    }

}
