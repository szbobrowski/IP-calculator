package makaANDsimonovsky.com;

import java.util.*;

public class IP_Calculator {
    protected static String address = "";
    protected static Integer mask = 0;
    static ArrayList<Integer> numbersOfHosts = new ArrayList<Integer>();

    static Scanner sc = new Scanner(System.in);

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
