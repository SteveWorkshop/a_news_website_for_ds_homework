package com.example.mynews.service.ex;

public class NoSuchNewsTypeException extends ServiceException{
    public NoSuchNewsTypeException() {
        super();
    }

    public NoSuchNewsTypeException(String message) {
        super(message);
    }

    public NoSuchNewsTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchNewsTypeException(Throwable cause) {
        super(cause);
    }

    protected NoSuchNewsTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
