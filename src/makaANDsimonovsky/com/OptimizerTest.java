package makaANDsimonovsky.com;

import org.junit.jupiter.api.*;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class OptimizerTest {

    private Optimizer object;
    private ArrayList<Integer> arrayListOfNeededSubnetworksHosts;

    @BeforeEach
    public void init(){

        this.arrayListOfNeededSubnetworksHosts = new ArrayList<>();
        this.object = new Optimizer();

        arrayListOfNeededSubnetworksHosts.add(1000);
        arrayListOfNeededSubnetworksHosts.add(234);
        arrayListOfNeededSubnetworksHosts.add(131);

        object.optimize("192.168.128.0", 18, arrayListOfNeededSubnetworksHosts);
    }

    @AfterEach
    public void tearDown() {
        this.arrayListOfNeededSubnetworksHosts = null;
        this.object = null;
    }

    @Test
    public void checkingCountingNumeralMask() {
        assertEquals(24, object.countingNumeralMask(230));
    }

    @Test
    public void checkingIncrementAddress() {
        assertEquals("192.168.129.0", object.incrementAddress("192.168.128.255"));
    }

    @Nested
    class TestMethodOptimize {

        private ArrayList<Network> subnetworkList;

        @BeforeEach
        public void init() {
            this.subnetworkList = new ArrayList<>();
            subnetworkList.add(new Network("192.168.128.0", 22));
            subnetworkList.add(new Network("192.168.132.0", 24));
            subnetworkList.add(new Network("192.168.133.0", 24));
        }

        @AfterEach
        public void tearDown() {
            this.subnetworkList = null;
        }

        @Test
        public void checkingFirstSubnetworkAddress() {
            assertEquals(subnetworkList.get(0).getNetworkAddressDecimal(), object.subnetworkList.get(0).getNetworkAddressDecimal());
        }

        @Test
        public void checkingFirstSubnetworkMask() {
            assertEquals(subnetworkList.get(0).getNetworkMaskNumeral(), object.subnetworkList.get(0).getNetworkMaskNumeral());
        }

        @Test
        public void checkingSecondSubnetworkAddress() {
            assertEquals(subnetworkList.get(1).getNetworkAddressDecimal(), object.subnetworkList.get(1).getNetworkAddressDecimal());
        }

        @Test
        public void checkingSecondSubnetworkMask() {
            assertEquals(subnetworkList.get(1).getNetworkMaskNumeral(), object.subnetworkList.get(1).getNetworkMaskNumeral());
        }

        @Test
        public void checkingThirdSubnetworkAddress() {
            assertEquals(subnetworkList.get(2).getNetworkAddressDecimal(), object.subnetworkList.get(2).getNetworkAddressDecimal());
        }

        @Test
        public void checkingThirdSubnetworkMask() {
            assertEquals(subnetworkList.get(2).getNetworkMaskNumeral(), object.subnetworkList.get(2).getNetworkMaskNumeral());
        }
    }
}