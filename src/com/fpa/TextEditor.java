package com.fpa;

import com.fpa.exceptions.InvalidLineIndexException;
import com.fpa.exceptions.InvalidWordIndexException;
import com.fpa.validation.LineIndexValidator;
import com.fpa.validation.WordIndexValidator;

import java.util.ArrayList;

public class TextEditor {

    private ArrayList<String> data;
    private int firstLine;
    private int secondLine;
    private int firstLineWord;
    private int secondLineWord;

    public TextEditor(){
    }

    public void setData(ArrayList<String> data) {
        this.data = data;
    }

    public void setFirstLine(int firstLine) {
        this.firstLine = firstLine;
    }

    public void setSecondLine(int secondLine) {
        this.secondLine = secondLine;
    }

    public void setFirstLineWord(int firstLineWord) {
        this.firstLineWord = firstLineWord;
    }

    public void setSecondLineWord(int secondLineWord) {
        this.secondLineWord = secondLineWord;
    }

    public String swapLines() throws InvalidLineIndexException {

        LineIndexValidator validator = new LineIndexValidator(this.firstLine, this.secondLine, this.data);
        validator.validate();

        int firstLineIndex = --firstLine;
        int secondLineIndex = --secondLine;

        data.set(firstLineIndex, data.set(secondLineIndex, data.get(firstLineIndex)));

        return String.join(System.lineSeparator(), data);
    }


    public String swapWords() throws InvalidWordIndexException, InvalidLineIndexException {

        WordIndexValidator validator = new WordIndexValidator
                (this.firstLine, this.secondLine, this.firstLineWord, this.secondLineWord, this.data);
        validator.validate();

        int firstLineIndex = --firstLine;
        int secondLineIndex = --secondLine;
        int firstWordIndex = --firstLineWord;
        int secondWordIndex = --secondLineWord;

        String line1Words = this.data.get(firstLineIndex);
        String line2Words = this.data.get(secondLineIndex);

        String[] words1 = splitWords(line1Words);
        String[] words2 = splitWords(line2Words);

        if (firstLineIndex != secondLineIndex) {
            words1[firstWordIndex] += words2[secondWordIndex];
            words2[secondWordIndex] = words1[firstWordIndex].substring(0, words1[firstWordIndex].length() - words2[secondWordIndex].length());
            words1[firstWordIndex] = words1[firstWordIndex].substring(words2[secondWordIndex].length());
            this.data.set(firstLineIndex, String.join(" ", words1));
            this.data.set(secondLineIndex, String.join(" ", words2));
        }
        else{
            String temp = words1[firstWordIndex];
            words1[firstWordIndex] = words1[secondWordIndex];
            words1[secondWordIndex] = temp;
            this.data.set(firstLineIndex, String.join(" ", words1));
        }

        return String.join(System.lineSeparator(), this.data);
    }

    public static String[] splitWords(String line){
        String[] wordsArray = line.split("\\s+");
        String[] result = null;
        boolean isFoundNullElement = false;
        for(int i = 0; i < wordsArray.length; i++){
            if (wordsArray[i].isEmpty()){
                result = removeElementFromArray(wordsArray, i);
                isFoundNullElement = true;
            }
        }
        if (!isFoundNullElement){
            return wordsArray;
        }
        return result;
    }

    private static String[] removeElementFromArray(String[] arr, int index)
    {
        if (arr == null || index < 0 || index >= arr.length) {
            return arr;
        }

        String[] anotherArray = new String[arr.length - 1];

        for (int i = 0, k = 0; i < arr.length; i++) {
            if (i == index) {
                continue;
            }
            anotherArray[k++] = arr[i];
        }
        return anotherArray;
    }
}
