package com.fpa.validation;

import java.util.ArrayList;

public class IndexValidator {

    boolean validateLineIndex(int[] indexArr, ArrayList<String> data){
        for (int index : indexArr){
            if (index < 0 || index >= data.size()){
                return false;
            }
        }
        return true;
    }

    boolean validateWordIndex(int wordIndex, int numberOfWordsPerLine){
        if (wordIndex < 0 || wordIndex >= numberOfWordsPerLine){
            return false;
        }
        return true;
    }

}
