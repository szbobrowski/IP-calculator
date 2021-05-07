package makaANDsimonovsky.com;

import org.junit.jupiter.api.*;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class NetworkTest {

    private Network network;

    @BeforeEach
    public void init(){
        this.network = new Network("192.168.128.0", 18);
    }

    @AfterEach
    public void tearDown() {
        this.network = null;
    }

    @Test
    public void checkingConvertAddressToBinary() {
        assertEquals("11000000.10101000.10000000.00000000", network.convertAddressToBinary("192.168.128.0"));
    }

    @Test
    public void checkingConvertAddressToDecimal() {
        assertEquals("192.168.128.0", network.convertAddressToDecimal("11000000.10101000.10000000.00000000"));
    }

    @Test
    public void checkingConvertNetworkAddressToBinary() {
        assertEquals("11000000.10101000.10000000.00000000", network.getNetworkAddressBinary());
    }

    @Test
    public void checkingConvertMaskToBinary() {
        assertEquals("11111111.11111111.11000000.00000000", network.getNetworkMaskBinary());
    }

    @Test
    public void checkingConvertMaskToDecimal() {
        assertEquals("255.255.192.0", network.getNetworkMaskDecimal());
    }

    @Test
    public void checkingCalculateNumberOfAvailableHosts() {
        assertEquals(16382, network.getNumberOfAvailableHosts());
    }

    @Test
    public void checkingFindingBroadcastAddressBinary() {
        assertEquals("11000000.10101000.10111111.11111111", network.getBroadcastAddressBinary());
    }

    @Test
    public void checkingConvertBroadcastAddressToDecimal() {
        assertEquals("192.168.191.255", network.getBroadcastAddressDecimal());
    }

    @Test
    public void checkingFindingFirstHostAddressDecimal() {
        assertEquals("192.168.128.1", network.getFirstHostAddress());
    }

    @Test
    public void checkingFindingLastHostAddressDecimal() {
        assertEquals("192.168.191.254", network.getLastHostAddress());
    }

    @Test
    public void checkingIfNetworkIsCorrect() {
        assertTrue(network.ifNetworkIsCorrect());
    }

    @Nested
    class TestOfCheckingIfNumberOfHostsOkay {

        ArrayList<Integer> list;

        @BeforeEach
        public void init() {
            list = new ArrayList<>();
            list.add(1000);
            list.add(1);
            list.add(80);
            list.add(3600);
            list.add(9000);
        }

        @AfterEach
        public void tearDown() {
            this.list = null;
        }

        @Test
        public void checkingIfNumberOfHostsOkay() {
            assertFalse(network.ifNumberOfHostsOkay(list));
        }

    }
}