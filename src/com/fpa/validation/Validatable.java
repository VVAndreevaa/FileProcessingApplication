package com.fpa.validation;

import com.fpa.exceptions.InvalidLineIndexException;
import com.fpa.exceptions.InvalidWordIndexException;

public interface Validatable {
    public boolean validate() throws InvalidLineIndexException, InvalidWordIndexException;
}
