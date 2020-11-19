package com.fpa.gui;

import javax.swing.*;
import javax.swing.text.Element;
import java.awt.*;

public class NumberedLinesJTextArea extends JTextArea {
    private JTextArea textArea;

    public NumberedLinesJTextArea(JTextArea textArea, Insets margin){
        this.textArea = textArea;
        super.setMargin(margin);
        super.setFont(textArea.getFont().deriveFont(16f));
        super.setBackground(Color.CYAN);
        super.setCaretColor(Color.MAGENTA);
        super.setEditable(false);
    }

    private String getLineNumbers(){
        int position = textArea.getDocument().getLength();
        Element root = textArea.getDocument().getDefaultRootElement();

        StringBuilder sb = new StringBuilder();
        sb.append("1").append(System.lineSeparator());

        for (int i = 2; i < root.getElementIndex(position) + 2; i++){
            sb.append(i).append(System.lineSeparator());
        }

        return sb.toString();

    }

    public void updateLineNumbers(){
        String numbersText = getLineNumbers();
        setText(numbersText);
    }
}
