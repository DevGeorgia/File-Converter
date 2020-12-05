package converter.app;

import converter.lib.csv.CsvToJson;
import converter.lib.csv.CsvToXml;
import converter.lib.xml.XmlToCsv;
import org.apache.commons.io.FilenameUtils;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class FileConverterGUI extends JFrame {

    private JPanel mainPanel;
    private JButton browseFileButton;
    private JLabel browseFileLabel;
    private JLabel currentFormatLabel;
    private JTextField currentFormatText;
    private JComboBox<String> expectedFormatBox;
    private JLabel expectedFormatLabel;
    private JTextArea consoleText;
    private JLabel consoleLabel;
    private JButton convertButton;
    private JTextField allowedFormatText;
    private JTextField csvSeparatorText;
    private JLabel csvSeparatorLabel;

    private String inputFilePath;
    private String currentFormat;
    private String expectedFormat;
    private String csvSeparator;

    public FileConverterGUI() {
        expectedFormatBox.addItem("csv");
        expectedFormatBox.addItem("json");
        expectedFormatBox.addItem("xml");

        csvSeparator = csvSeparatorText.getText();
        expectedFormat = expectedFormatBox.getSelectedItem().toString();
        consoleText.setLineWrap(true);

        browseFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == browseFileButton) {
                    String filename;

                    // Open browse file window
                    JFileChooser jFileChooser = new JFileChooser();
                    int rVal = jFileChooser.showOpenDialog(FileConverterGUI.this);

                    if (rVal == JFileChooser.APPROVE_OPTION) {

                        // Get extension of selected file
                        filename = jFileChooser.getSelectedFile().getName();
                        currentFormat = FilenameUtils.getExtension(filename);
                        currentFormatText.setText(currentFormat);

                        if (currentFormat.equals("csv")) {
                            csvSeparatorLabel.setVisible(true);
                            csvSeparatorText.setVisible(true);
                        } else {
                            csvSeparatorLabel.setVisible(false);
                            csvSeparatorText.setVisible(false);
                        }

                        // Get path of selected file
                        inputFilePath = jFileChooser.getSelectedFile().getAbsolutePath();
                        System.out.println("File path : " + inputFilePath);

                    }

                    if (rVal == JFileChooser.CANCEL_OPTION) {
                        consoleText.setText("No file selected");
                    }
                }
            }
        });
        expectedFormatBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    expectedFormat = e.getItem().toString();
                    System.out.println("expectedFormat " + expectedFormat);
                }
            }
        });
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == convertButton) {
                    System.out.println("convert");
                    convertFile();
                }
            }
        });
        csvSeparatorText.getDocument().addDocumentListener(new DocumentListener() {

            private void updateData() {
                csvSeparator = csvSeparatorText.getText();
                System.out.println(csvSeparator);
            }

            @Override
           public void insertUpdate(DocumentEvent e) {
                updateData();
           }

           @Override
           public void removeUpdate(DocumentEvent e) {
               updateData();
           }

           @Override
           public void changedUpdate(DocumentEvent e) {
               updateData();
           }
       });
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("FileConverterGUI");
        frame.setTitle("File Converter App");
        frame.setContentPane(new FileConverterGUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    public void convertFile() {

        System.out.println("currentFormat : " + currentFormat);
        System.out.println("expectedFormat : " + expectedFormat);
        System.out.println("inputFilePath : " + inputFilePath);
        System.out.println("csvSeparator : " + csvSeparator);

        switch(currentFormat) {
            case "csv":
                if (expectedFormat.equals("json")) {
                    String newFilePath = CsvToJson.convertCsvToJson(inputFilePath, csvSeparator);
                    consoleText.setText("New file created : " + newFilePath);

                } else if(expectedFormat.equals("xml")) {
                    String newFilePath = CsvToXml.convertCsvToXmlWithAttributes(inputFilePath, csvSeparator);
                    consoleText.setText("New file created : " + newFilePath);
                }
                else {
                    consoleText.setText("Nothing to do");
                }
                break;
            case "xml":
                if(expectedFormat.equals("csv")) {
                    consoleText.setText("Functionality not implemented yet");
                } else if(expectedFormat.equals("json")) {
                    consoleText.setText("Functionality not implemented yet");
                }
                else {
                    consoleText.setText("Nothing to do");
                }
                break;
            case "json":
                if (expectedFormat.equals("csv")) {
                    consoleText.setText("Functionality not implemented yet");
                } else if (expectedFormat.equals("xml")) {
                    consoleText.setText("Functionality not implemented yet");
                }
                else {
                    consoleText.setText("Nothing to do");
                }
                break;
            default:
                consoleText.setText("Nothing to do");
                break;
        }
    }

}
