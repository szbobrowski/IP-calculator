package makaANDsimonovsky.com;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IP_CalculatorTest {

    @Test
    public void checkLowestPossibleMask() {
        assertTrue(IP_Calculator.isMaskCorrect("1"));
    }

    @Test
    public void checkBiggestPossibleMask() {
        assertTrue(IP_Calculator.isMaskCorrect("31"));
    }

    @Test
    public void checkLowestNotAllowedMask() {
        assertFalse(IP_Calculator.isMaskCorrect("32"));
    }

    @Test
    public void checkNegativeMask() {
        assertFalse(IP_Calculator.isMaskCorrect("-5"));
    }

    @Test
    public void checkNotNumericalMask() {
        assertFalse(IP_Calculator.isMaskCorrect("pizza"));
    }

    @Test
    public void checkMaximumAllowedAddress() {
        assertTrue(IP_Calculator.isAddressCorrect("255.255.255.255"));
    }

    @Test
    public void checkMinimumAllowedAddress() {
        assertTrue(IP_Calculator.isAddressCorrect("0.0.0.0"));
    }

    @Test
    public void checkIfSubnetsHasBeenCleared() {
        IP_Calculator.numbersOfHosts.clear();
        assertTrue(IP_Calculator.numbersOfHosts.isEmpty());
    }

}