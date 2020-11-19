package com.fpa.io;

import com.fpa.TextEditor;
import com.fpa.exceptions.EmptyFileException;
import com.fpa.gui.GUI;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class FileLoader implements ActionListener {

    private JTextArea fileNameBox;
    private GUI gui;
    private JFrame frame;
    private TextEditor editor;

    public FileLoader(JTextArea fileNameBox, GUI gui, JFrame frame) {
        this.fileNameBox = fileNameBox;
        this.gui = gui;
        this.frame = frame;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("Text files", "txt");
        chooser.setFileFilter(txtFilter);

        if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
            try {
                if (chooser.getSelectedFile().length() == 0){
                    throw new EmptyFileException("The file is empty!");
                }

                FileOperations fileOperations = new FileOperations(chooser.getSelectedFile());

                fileNameBox.setText(chooser.getSelectedFile().getAbsolutePath());

                ArrayList<String> data = fileOperations.readFile();
                fileOperations.setData(data);

                editor = new TextEditor();
                editor.setData(fileOperations.getData());
                gui.setEditor(editor);
                gui.setFileOperations(fileOperations);
                gui.addFunctionalityPanels(data);

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(gui.frame, ex.getMessage(), "Loading file error", JOptionPane.ERROR_MESSAGE);
            } catch (EmptyFileException ex){
                JOptionPane.showMessageDialog(gui.frame, ex.getMessage(), "Empty file error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}