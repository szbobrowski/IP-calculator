package makaANDsimonovsky.com;

import java.util.ArrayList;
import static java.lang.Math.*;

public class Optimizer {

    protected ArrayList<Network> subnetworkList = new ArrayList<>();

    protected String incrementAddress(String networkAddressDecimal) {
        String[] arrayNetworkAddressString;
        arrayNetworkAddressString = networkAddressDecimal.split("\\.");
        int[] arrayNetworkAddressInteger = parseStringArrayToIntArray(arrayNetworkAddressString);

        int index = findWhichNumberToIncrement(arrayNetworkAddressInteger);
        arrayNetworkAddressInteger[3 - index]++;
        arrayNetworkAddressInteger = insertZeros(index, arrayNetworkAddressInteger);

        arrayNetworkAddressString = parseIntArrayToStringArray(arrayNetworkAddressInteger, arrayNetworkAddressString);

        return String.join(".", arrayNetworkAddressString);
    }

    protected void optimize(String networkAddress, Integer networkMaskNumeral, ArrayList<Integer> listOfNeededSubnetworksHosts) {
        Network network = new Network(networkAddress, networkMaskNumeral);
        network.isNumberOfHostsCorrect(listOfNeededSubnetworksHosts);
        
        subnetworkList.add(new Network(networkAddress, countNumeralMask(listOfNeededSubnetworksHosts.get(0))));
        String broadcastAddress = subnetworkList.get(0).getBroadcastAddressDecimal();

        for (int i = 1; i < listOfNeededSubnetworksHosts.size(); i++) {
            subnetworkList.add(new Network(incrementAddress(broadcastAddress), countNumeralMask(listOfNeededSubnetworksHosts.get(i))));
            broadcastAddress = subnetworkList.get(i).getBroadcastAddressDecimal();
        }
    }

    protected int[] insertZeros(int index, int[] arrayNetworkAddressInteger){
        for (int i = 0; i < index; i++){
            arrayNetworkAddressInteger[3-i] = 0;
        }
        return arrayNetworkAddressInteger;
    }

    protected int findWhichNumberToIncrement(int[] arrayNetworkAddressInteger) {
        int index = 0;
        while(arrayNetworkAddressInteger[3-index] == 255){
            index++;
        }
        return index;
    }

    protected int[] parseStringArrayToIntArray(String[] arrayNetworkAddressString) {
        int[] arrayNetworkAddressInteger = new int[4];
        for (int i = 0; i < arrayNetworkAddressString.length; i++){
            arrayNetworkAddressInteger[i] = Integer.parseInt(arrayNetworkAddressString[i]);
        }
        return arrayNetworkAddressInteger;
    }

    protected String[] parseIntArrayToStringArray(int[] arrayNetworkAddressInteger, String[] arrayNetworkAddressString) {
        for (int i = 0; i < arrayNetworkAddressString.length; i++){
            arrayNetworkAddressString[i] = Integer.toString(arrayNetworkAddressInteger[i]);
        }
        return arrayNetworkAddressString;
    }

    protected Integer countNumeralMask(Integer numberOfNeededHosts) {
        int j = 0;

        while (pow(2,j) - 2 < numberOfNeededHosts) {
            j++;
        }

        return 32 - j;
    }
}
