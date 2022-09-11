package com.example.mynews.service.ex;

public class NoMoreNewsException extends RuntimeException{
    public NoMoreNewsException() {
        super();
    }

    public NoMoreNewsException(String message) {
        super(message);
    }

    public NoMoreNewsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoMoreNewsException(Throwable cause) {
        super(cause);
    }

    protected NoMoreNewsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
