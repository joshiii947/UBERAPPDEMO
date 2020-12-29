package com.uber.uberapi.exceptions;

public class NoSuchDriverException extends UberException{
    public NoSuchDriverException(String message) {
        super(message);
    }
}
