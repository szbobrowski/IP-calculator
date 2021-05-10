package makaANDsimonovsky.com;

import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class NetworkTest {

    static private Network network;

    @BeforeAll
    public static void init() {
        network = new Network("192.168.128.0", 18);
    }

    @AfterAll
    public static void tearDown() { network = null; }

    @Test
    public void testConvertMaskToDecimal() {
        assertEquals("255.255.192.0", network.getNetworkMaskDecimal());
    }

    @Test
    public void testConvertMaskToDecimalIncorrect() {
        assertNotEquals("119.0.29.0", network.getNetworkMaskDecimal());
    }

    @Test
    public void testConvertNetworkAddressToBinary() {
        assertEquals("11000000.10101000.10000000.00000000", network.getNetworkAddressBinary());
    }

    @Test
    public void testConvertAddressToBinary() {
        assertEquals("11000000.10101000.10000000.00000000", network.convertAddressToBinary("192.168.128.0"));
    }

    @Test
    public void testConvertAddressToDecimal() {
        assertEquals("192.168.128.0", network.convertAddressToDecimal("11000000.10101000.10000000.00000000"));
    }

    @Test
    public void testConvertMaskToBinary() {
        assertEquals("11111111.11111111.11000000.00000000", network.getNetworkMaskBinary());
    }

    @Test
    public void testCalculateNumberOfAvailableHosts() {
        assertEquals(16382, network.getNumberOfAvailableHosts());
    }

    @Test
    public void testFindingBroadcastAddressBinary() {
        assertEquals("11000000.10101000.10111111.11111111", network.getBroadcastAddressBinary());
    }

    @Test
    public void testConvertBroadcastAddressToDecimal() {
        assertEquals("192.168.191.255", network.getBroadcastAddressDecimal());
    }

    @Test
    public void testFindingFirstHostAddressDecimal() {
        assertEquals("192.168.128.1", network.getFirstHostAddress());
    }

    @Test
    public void testFindingLastHostAddressDecimal() {
        assertEquals("192.168.191.254", network.getLastHostAddress());
    }

    @Test
    public void testIfNetworkIsCorrect() {
        assertTrue(network.isNetworkCorrect());
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class NumberOfHostsCorrectness {

        ArrayList<Integer> list;

        @BeforeAll
        public void init() {
            list = new ArrayList<>();
            list.add(38);
            list.add(1);
            list.add(191);
        }

        @AfterAll
        public void tearDown() {
            list = null;
        }

        @Test
        public void testIfNumberOfHostsIsCorrect() {
            assertTrue(network.isNumberOfHostsCorrect(list));
        }
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class TestOfNumberOfHostsCorrectness {

        ArrayList<Integer> list;

        @BeforeAll
        public void init() {
            list = new ArrayList<>();
            list.add(1000);
            list.add(1);
            list.add(80);
            list.add(3600);
            list.add(9000);
        }

        @AfterAll
        public void tearDown() {
            list = null;
        }

        @Test
        public void testIfNumberOfHostsIsCorrect() {
            assertFalse(network.isNumberOfHostsCorrect(list));
        }
    }
}