package com.kalpana;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;

public class DiaryApp extends JFrame {
    private JTextArea diaryText;

    public DiaryApp() {
        setTitle("Personal Diary");
        setSize(1080, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        diaryText = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(diaryText);

        JButton saveButton = new JButton("Save Entry");
        saveButton.addActionListener(e -> saveEntry());

        JMenuBar menuBar = new JMenuBar();
        JMenu themeMenu = new JMenu("Themes");

        JMenuItem metalTheme = new JMenuItem("Metal");
        JMenuItem nimbusTheme = new JMenuItem("Nimbus");
        JMenuItem motifTheme = new JMenuItem("Motif");
        JMenuItem windowsTheme = new JMenuItem("Windows");

        metalTheme.addActionListener(e -> setTheme("javax.swing.plaf.metal.MetalLookAndFeel"));
        nimbusTheme.addActionListener(e -> setTheme("javax.swing.plaf.nimbus.NimbusLookAndFeel"));
        motifTheme.addActionListener(e -> setTheme("com.sun.java.swing.plaf.motif.MotifLookAndFeel"));
        windowsTheme.addActionListener(e -> setTheme("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"));

        themeMenu.add(metalTheme);
        themeMenu.add(nimbusTheme);
        themeMenu.add(motifTheme);
        themeMenu.add(windowsTheme);

        menuBar.add(themeMenu);
        setJMenuBar(menuBar);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(saveButton, BorderLayout.SOUTH);
    }

    private void saveEntry() {
        try (FileWriter writer = new FileWriter("diary.txt", true)) {
            writer.write(diaryText.getText() + "\n");
            diaryText.setText("");
            JOptionPane.showMessageDialog(this, "Entry saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving entry.");
        }
    }

    private void setTheme(String className) {
        try {
            UIManager.setLookAndFeel(className);
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to apply theme.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DiaryApp app = new DiaryApp();
            app.setVisible(true);
        });
    }
}