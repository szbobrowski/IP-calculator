package makaANDsimonovsky.com;

import javax.swing.*;

public class SubnettingResultGUI {
    public JPanel subnettingResultPanel;
    private JPanel innerMainPanel;
    private JPanel overallResultsPanel;
    private JPanel subnetsPanel;
    private JLabel subnetsLabel;
    private JTextArea subnetsArea;
    private JTextField addressField;
    private JTextField maskField;
    private JTextField subnetsField;
    private JLabel topFulfillLabel;
    private JLabel rightFulfillLabel;


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

    SubnettingResultGUI() {
        addressField.setText(IP_Calculator.address);
        maskField.setText(Integer.toString(IP_Calculator.mask));
        subnetsField.setText(Integer.toString(IP_Calculator.numberOfHosts.size()));

        Network network = new Network(IP_Calculator.address, IP_Calculator.mask);
        System.out.println(IP_Calculator.numberOfHosts.toString());

        if(!network.ifNetworkIsCorrect()) {
            subnetsArea.setText("This is not a valid network address.");
        } else if (!network.ifNumberOfHostsOkay(IP_Calculator.numberOfHosts)) {
            subnetsArea.setText("Not enough available address for such addressing scheme.");
        } else {
            Optimizer optimizer = new Optimizer();
            optimizer.optimize(IP_Calculator.address, IP_Calculator.mask, IP_Calculator.numberOfHosts);

            int i = 1;
            for(Network object : optimizer.subnetworkList) {
                subnetsArea.append("Subnet nr " + i + ": " + object.networkAddressDecimal + "/" + object.networkMaskNumeral);
                subnetsArea.append("\n");
                i++;
            }
        }
    }
}
