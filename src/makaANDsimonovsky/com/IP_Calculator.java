package makaANDsimonovsky.com;

import java.util.*;

public class IP_Calculator {
    protected static String address = "";
    protected static Integer mask = 0;
    protected static Integer numberOfSubnets = 0;
    ArrayList<Integer> numberOfHosts = new ArrayList<Integer>();

    Scanner sc = new Scanner(System.in);

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

    protected void takeAddressInput() {
        System.out.print("Enter the address (for example 192.168.1.0): ");
        address = sc.nextLine();
        String[] partsOfAddress = address.split("\\.");

        boolean flag = true;
        if (partsOfAddress.length != 4) flag = false;
        else if (!isInteger(partsOfAddress[0]) || !isInteger(partsOfAddress[1]) ) flag = false;
        else if (!isInteger(partsOfAddress[2]) || !isInteger(partsOfAddress[3]) ) flag = false;
        else if (Integer.parseInt(partsOfAddress[0]) < 0 || Integer.parseInt(partsOfAddress[0]) > 255) flag = false;
        else if (Integer.parseInt(partsOfAddress[1]) < 0 || Integer.parseInt(partsOfAddress[1]) > 255) flag = false;
        else if (Integer.parseInt(partsOfAddress[2]) < 0 || Integer.parseInt(partsOfAddress[2]) > 255) flag = false;
        else if (Integer.parseInt(partsOfAddress[3]) < 0 || Integer.parseInt(partsOfAddress[3]) > 255) flag = false;

        while (!flag) {
            System.out.println("Provided address incorrect.");
            System.out.print("Enter the address (for example 192.168.1.0): ");
            address = sc.nextLine();
            partsOfAddress = address.split("\\.");
            if (partsOfAddress.length != 4) flag = false;
            else if (!isInteger(partsOfAddress[0]) || !isInteger(partsOfAddress[1]) ) flag = false;
            else if (!isInteger(partsOfAddress[2]) || !isInteger(partsOfAddress[3]) ) flag = false;
            else if (Integer.parseInt(partsOfAddress[0]) < 0 || Integer.parseInt(partsOfAddress[0]) > 255) flag = false;
            else if (Integer.parseInt(partsOfAddress[1]) < 0 || Integer.parseInt(partsOfAddress[1]) > 255) flag = false;
            else if (Integer.parseInt(partsOfAddress[2]) < 0 || Integer.parseInt(partsOfAddress[2]) > 255) flag = false;
            else if (Integer.parseInt(partsOfAddress[3]) < 0 || Integer.parseInt(partsOfAddress[3]) > 255) flag = false;
            else flag = true;
        }
    }

    protected void takeMaskInput() {
        System.out.print("Enter mask to the address (for example 24): ");
        String maskText = "";
        maskText = sc.nextLine();

        boolean flag = true;
        if (!isInteger(maskText)) flag = false;
        else if (Integer.parseInt(maskText) < 1) flag = false;
        else if (Integer.parseInt(maskText) > 31) flag = false;

        while (!flag) {
            System.out.println("Invalid mask! Must be integer from 1 to 31");
            System.out.print("Enter mask to the address (for example 24): ");
            maskText = sc.nextLine();

            if (!isInteger(maskText)) flag = false;
            else if (Integer.parseInt(maskText) < 1) flag = false;
            else if (Integer.parseInt(maskText) > 31) flag = false;
            else flag = true;
        }

        mask = Integer.parseInt(maskText);
    }

    protected void takeNumberOfSubnets() {

        System.out.print("Enter number of subnets: ");
        String numberText = "";
        numberText = sc.nextLine();

        boolean flag = true;
        if (!isInteger(numberText)) flag = false;
        else if (Integer.parseInt(numberText) < 1) flag = false;
        else if (Integer.parseInt(numberText) > 100) flag = false;

        while (!flag) {
            System.out.println("Invalid number of subnets! Must be integer from 1 to 100");
            System.out.print("Enter number of subnets: ");
            numberText = sc.nextLine();

            if (!isInteger(numberText)) flag = false;
            else if (Integer.parseInt(numberText) < 1) flag = false;
            else if (Integer.parseInt(numberText) > 31) flag = false;
            else flag = true;
        }

        numberOfSubnets = Integer.parseInt(numberText);
    }

    protected void takeNumberOfHosts() {
        for(int i = 1; i <= numberOfSubnets; i++) {
            System.out.print("Number of hosts for subnetwork " + i + ": ");

            String numberText = "";
            numberText = sc.nextLine();

            boolean flag = true;
            if (!isInteger(numberText)) flag = false;
            else if (Integer.parseInt(numberText) < 1) flag = false;
            else if (Integer.parseInt(numberText) > 100000) flag = false;

            while (!flag) {
                System.out.println("Invalid number of subnets! Must be integer from 1 to 100");
                System.out.print("Number of hosts for subnetwork " + i + ": ");
                numberText = sc.nextLine();

                if (!isInteger(numberText)) flag = false;
                else if (Integer.parseInt(numberText) < 1) flag = false;
                else if (Integer.parseInt(numberText) > 31) flag = false;
                else flag = true;
            }

            numberOfHosts.add(Integer.parseInt(numberText));
        }
    }
}
