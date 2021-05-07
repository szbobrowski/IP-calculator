package makaANDsimonovsky.com;

import java.util.ArrayList;
import static java.lang.Math.*;

public class Optimizer {

    protected ArrayList<Network> subnetworkList = new ArrayList<>();

    protected String incrementAddress(String networkAddressDecimal){
        String[] arrayNetworkAddressString;
        arrayNetworkAddressString = networkAddressDecimal.split("\\.");
        int[] arrayNetworkAddressInteger = parsingStringArrayToIntArray(arrayNetworkAddressString);

        int index = findingWhichNumberToIncrement(arrayNetworkAddressInteger);
        arrayNetworkAddressInteger[3 - index]++;
        arrayNetworkAddressInteger = insertZeros(index, arrayNetworkAddressInteger);

        arrayNetworkAddressString = parsingIntArrayToStringArray(arrayNetworkAddressInteger, arrayNetworkAddressString);
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

    protected int[] insertZeros(int index, int[] arrayNetworkAddressInteger){
        for (int i = 0; i < index; i++){
            arrayNetworkAddressInteger[3-i] = 0;
        }
        return arrayNetworkAddressInteger;
    }

    protected int findingWhichNumberToIncrement(int[] arrayNetworkAddressInteger){
        int index = 0;
        while(arrayNetworkAddressInteger[3-index] == 255){
            index++;
        }
        return index;
    }

    protected int[] parsingStringArrayToIntArray(String[] arrayNetworkAddressString){
        int[] arrayNetworkAddressInteger = new int[4];
        for (int i = 0; i < arrayNetworkAddressString.length; i++){
            arrayNetworkAddressInteger[i] = Integer.parseInt(arrayNetworkAddressString[i]);
        }
        return arrayNetworkAddressInteger;
    }

    protected String[] parsingIntArrayToStringArray(int[] arrayNetworkAddressInteger, String[] arrayNetworkAddressString){
        for (int i = 0; i < arrayNetworkAddressString.length; i++){
            arrayNetworkAddressString[i] = Integer.toString(arrayNetworkAddressInteger[i]);
        }
        return arrayNetworkAddressString;
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
