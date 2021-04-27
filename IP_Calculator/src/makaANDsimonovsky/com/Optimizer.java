package makaANDsimonovsky.com;

import java.util.ArrayList;
import static java.lang.Math.*;

public class Optimizer {

    protected ArrayList<Network> subnetworkList = new ArrayList<>();

    protected String incrementAddress(String networkAddressDecimal){
        String[] arrayNetworkAddressString;
        arrayNetworkAddressString = networkAddressDecimal.split("\\.");
        int[] arrayNetworkAddressInteger = new int[4];
        for (int i = 0; i < arrayNetworkAddressString.length; i++){
            arrayNetworkAddressInteger[i] = Integer.parseInt(arrayNetworkAddressString[i]);
        }
        int index = 0;
        while(arrayNetworkAddressInteger[3-index] == 255){
            index++;
        }
        arrayNetworkAddressInteger[3 - index]++;
        for (int i = 0; i < index; i++){
            arrayNetworkAddressInteger[3-i] = 0;
        }
        for (int i = 0; i < arrayNetworkAddressString.length; i++){
            arrayNetworkAddressString[i] = Integer.toString(arrayNetworkAddressInteger[i]);
        }
        String addressAfterIncrement = String.join(".", arrayNetworkAddressString);
        return addressAfterIncrement;
    }

    protected void optimize(String networkAddress, Integer networkMaskNumeral, ArrayList<Integer> listOfNeededSubnetworksHosts){
        Network network = new Network(networkAddress, networkMaskNumeral);
        network.ifNumberOfHostsOkay(listOfNeededSubnetworksHosts);
        //exception, first address of subnetwork is address of network
        subnetworkList.add(new Network(networkAddress, countingNumeralMask(listOfNeededSubnetworksHosts.get(0))));
        String broadcastAddress = subnetworkList.get(0).getBroadcastAddressDecimal();
        for (int i = 1; i < listOfNeededSubnetworksHosts.size(); i++){
            System.out.println(broadcastAddress);
            subnetworkList.add(new Network(incrementAddress(broadcastAddress), countingNumeralMask(listOfNeededSubnetworksHosts.get(i))));
            broadcastAddress = subnetworkList.get(i).getBroadcastAddressDecimal();
        }
    }

    protected Integer countingNumeralMask(Integer numberOfNeededHosts){
        int j = 0;
        while (pow(2,j) - 2 < numberOfNeededHosts){
            j++;
        }
        int mask = 32 - j;
        return mask;
    }
}
