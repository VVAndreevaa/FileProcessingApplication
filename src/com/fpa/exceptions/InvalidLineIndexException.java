package com.fpa.exceptions;

public class InvalidLineIndexException extends Exception {

    private final String message;

    public InvalidLineIndexException(final String message){
        this.message = message;
    }
    public InvalidLineIndexException(final int dataSize){
        this.message = "The line numbers must be in the range of 1 to " + dataSize;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}