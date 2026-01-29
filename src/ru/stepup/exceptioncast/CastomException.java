package ru.stepup.exceptioncast;

public class CastomException extends Exception {
    public CastomException() {
        super();
    }

    public CastomException(String message) {
        super(message);
    }

    public CastomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CastomException(Throwable cause) {
        super(cause);
    }

    protected CastomException(String message, Throwable cause,
                              boolean enableSuppression,
                              boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
