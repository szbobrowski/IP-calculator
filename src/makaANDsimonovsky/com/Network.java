package makaANDsimonovsky.com;

import static java.lang.Math.*;

public class Network {

    protected String networkAddressDecimal;
    protected String networkAddressBinary;
    protected Integer networkMaskNumeral;
    protected String networkMaskDecimal;
    protected String networkMaskBinary;
    protected double numberOfAvailableHosts;
    protected String broadcastAddressDecimal;
    protected String broadcastAddressBinary;
    protected String firstHostAddress;
    protected String lastHostAddress;

    //Constructor

    public Network(String networkAddressDecimal, Integer networkMaskNumeral) {
        this.networkAddressDecimal = networkAddressDecimal;
        this.networkMaskNumeral = networkMaskNumeral;
        setNetworkAddressBinary();
        setNetworkMaskBinary();
        setNetworkMaskDecimal();
        setNumberOfAvailableHosts();
        setBroadcastAddressBinary();
        setBroadcastAddressDecimal();
        setFirstHostAddress();
        setLastHostAddress();
    }

    //Setters

    public void setNetworkAddressBinary() {
        this.networkAddressBinary = convertAddressToBinary(networkAddressDecimal);
    }

    public void setNetworkMaskBinary() {
        StringBuilder maskBinaryBuild = new StringBuilder();
        maskBinaryBuild.append("1".repeat(Math.max(0, networkMaskNumeral)));
        maskBinaryBuild.append("0".repeat(Math.max(0, 32 - networkMaskNumeral)));
        maskBinaryBuild.insert(8, ".");
        maskBinaryBuild.insert(17, ".");
        maskBinaryBuild.insert(26, ".");
        this.networkMaskBinary = maskBinaryBuild.toString();
    }

    public void setNetworkMaskDecimal() {
        this.networkMaskDecimal = convertAddressToDecimal(networkAddressBinary);
    }

    public void setNumberOfAvailableHosts(){
        int numberOfZeros = 32 - networkMaskNumeral;
        this.numberOfAvailableHosts = pow(2,numberOfZeros) - 2;
    }

    public void setBroadcastAddressBinary(){
        StringBuilder broadcastAddressBinaryBuild = new StringBuilder();
        String networkAddressBinaryWithoutDots = networkAddressBinary.replace(".","");
        String[] arrayOfAddress = networkAddressBinaryWithoutDots.split("");
        String networkMaskBinaryWithoutDots = networkMaskBinary.replace(".","");
        String[] arrayOfMask = networkMaskBinaryWithoutDots.split("");
        for(int i = 0; i < 32; i++){
            if(arrayOfMask[i].equals("1")){
                broadcastAddressBinaryBuild.append(arrayOfAddress[i]);
            }else{
                broadcastAddressBinaryBuild.append("1");
            }
        }
        broadcastAddressBinaryBuild.insert(8, ".");
        broadcastAddressBinaryBuild.insert(17, ".");
        broadcastAddressBinaryBuild.insert(26, ".");
        this.broadcastAddressBinary = broadcastAddressBinaryBuild.toString();
    }

    public void setBroadcastAddressDecimal() {
        this.broadcastAddressDecimal = convertAddressToDecimal(broadcastAddressBinary);
    }

    public void setFirstHostAddress() {
        String[] arrayFirstHostAddress;
        arrayFirstHostAddress = networkAddressDecimal.split("\\.");
        int lastOctet = Integer.parseInt(arrayFirstHostAddress[3]);
        lastOctet += 1;
        arrayFirstHostAddress[3] = Integer.toString(lastOctet);
        this.firstHostAddress = String.join(".", arrayFirstHostAddress);
    }

    public void setLastHostAddress() {
        String[] arrayLastHostAddress;
        arrayLastHostAddress = broadcastAddressDecimal.split("\\.");
        int lastOctet = Integer.parseInt(arrayLastHostAddress[3]);
        lastOctet -= 1;
        arrayLastHostAddress[3] = Integer.toString(lastOctet);
        this.lastHostAddress = String.join(".", arrayLastHostAddress);
    }

    //Methods

    protected String convertAddressToBinary(String addressDecimal){
        String[] arrayAddressDecimal;
        arrayAddressDecimal = addressDecimal.split("\\.");
        String addressBinary;
        String [] arrayAddressBinary = new String[4];
        for(int i = 0; i < 4; i++){
            int element = Integer.parseInt(arrayAddressDecimal[i]);
            StringBuilder newElement = new StringBuilder();
            while(element > 0) {
                if (element % 2 == 0) {
                    newElement.insert(0, "0");
                }
                else{
                    newElement.insert(0, "1");
                }
                element = element/2;
            }
            int lengthOfElement = newElement.length();
            for(int j = 0; j < (8 - lengthOfElement); j++){
                newElement.insert(0, "0");
            }
            arrayAddressBinary[i] = newElement.toString();
        }
        addressBinary = String.join(".", arrayAddressBinary);
        return addressBinary;
    }

    public String convertAddressToDecimal(String addressBinary){
        String[] arrayAddressBinary;
        arrayAddressBinary = addressBinary.split("\\.");
        String addressDecimal;
        String [] arrayAddressDecimal = new String[4];
        for(int i = 0; i < 4; i++){
            String element = arrayAddressBinary[i];
            String[] arrayOfElement = element.split("");
            int newElement = 0;
            for(int j = 0; j < element.length(); j++){
                int argument = Integer.parseInt(arrayOfElement[element.length()-1-j]);
                int power = (int) pow(2,j);
                newElement = newElement + argument*power;
            }
            arrayAddressDecimal[i] = Integer.toString(newElement);
        }
        addressDecimal = String.join(".", arrayAddressDecimal);
        return addressDecimal;
    }
}