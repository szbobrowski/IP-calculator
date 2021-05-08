package makaANDsimonovsky.com;

import java.util.*;

public class IP_Calculator {

    protected static String address = "";
    protected static Integer mask = 0;
    static ArrayList<Integer> numbersOfHosts = new ArrayList<>();

    public static boolean isInteger(String strNum) {
        if (strNum == null) {
            return false;
        }

        try {
            Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }

        return true;
    }

    protected static boolean isAddressCorrect(String address) {
        String[] partsOfAddress = address.split("\\.");

        boolean flag = true;
        if (partsOfAddress.length != 4) flag = false;
        else if (!isInteger(partsOfAddress[0]) || !isInteger(partsOfAddress[1]) ) flag = false;
        else if (!isInteger(partsOfAddress[2]) || !isInteger(partsOfAddress[3]) ) flag = false;
        else if (Integer.parseInt(partsOfAddress[0]) < 0 || Integer.parseInt(partsOfAddress[0]) > 255) flag = false;
        else if (Integer.parseInt(partsOfAddress[1]) < 0 || Integer.parseInt(partsOfAddress[1]) > 255) flag = false;
        else if (Integer.parseInt(partsOfAddress[2]) < 0 || Integer.parseInt(partsOfAddress[2]) > 255) flag = false;
        else if (Integer.parseInt(partsOfAddress[3]) < 0 || Integer.parseInt(partsOfAddress[3]) > 255) flag = false;

        return flag;
    }

    protected static boolean isMaskCorrect(String maskText) {
        boolean flag = true;

        if (!isInteger(maskText)) flag = false;
        else if (Integer.parseInt(maskText) < 1) flag = false;
        else if (Integer.parseInt(maskText) > 31) flag = false;

        return flag;
    }

    protected static void setAddress(String addressReceived) {
        address = addressReceived;
    }

    protected static void setMask(String maskText) {
        mask = Integer.parseInt(maskText);
    }

    protected static void takeNumberOfHosts(int num) {
        numbersOfHosts.add(num);
    }
}
