package makaANDsimonovsky.com;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class NetworkTest {

    private final Network network = new Network("192.168.128.0", 18);

    @Test
    public void checkingConvertAddressToBinary() {
        assertEquals("11000000.10101000.10000000.00000000", network.networkAddressBinary);
    }

    @Test
    public void checkingConvertAddressToDecimal() {
        assertEquals("192.168.191.255", network.broadcastAddressDecimal);
    }

    @Test
    public void checkingConvertMaskToDecimal() {
        assertEquals("11111111.11111111.11000000.00000000", network.networkMaskBinary);
    }

    @Test
    public void checkingCalculateNumberOfAvailableHosts() {
        assertEquals(16382, network.numberOfAvailableHosts);
    }

    @Test
    public void checkingFindingBroadcastAddressBinary() {
        assertEquals("11000000.10101000.10111111.11111111", network.broadcastAddressBinary);
    }

    @Test
    public void checkingFindingFirstHostAddressDecimal() {
        assertEquals("192.168.128.1", network.firstHostAddress);
    }

    @Test
    public void checkingFindingLastHostAddressDecimal() {
        assertEquals("192.168.191.254", network.lastHostAddress);
    }

    @Test
    public void checkingIfNetworkIsCorrect() {
        assertTrue(network.ifNetworkIsCorrect());
    }

    @Test
    public void checkingIfNumberOfHostsOkay() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1000);
        list.add(3000);
        list.add(4000);
        list.add(6000);
        list.add(9000);
        assertFalse(network.ifNumberOfHostsOkay(list));
    }
}