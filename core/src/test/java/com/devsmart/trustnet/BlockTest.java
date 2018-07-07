package com.devsmart.trustnet;

import com.google.common.hash.HashCode;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class BlockTest {

    @Test
    public void testCreateDifficulty() {
        int value;

        value = Block.createDifficultyBits(8);
        assertEquals(0x00FFFFFF, value);

        value = Block.createDifficultyBits(9);
        assertEquals(0x007FFFFF, value);

        value = Block.createDifficultyBits(10);
        assertEquals(0x003FFFFF, value);

        value = Block.createDifficultyBits(16);
        assertEquals(0x01FFFFFF, value);

    }

    @Test
    public void meetsDifficulty() {
        byte[] data = new byte[32];

        assertTrue(Block.meetsDifficulty(0x00FFFFFF, data));

        data[0] = (byte) 0x80;
        assertFalse(Block.meetsDifficulty(0x00FFFFFF, data));

        data = new byte[32];
        data[1] = (byte) 0x80;
        assertTrue(Block.meetsDifficulty(0x00FFFFFF, data));

    }


    @Test
    public void testBlockValid() {

        HashCode previous = Block.HASH_FUNCTION.newHasher().putInt(1).hash();

        //Block b = new Block(2, previous, 0, )
    }
}
