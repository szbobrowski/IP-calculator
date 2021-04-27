package makaANDsimonovsky.com;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OptimizerTest {

    @Test
    public void checkingOptimizing() {
        ArrayList<Integer> arrayListOfNeededSubnetworksHosts = new ArrayList<>();
        arrayListOfNeededSubnetworksHosts.add(1000);
        arrayListOfNeededSubnetworksHosts.add(234);
        arrayListOfNeededSubnetworksHosts.add(131);
        ArrayList<Integer> list = new ArrayList<>();
        Optimizer object = new Optimizer();
        object.optimize("192.168.128.0", 18, arrayListOfNeededSubnetworksHosts);
        ArrayList<Network> subnetworkList = new ArrayList<>();
        Network sub1 = new Network("192.168.128.0", 22);
        subnetworkList.add(sub1);
        Network sub2 = new Network("192.168.132.0", 24);
        subnetworkList.add(sub2);
        Network sub3 = new Network("192.168.133.0", 24);
        subnetworkList.add(sub3);

        assertEquals(subnetworkList.get(0).getNetworkAddressDecimal(), object.subnetworkList.get(0).getNetworkAddressDecimal());
        assertEquals(subnetworkList.get(1).getNetworkAddressDecimal(), object.subnetworkList.get(1).getNetworkAddressDecimal());
        assertEquals(subnetworkList.get(2).getNetworkAddressDecimal(), object.subnetworkList.get(2).getNetworkAddressDecimal());

        assertEquals(subnetworkList.get(0).getNetworkMaskNumeral(), object.subnetworkList.get(0).getNetworkMaskNumeral());
        assertEquals(subnetworkList.get(1).getNetworkMaskNumeral(), object.subnetworkList.get(1).getNetworkMaskNumeral());
        assertEquals(subnetworkList.get(2).getNetworkMaskNumeral(), object.subnetworkList.get(2).getNetworkMaskNumeral());
    }
}