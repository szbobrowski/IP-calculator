package makaANDsimonovsky.com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubnettingDataGUI extends JFrame {
    public JPanel subnettingPanel;
    private JPanel addressPanel;
    private JPanel subnetPanel;
    private JTextField addressTextField;
    private JTextField maskTextField;
    private JTextField hostsTextField;
    private JButton addSubnetButton;
    private JTextPane subnetTextPane;
    private JLabel addressLabel;
    private JLabel maskLabel;
    private JLabel addSubnetLabel;
    private JLabel alreadyAddedLabel;
    private JLabel topFulfillLabel;
    private JLabel leftFulfillLabel;
    private JLabel rightFulfillLabel;
    private JLabel bottomFulfillLabel;
    private JButton submitButton;

    public static boolean isInteger(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public SubnettingDataGUI() {
        addSubnetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numberText = "";
                numberText = hostsTextField.getText();

                boolean flag = true;
                if (!isInteger(numberText)) flag = false;
                else if (Integer.parseInt(numberText) < 1) flag = false;
                else if (Integer.parseInt(numberText) > 2000000000) flag = false;

                if (flag) {
                    IP_Calculator.takeNumberOfHosts(Integer.parseInt(numberText));
                    subnetTextPane.setText(IP_Calculator.numberOfHosts.toString());
                    hostsTextField.setText("");
                } else {
                    System.out.println("Something went wrong!");
                }
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String address = "";
                address = addressTextField.getText();
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
                maskText = maskTextField.getText();
                if (!isInteger(maskText)) flag = false;
                else if (Integer.parseInt(maskText) < 1) flag = false;
                else if (Integer.parseInt(maskText) > 31) flag = false;

                if(IP_Calculator.numberOfHosts.isEmpty()) flag = false;

                if (flag) {
                    System.out.println("Data correct. Perform submit..");
                    IP_Calculator.setMask(maskText);
                    IP_Calculator.setAddress(address);
                } else {
                    System.out.println("Some data not correct. Cannot perform submit.");
                }
            }
        });
    }
}
