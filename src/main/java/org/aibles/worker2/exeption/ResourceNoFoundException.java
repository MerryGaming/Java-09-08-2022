package org.aibles.worker2.exeption;

public class ResourceNoFoundException extends RuntimeException{
    private final String message;

    public ResourceNoFoundException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
