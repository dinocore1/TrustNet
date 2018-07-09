package com.devsmart.trustnet;

import com.devsmart.ubjson.UBArray;
import com.devsmart.ubjson.UBReader;
import com.devsmart.ubjson.UBValue;
import com.google.common.hash.HashCode;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class Blockchain {


    Block mTopBlock;
    int mCurrentDifficulty;

    /**
     *
     * @param b
     * @return true if block was successfully added to the chain.
     */
    public boolean addBlock(Block b) {
        UBValue value;


        final HashCode expected = Block.computeHash(b.index, mTopBlock.hash, b.timestamp, b.difficultyBits, b.nunce, b.data);
        boolean isValid = b.hash.equals(expected)
                && Block.meetsDifficulty(mCurrentDifficulty, b.hash.asBytes());

        if(!isValid) {
            return false;
        }

        try {
            UBReader reader = new UBReader(new ByteArrayInputStream(b.data));
            value = reader.read();
            reader.close();
        } catch (IOException e) {
            return false;
        }

        if(!value.isArray()) {
            return false;
        }

        UBArray array = value.asArray();
        for(int i=0;i<array.size();i++) {
            value = array.get(i);

        }

        mTopBlock = b;
        return true;
    }

    public static Block mineBlock(int index, HashCode previousHash, long timestamp, int difficultyBits, byte[] data) {
        long nunce = Long.MIN_VALUE;
        HashCode hashCode;
        for(;nunce<Long.MAX_VALUE;nunce++) {
            hashCode = Block.computeHash(index, previousHash, timestamp, difficultyBits, nunce, data);
            if(Block.meetsDifficulty(difficultyBits, hashCode.asBytes())) {
                return new Block(index, previousHash, timestamp, difficultyBits, nunce, data, hashCode);
            }
        }

        return null;
    }
}
