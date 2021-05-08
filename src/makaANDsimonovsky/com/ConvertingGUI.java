package makaANDsimonovsky.com;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConvertingGUI {
    public JPanel convertingPanel;
    private JPanel InnerMainPanel;
    private JPanel inputDataPanel;
    private JPanel outputDataPanel;
    private JLabel inputAddressLabel;
    private JLabel inputMaskLabel;
    private JTextField inputMaskField;
    private JTextField inputAddressField;
    private JPanel outputLabelsPanel;
    private JPanel outputFieldsLabel;
    private JTextField firstHostField;
    private JTextField lastHostField;
    private JTextField broadcastField;
    private JTextField hostsField;
    private JTextField maskDecimalField;
    private JTextField networkBinaryField;
    private JTextField maskBinaryField;
    private JTextField broadcastBinaryField;
    private JLabel leftFulfillLabel;
    private JLabel rightFulfillLabel;
    private JLabel topFulfillLabel;
    private JLabel bottomFulfillLabel;
    private JButton submitButton;

    public static boolean isInteger(String strNum) {
        if (strNum == null) {
            return false;
        }

        try {
            Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }

        return true;
    }

    public ConvertingGUI() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String address = "";
                address = inputAddressField.getText();
                String[] partsOfAddress = address.split("\\.");

                boolean flag = true;
                if (partsOfAddress.length != 4) flag = false;
                else if (!isInteger(partsOfAddress[0]) || !isInteger(partsOfAddress[1]) ) flag = false;
                else if (!isInteger(partsOfAddress[2]) || !isInteger(partsOfAddress[3]) ) flag = false;
                else if (Integer.parseInt(partsOfAddress[0]) < 0 || Integer.parseInt(partsOfAddress[0]) > 255) flag = false;
                else if (Integer.parseInt(partsOfAddress[1]) < 0 || Integer.parseInt(partsOfAddress[1]) > 255) flag = false;
                else if (Integer.parseInt(partsOfAddress[2]) < 0 || Integer.parseInt(partsOfAddress[2]) > 255) flag = false;
                else if (Integer.parseInt(partsOfAddress[3]) < 0 || Integer.parseInt(partsOfAddress[3]) > 255) flag = false;

                String maskText = "";
                maskText = inputMaskField.getText();
                if (!isInteger(maskText)) flag = false;
                else if (Integer.parseInt(maskText) < 1) flag = false;
                else if (Integer.parseInt(maskText) > 31) flag = false;


                if(flag) {
                    Network network = new Network(address, Integer.parseInt(maskText));
                    if(!network.isNetworkCorrect()) {
                        firstHostField.setText("");
                        lastHostField.setText("");
                        broadcastField.setText("");
                        hostsField.setText(Double.toString(0));
                        maskDecimalField.setText("");
                        networkBinaryField.setText("");
                        maskBinaryField.setText("");
                        broadcastBinaryField.setText("");
                        JFrame frame = new JFrame("WarningGUI");
                        frame.setContentPane(new WarningGUI().warningPanel);
                        frame.pack();
                        frame.setVisible(true);
                        frame.setLocationRelativeTo(null);
                    } else {
                        String doubleAsString = String.valueOf(network.numberOfAvailableHosts);
                        int indexOfDecimal = doubleAsString.indexOf(".");

                        firstHostField.setText(network.firstHostAddress);
                        lastHostField.setText(network.lastHostAddress);
                        broadcastField.setText(network.broadcastAddressDecimal);
                        hostsField.setText(doubleAsString.substring(0, indexOfDecimal));
                        maskDecimalField.setText(network.networkMaskDecimal);
                        networkBinaryField.setText(network.networkAddressBinary);
                        maskBinaryField.setText(network.networkMaskBinary);
                        broadcastBinaryField.setText(network.broadcastAddressBinary);
                    }
                } else {
                    firstHostField.setText("");
                    lastHostField.setText("");
                    broadcastField.setText("");
                    hostsField.setText(Double.toString(0));
                    maskDecimalField.setText("");
                    networkBinaryField.setText("");
                    maskBinaryField.setText("");
                    broadcastBinaryField.setText("");
                    JFrame frame = new JFrame("WarningGUI");
                    frame.setContentPane(new WarningGUI().warningPanel);
                    frame.pack();
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                }
            }
        });
    }
}
