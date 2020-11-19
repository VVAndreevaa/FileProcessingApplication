package com.fpa.gui;

import com.fpa.TextEditor;
import com.fpa.exceptions.InvalidLineIndexException;
import com.fpa.exceptions.InvalidWordIndexException;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FunctionJPanel extends JPanel {

    private JPanel swapLinesPane;
    private JPanel swapWordsPane;
    private TextEditor editor;
    private JTextArea textArea;
    private JFrame frame;

    public void setEditor(TextEditor editor){
        this.editor = editor;
    }
    public void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }
    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public FunctionJPanel(){
        super.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;

        createSwapLinesPane(constraints);
        createSwapWordPane(constraints);

        constraints.insets = new Insets(0,20,0,0);
        this.add(swapLinesPane, constraints);
        constraints.gridy++;
        constraints.insets = new Insets(0,20,0,0);
        this.add(swapWordsPane,constraints);
    }

    private void createSwapLinesPane(GridBagConstraints constraints){
        this.swapLinesPane = new JPanel();
        this.swapLinesPane.setLayout(new GridBagLayout());
        this.swapLinesPane.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Swap Lines", TitledBorder.LEFT, TitledBorder.TOP));
        this.swapLinesPane.setBackground(Color.CYAN);
        this.swapLinesPane.setPreferredSize(new Dimension(220,100));

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.ipadx = 0;
        constraints.ipady = 0;
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;

        JLabel firstLineLabel = new JLabel("First line number");
        this.swapLinesPane.add(firstLineLabel,constraints);

        JTextField firstLineField = new JTextField(6);
        constraints.gridx++;
        this.swapLinesPane.add(firstLineField, constraints);

        JLabel secondLineLabel = new JLabel("Second line number");
        constraints.gridx--;
        constraints.gridy++;
        this.swapLinesPane.add(secondLineLabel, constraints);

        JTextField secondLineField = new JTextField(6);
        constraints.gridx++;
        this.swapLinesPane.add(secondLineField,constraints);

        JButton swapLinesButton = new JButton("Swap Lines");
        constraints.gridx--;
        constraints.gridy++;
        this.swapLinesPane.add(swapLinesButton, constraints);

        swapLinesButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int firstLineNumber = Integer.parseInt(firstLineField.getText());
                    int secondLineNumber = Integer.parseInt(secondLineField.getText());

                    firstLineField.setText("");
                    secondLineField.setText("");

                    editor.setFirstLine(firstLineNumber);
                    editor.setSecondLine(secondLineNumber);
                    textArea.setText(editor.swapLines());
                } catch (InvalidLineIndexException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Line index error", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(frame, "Invalid or missing values for line numbers", "Swapping lines error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void createSwapWordPane(GridBagConstraints constraints){
        this.swapWordsPane = new JPanel();
        swapWordsPane.setLayout(new GridBagLayout());
        this.swapWordsPane.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Swap Words", TitledBorder.LEFT, TitledBorder.TOP));
        this.swapWordsPane.setBackground(Color.CYAN);
        this.swapWordsPane.setPreferredSize(new Dimension(220,150));

        JLabel firstLineLabel = new JLabel("First line number");
        constraints.gridy++;
        this.swapWordsPane.add(firstLineLabel, constraints);

        JTextField firstLineField = new JTextField(6);
        constraints.gridx++;
        this.swapWordsPane.add(firstLineField,constraints);

        JLabel secondLineLabel = new JLabel("Second line number");
        constraints.gridx--;
        constraints.gridy++;
        this.swapWordsPane.add(secondLineLabel, constraints);

        JTextField secondLineField = new JTextField(6);
        constraints.gridx++;
        this.swapWordsPane.add(secondLineField,constraints);

        JLabel firstWordLabel = new JLabel("First word number");
        constraints.gridx--;
        constraints.gridy++;
        this.swapWordsPane.add(firstWordLabel,constraints);

        JTextField firstWordField = new JTextField(6);
        constraints.gridx++;
        this.swapWordsPane.add(firstWordField,constraints);

        JLabel secondWordLabel = new JLabel("Second word number");
        constraints.gridx--;
        constraints.gridy++;
        this.swapWordsPane.add(secondWordLabel,constraints);

        JTextField secondWordField = new JTextField(6);
        constraints.gridx++;
        this.swapWordsPane.add(secondWordField,constraints);

        JButton swapWordsButton = new JButton("Swap Words");
        constraints.gridx--;
        constraints.gridy++;
        this.swapWordsPane.add(swapWordsButton,constraints);

        swapWordsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int firstLineNumber = Integer.parseInt(firstLineField.getText());
                    int secondLineNumber = Integer.parseInt(secondLineField.getText());
                    int firstWordNumber = Integer.parseInt(firstWordField.getText());
                    int secondWordNumber = Integer.parseInt(secondWordField.getText());

                    firstLineField.setText("");
                    secondLineField.setText("");
                    firstWordField.setText("");
                    secondWordField.setText("");

                    editor.setFirstLine(firstLineNumber);
                    editor.setSecondLine(secondLineNumber);
                    editor.setFirstLineWord(firstWordNumber);
                    editor.setSecondLineWord(secondWordNumber);

                    textArea.setText(editor.swapWords());

                } catch (InvalidWordIndexException ex){
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Word index error", JOptionPane.ERROR_MESSAGE);
                } catch (InvalidLineIndexException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Line index error", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(frame, "Invalid or missing value", "Swapping words error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
