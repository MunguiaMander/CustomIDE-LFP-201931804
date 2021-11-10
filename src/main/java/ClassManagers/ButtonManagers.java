/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassManagers;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

/**
 *
 * @author Marco Munguia <@markomannder>
 */
public class ButtonManagers {

    private StringSelection tmp;

    public void initializeTextAreaButtons(JTextArea codeArea, JButton undoButton, JButton redoButton, JButton copyButton, JButton pasteButton) {
        UndoManager editManager = new UndoManager();
        codeArea.getDocument().addUndoableEditListener(new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent e) {
                editManager.addEdit(e.getEdit());
            }
        });
        undoButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    editManager.undo();
                } catch (Exception a) {

                }
            }
        });
        redoButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    editManager.redo();
                } catch (Exception a) {

                }
            }
        });
        copyButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    tmp = new StringSelection(codeArea.getSelectedText());
                    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(tmp, tmp);
                } catch (Exception a) {

                }
            }
        });
        pasteButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
                if (c.isDataFlavorAvailable(DataFlavor.stringFlavor)) {

                    try {
                        String temp = (String) c.getData(DataFlavor.stringFlavor);
                        codeArea.setText(codeArea.getText() + temp);
                    } catch (Exception a) {

                    }
                }
            }
        });

    }

}
