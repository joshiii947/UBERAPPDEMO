package com.uber.uberapi.exceptions;

public class InvalidOTPException extends UberException {

    public InvalidOTPException() {
        super("invalid OTP");
    }

}
