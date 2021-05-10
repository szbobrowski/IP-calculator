package makaANDsimonovsky.com;

import javax.swing.*;
import java.util.*;

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

    static ArrayList<Integer> numbersOfHostsSorted = new ArrayList<Integer>();

    SubnettingResultGUI() {
        addressField.setText(IP_Calculator.address);
        maskField.setText(Integer.toString(IP_Calculator.mask));
        subnetsField.setText(Integer.toString(IP_Calculator.numbersOfHosts.size()));

        Network network = new Network(IP_Calculator.address, IP_Calculator.mask);

        if (!network.isNetworkCorrect()) {
            subnetsArea.setText("This is not a valid network address.");
        } else if (!network.isNumberOfHostsCorrect(IP_Calculator.numbersOfHosts)) {
            subnetsArea.setText("Not enough available address for such addressing scheme.");
        } else {
            Optimizer optimizer = new Optimizer();
            numbersOfHostsSorted.clear();
            numbersOfHostsSorted.addAll(IP_Calculator.numbersOfHosts);
            Collections.sort(numbersOfHostsSorted, Collections.reverseOrder());
            optimizer.optimize(IP_Calculator.address, IP_Calculator.mask, numbersOfHostsSorted);

            int i = 1;
            
            for (Network object : optimizer.subnetworkList) {
                subnetsArea.append("Subnet nr " + i + ": " + object.networkAddressDecimal + "/" + object.networkMaskNumeral);
                subnetsArea.append("\n");
                i++;
            }
        }
    }
}
