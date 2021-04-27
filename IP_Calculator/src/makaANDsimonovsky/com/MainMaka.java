package makaANDsimonovsky.com;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainMaka {

    public static boolean ifNetworkIsCorrect(){
        String addressBinary = "11000000.10101000.10000001.00000000";
        String networkAddressBinaryWithoutDots = addressBinary.replace(".","");
        String[] arrayOfAddress = networkAddressBinaryWithoutDots.split("");
        for(int i = 0; i < 32-18; i++){
            int element = Integer.parseInt(arrayOfAddress[31 - i]);
            //System.out.println(element);
            if(element != 0){
                return false;
            }
        }
        return true;
    }

    public static String incrementAddress(String networkAddressDecimal){
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

    public static void main(String[] args) {
        System.out.println("Workspace for Marianna");
        System.out.println(ifNetworkIsCorrect());
        System.out.println(incrementAddress("192.168.255.255"));
    }
}
