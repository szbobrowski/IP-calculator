package makaANDsimonovsky.com;

import org.junit.jupiter.api.*;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class OptimizerTest {

    private static Optimizer object;
    private static ArrayList<Integer> arrayListOfNeededSubnetworksHosts;

    @BeforeAll
    public static void init(){

        arrayListOfNeededSubnetworksHosts = new ArrayList<>();
        object = new Optimizer();

        arrayListOfNeededSubnetworksHosts.add(1000);
        arrayListOfNeededSubnetworksHosts.add(234);
        arrayListOfNeededSubnetworksHosts.add(131);

        object.optimize("192.168.128.0", 18, arrayListOfNeededSubnetworksHosts);
    }

    @AfterAll
    public static void tearDown() {
        arrayListOfNeededSubnetworksHosts = null;
        object = null;
    }

    @Test
    public void testCountingNumeralMask() {
        assertEquals(24, object.countNumeralMask(230));
    }

    @Test
    public void testIncrementAddress() {
        assertEquals("192.168.129.0", object.incrementAddress("192.168.128.255"));
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class TestMethodOptimize {

        private ArrayList<Network> subnetworkList;

        @BeforeAll
        public void init() {
            subnetworkList = new ArrayList<>();
            subnetworkList.add(new Network("192.168.128.0", 22));
            subnetworkList.add(new Network("192.168.132.0", 24));
            subnetworkList.add(new Network("192.168.133.0", 24));
        }

        @AfterAll
        public void tearDown() {
            subnetworkList = null;
        }

        @Test
        public void testFirstSubnetworkAddress() {
            assertEquals(subnetworkList.get(0).getNetworkAddressDecimal(), object.subnetworkList.get(0).getNetworkAddressDecimal());
        }

        @Test
        public void testFirstSubnetworkMask() {
            assertEquals(subnetworkList.get(0).getNetworkMaskNumeral(), object.subnetworkList.get(0).getNetworkMaskNumeral());
        }

        @Test
        public void testSecondSubnetworkAddress() {
            assertEquals(subnetworkList.get(1).getNetworkAddressDecimal(), object.subnetworkList.get(1).getNetworkAddressDecimal());
        }

        @Test
        public void testSecondSubnetworkMask() {
            assertEquals(subnetworkList.get(1).getNetworkMaskNumeral(), object.subnetworkList.get(1).getNetworkMaskNumeral());
        }

        @Test
        public void testThirdSubnetworkAddress() {
            assertEquals(subnetworkList.get(2).getNetworkAddressDecimal(), object.subnetworkList.get(2).getNetworkAddressDecimal());
        }

        @Test
        public void testThirdSubnetworkMask() {
            assertEquals(subnetworkList.get(2).getNetworkMaskNumeral(), object.subnetworkList.get(2).getNetworkMaskNumeral());
        }
    }
}