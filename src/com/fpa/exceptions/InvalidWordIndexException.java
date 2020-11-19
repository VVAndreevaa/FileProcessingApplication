package com.fpa.exceptions;

public class InvalidWordIndexException extends Exception {

    private final String message;

    public InvalidWordIndexException(final String message){
        this.message = message;
    }
    public InvalidWordIndexException(int lineIndex, int lineLength){
        this.message = "The word numbers in line " + (++lineIndex) + " must be in range of 1 to " + lineLength;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
