package com.fpa.gui;

import com.fpa.TextEditor;
import com.fpa.exceptions.InvalidSaveCommandException;
import com.fpa.io.FileLoader;
import com.fpa.io.FileOperations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class GUI {
    public JFrame frame;
    private JTextArea path;
    private TextEditor editor;
    private FileOperations fileOperations;

    private JTextArea textArea;

    private TextContainer textContainer;
    private FunctionJPanel functionJPanel;

    public static boolean isAddFunctionalityPanels = false;

    public void setEditor(final TextEditor editor) {
        this.editor = editor;
    }

    public void setFileOperations(FileOperations fileOperations) {
        this.fileOperations = fileOperations;
    }

    public void showGUI(){
        frame = new JFrame("File Processing Application");
        frame.setResizable(false);
        frame.setSize(new Dimension(1000, 350));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.add(BorderLayout.SOUTH, addLoadAndSaveButtons());

        frame.pack();
        frame.setVisible(true);
    }
    public void addFunctionalityPanels(ArrayList<String> data) {
        textArea.setCaretPosition(0);
        textArea.setText(String.join(System.lineSeparator(), data));
        textContainer.setData(String.join(System.lineSeparator(), data));

        if (!isAddFunctionalityPanels){
            isAddFunctionalityPanels = true;
            textContainer.createTextContainer();
            frame.add(BorderLayout.EAST, textContainer);

            functionJPanel = new FunctionJPanel();
            functionJPanel.setEditor(editor);
            functionJPanel.setFrame(frame);
            functionJPanel.setTextArea(textArea);

            frame.add(BorderLayout.WEST, functionJPanel);

            frame.pack();
        }
        else{

        }
    }

    public Container addLoadAndSaveButtons(){

        textArea = new JTextArea(10, 50);
        textContainer = new TextContainer();
        textContainer.setTextArea(textArea);

        Container container = new Container();
        container.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        container.setSize(200, 30);

        path = new JTextArea(1,30);
        constraints.ipadx = 0;
        constraints.ipady = 12;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(0,250,25,0);
        path.setFont(path.getFont().deriveFont(15f));

        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.getViewport().add(path);
        jScrollPane.setPreferredSize(new Dimension(400,25));
        container.add(jScrollPane, constraints);

        JButton loadFileButton = new JButton("Load File");
        constraints.gridx++;
        constraints.insets = new Insets(0,0,25,0);
        loadFileButton.setPreferredSize(new Dimension(90,24));
        container.add(loadFileButton, constraints);

        loadFileButton.addActionListener(new FileLoader(path, this, frame));


        JButton saveFileButton = new JButton("Save File");
        constraints.gridx++;
        constraints.insets = new Insets(0,35,25,10);
        saveFileButton.setPreferredSize(new Dimension(150, 24));

        container.add(saveFileButton, constraints);

        saveFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if (fileOperations == null){
                        throw new InvalidSaveCommandException("Please load your file first");
                    }
                    fileOperations.saveFile(textArea.getText().split("\\n"));
                }catch (InvalidSaveCommandException ex){
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Saving error", JOptionPane.ERROR_MESSAGE);
                }catch (IOException ex){
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Saving file error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        return container;
    }
}