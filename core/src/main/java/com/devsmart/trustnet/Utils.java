package com.devsmart.trustnet;

public class Utils {

    /**
     * convert a byte array representing an unsigned integer (4 bytes) to its long value
     * @param data
     * @param offset
     * @return
     */
    public static long unsignedIntToLong(byte[] data, int offset) {
        long l = 0;
        l |= data[offset] & 0xFF;
        l <<= 8;
        l |= data[offset+1] & 0xFF;
        l <<= 8;
        l |= data[offset+2] & 0xFF;
        l <<= 8;
        l |= data[offset+3] & 0xFF;

        return l;
    }
}
