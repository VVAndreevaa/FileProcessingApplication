package com.fpa.gui;

import javax.swing.*;
import java.awt.*;

public class TextContainer extends Container {

    private JTextArea textArea;
    private NumberedLinesJTextArea numberedLinesJTextArea;
    private String data;

    public void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }
    public void setData(String data) {
        this.data = data;
    }

    public void createTextContainer(){
        super.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.ipadx = 0;
        constraints.ipady = 0;
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        constraints.gridwidth = 10;
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;

        Insets margin = new Insets(3,6,3,6);
        textArea.setFont(textArea.getFont().deriveFont(16f));
        textArea.setEditable(false);
        textArea.setMargin(margin);
        textArea.setText(data);
        numberedLinesJTextArea = new NumberedLinesJTextArea(textArea,margin);

        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.getViewport().add(textArea);
        jScrollPane.setRowHeaderView(numberedLinesJTextArea);
        super.add(jScrollPane, constraints);
        numberedLinesJTextArea.updateLineNumbers();
    }
}
