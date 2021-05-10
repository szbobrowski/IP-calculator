package makaANDsimonovsky.com;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class IP_CalculatorTest {

    @Test
    public void testLowestPossibleMask() { assertTrue(IP_Calculator.isMaskCorrect("1")); }

    @Test
    public void testNegativeMask() {
        assertFalse(IP_Calculator.isMaskCorrect("-5"));
    }

    @Test
    public void testNotNumericalMask() {
        assertFalse(IP_Calculator.isMaskCorrect("pizza"));
    }

    @Test
    public void testMaximumAllowedAddress() {
        assertTrue(IP_Calculator.isAddressCorrect("255.255.255.255"));
    }

    @Test
    public void testMinimumAllowedAddress() {
        assertTrue(IP_Calculator.isAddressCorrect("0.0.0.0"));
    }

    @Test
    public void testLowestNotAllowedMask() {
        assertFalse(IP_Calculator.isMaskCorrect("32"));
    }

    @Test
    public void testGreatestPossibleMask() {
        assertTrue(IP_Calculator.isMaskCorrect("31"));
    }



    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class SubnetsClearOptionTest {

        @BeforeAll
        void init() {
            IP_Calculator.numbersOfHosts.clear();
        }

        @Test
        public void testIfSubnetsHasBeenCleared() {
            assertTrue(IP_Calculator.numbersOfHosts.isEmpty());
        }

        @Test
        public void testIfLengthIsZero() {
            assertEquals(IP_Calculator.numbersOfHosts.size(), 0);
        }

    }

}