package com.devsmart.trustnet;

import com.google.common.hash.HashCode;
import org.junit.Test;

import static org.junit.Assert.*;

public class BlockchainTest {

    @Test
    public void testAddBlock() {

        Blockchain blockchain = new Blockchain();
        blockchain.mCurrentDifficulty = 0x0102FFFF;

        byte[] data = new byte[0];

        HashCode hashCode = Block.computeHash(0, null, 0, Block.MIN_DIFFICULTY_BITS, 0, data);
        blockchain.mTopBlock = new Block(0, null, 0, Block.MIN_DIFFICULTY_BITS, 0, data, hashCode);



        Block b = Blockchain.mineBlock(1, hashCode, 1, blockchain.mCurrentDifficulty, data);

        assertTrue(blockchain.addBlock(b));

    }
}
