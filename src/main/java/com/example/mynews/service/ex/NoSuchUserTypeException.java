package com.example.mynews.service.ex;

public class NoSuchUserTypeException extends ServiceException{
    public NoSuchUserTypeException() {
        super();
    }

    public NoSuchUserTypeException(String message) {
        super(message);
    }

    public NoSuchUserTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchUserTypeException(Throwable cause) {
        super(cause);
    }

    protected NoSuchUserTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
