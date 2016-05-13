package org.john.javabrains.messenger.exception;

public class DataNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 2763302761927548753L;

    public DataNotFoundException(String message) {
        super(message);
    }
}
