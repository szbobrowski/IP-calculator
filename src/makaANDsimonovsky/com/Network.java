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

    public Network(String networkAddressDecimal, Integer networkMaskNumeral) {
        this.networkAddressDecimal = networkAddressDecimal;
        this.networkMaskNumeral = networkMaskNumeral;
        setNetworkAddressBinary();
        convertMaskToBinary();
    }

    //wgl co robimy czy podajemy adres sieci czy adres IP po prostu czy musi to byc adres sieci?
    //co robimy z zabezpieczeniami w tej klasie czy w głównej? żeby nie było sytuacji gdzie będzie wieksze od 255 lub liczba ujemna

    public void setNetworkAddressBinary() {
        this.networkAddressBinary = convertAddressToBinary(networkAddressDecimal);
    }

    //zalezy nam na addressBinary w postaci 11111111.111100 itd. (znaczy z kropkami) czy moze tylko tak 1111000100101010 ...?
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

    public static String convertAddressToDecimal(String addressBinary){
        String[] arrayAddressBinary;
        arrayAddressBinary = addressBinary.split("\\.");
        String addressDecimal;
        String [] arrayAddressDecimal = new String[4];
        for(int i = 0; i < 4; i++){
            //int element = Integer.parseInt(arrayAddressDecimal[i]);
            String element = arrayAddressBinary[i];
            String[] arrayOfElement = element.split("");
            int newElement = 0;
            for(int j = 0; j < element.length(); j++){
                int argument = Integer.parseInt(arrayOfElement[element.length()-1-j]);
                int power = (int) pow(2,j);
                newElement = newElement + argument*power;
            }
            //arrayAddressBinary[i] = newElement.toString(); //czemu to nie dziala?
        }
        return "addressDecimal";
    }

    //zrobic jako setter czy zostawic jak jest?
    protected String convertMaskToBinary(){
        StringBuilder maskBinaryBuild = new StringBuilder();
        maskBinaryBuild.append("1".repeat(Math.max(0, networkMaskNumeral)));
        maskBinaryBuild.append("0".repeat(Math.max(0, 32 - networkMaskNumeral)));
        maskBinaryBuild.insert(8, ".");
        maskBinaryBuild.insert(17, ".");
        maskBinaryBuild.insert(26, ".");
        this.networkMaskBinary = maskBinaryBuild.toString();
        return networkMaskBinary;
    }

    protected double calculateNumberOfAvailableHosts(){
        int numberOfZeros = 32 - networkMaskNumeral;
        numberOfAvailableHosts = pow(2,numberOfZeros) - 2;
        return numberOfAvailableHosts;
    }

    protected String findingBroadcastAddressBinary(){
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
        broadcastAddressBinary = broadcastAddressBinaryBuild.toString();
        return broadcastAddressBinary;
    }

    public static void main(String[] args) {
        System.out.println("Hello, World of IP subnets!");
        convertAddressToDecimal("11000000.10101000.10000000.00000000");
    }
}