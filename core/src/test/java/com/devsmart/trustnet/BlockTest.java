package com.devsmart.trustnet;

import com.google.common.hash.HashCode;
import org.junit.Test;

import static org.junit.Assert.*;

public class BlockTest {

    @Test
    public void testCreateDifficulty() {
        int value = Block.createDifficultyBits(8);
        assertEquals(0x00FFFFFF, value);
    }


    @Test
    public void testBlockValid() {

        HashCode previous = Block.HASH_FUNCTION.newHasher().putInt(1).hash();

        //Block b = new Block(2, previous, 0, )
    }
}
