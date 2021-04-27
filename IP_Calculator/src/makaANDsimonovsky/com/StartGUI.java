package makaANDsimonovsky.com;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartGUI {

    private JPanel mainPanel;
    private JLabel messageLabel;
    private JButton convertingButton;
    private JButton subnettingButton;
    private JPanel functionalPanel;
    private JLabel leftFulfillLabel;
    private JLabel rightFulfillLabel;
    private JLabel bottomFulfillLabel;
    private JLabel topFulfillLabel;

    public StartGUI() {
        subnettingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("SubnettingDataGUI");
                frame.setContentPane(new SubnettingDataGUI().subnettingPanel);
                frame.pack();
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            }
        });

        convertingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("ConvertingGUI");
                frame.setContentPane(new ConvertingGUI().convertingPanel);
                frame.pack();
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("StartGUI");
        frame.setContentPane(new StartGUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
