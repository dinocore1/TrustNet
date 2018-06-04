package com.devsmart.trustnet;

import org.junit.Test;

import static org.junit.Assert.*;

public class TrustTest {


    @Test
    public void testCompare() {
        Trust three = Trust.ONE_TRUST.multiply(3);
        Trust two = Trust.ONE_TRUST.multiply(2);

        assertTrue(two.compareTo(three) < 0);
        assertEquals(0, two.compareTo(Trust.ONE_TRUST.multiply(2)));
    }

    @Test
    public void testDivision() {

        Trust three = Trust.ONE_TRUST.multiply(3);
        Trust result = three.divide(3);
        assertEquals(Trust.ONE_TRUST, result);

    }

    @Test
    public void testLeftOver() {
        Trust value = Trust.fromInt(3);
        value = value.divide(2);

        assertEquals(Trust.fromInt(1), value);
        assertEquals(Trust.fromInt(1), value.mod(2));
    }
}
