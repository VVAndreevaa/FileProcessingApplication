package com.fpa.io;

import com.fpa.exceptions.InvalidSaveCommandException;

import java.io.*;
import java.util.ArrayList;

public class FileOperations {
    public ArrayList<String> data;
    public int lines;
    private File txtFile;

    public FileOperations(File txtFile) {
        this.txtFile = txtFile;
    }

    public int getLines(){
        return this.lines;
    }

    public ArrayList<String> getData(){
        return this.data;
    }
    public void setData(ArrayList<String> data){
        this.data = data;
    }

    public ArrayList<String> readFile() throws IOException {

        ArrayList<String> data = new ArrayList<>();
        lines = 0;
        String line;
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(txtFile))) {
            while ((line = bufferedReader.readLine()) != null) {
                data.add(line);
                this.lines++;
            }
        }
        return data;
    }

    public void saveFile(String[] data) throws IOException, InvalidSaveCommandException {
        FileWriter writer = new FileWriter(txtFile);
        try(BufferedWriter bufferedWriter = new BufferedWriter(writer)) {

            for (int i = 0; i < data.length; i++) {
                if (data.length != 0) {
                    bufferedWriter.write(data[i]);
                } else {
                    bufferedWriter.write(data[i]);
                }
            }
        }
    }


}
