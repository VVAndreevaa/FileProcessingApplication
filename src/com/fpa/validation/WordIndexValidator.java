package com.fpa.validation;

import com.fpa.exceptions.InvalidLineIndexException;
import com.fpa.exceptions.InvalidWordIndexException;

import java.util.ArrayList;

import static com.fpa.TextEditor.splitWords;

public class WordIndexValidator extends IndexValidator implements Validatable {

    private final int wordIndex1;
    private final int wordIndex2;
    private int lineIndex1;
    private int lineIndex2;
    private ArrayList<String> data;

    public WordIndexValidator(int lineIndex1, int lineIndex2, int wordIndex1, int wordIndex2, ArrayList<String> data) {
        this.lineIndex1 = --lineIndex1;
        this.lineIndex2 = --lineIndex2;
        this.wordIndex1 = --wordIndex1;
        this.wordIndex2 = --wordIndex2;
        this.data = data;
    }

    @Override
    public boolean validate() throws InvalidWordIndexException, InvalidLineIndexException {

        int[] indexArr = {lineIndex1, lineIndex2};

        if (!super.validateLineIndex(indexArr, data)) {
            throw new InvalidLineIndexException(data.size());
        }
        if (lineIndex1 == lineIndex2 && this.wordIndex1 == this.wordIndex2) {
            throw new InvalidWordIndexException("The word cannot be the same!");
        }

        String line1Words = data.get(lineIndex1);
        String line2Words = data.get(lineIndex2);

        String[] words1 = splitWords(line1Words);
        String[] words2 = splitWords(line2Words);


        if (!super.validateWordIndex(this.wordIndex1, words1.length)){
            throw new InvalidWordIndexException(this.wordIndex1, words1.length);
        }
        if (!super.validateWordIndex(this.wordIndex2, words2.length)){
            throw new InvalidWordIndexException(this.wordIndex2, words2.length);
        }

        return true;
    }
}
