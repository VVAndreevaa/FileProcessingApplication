package com.fpa.validation;

import com.fpa.exceptions.InvalidLineIndexException;

import java.util.ArrayList;

public class LineIndexValidator extends IndexValidator implements Validatable{

    private int lineIndex1;
    private int lineIndex2;
    private ArrayList<String> data;

    public LineIndexValidator(int lineIndex1, int lineIndex2, ArrayList<String> data) {
        this.lineIndex1 = --lineIndex1;
        this.lineIndex2 = --lineIndex2;
        this.data = data;
    }

    @Override
    public boolean validate() throws InvalidLineIndexException {
        int[] indexArr = {lineIndex1, lineIndex2};
        if (lineIndex1 == lineIndex2){
            throw new InvalidLineIndexException("The lines cannot be the same!");
        }
        else if (!super.validateLineIndex(indexArr, data)) {
            throw new InvalidLineIndexException(data.size());
        }
        return true;
    }
}
