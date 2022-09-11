package com.example.mynews.service.ex;

public class NewsNotFoundException extends RuntimeException{
    public NewsNotFoundException() {
        super();
    }

    public NewsNotFoundException(String message) {
        super(message);
    }

    public NewsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NewsNotFoundException(Throwable cause) {
        super(cause);
    }

    protected NewsNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
