package com.devsmart.trustnet;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;

public class Block {

    public static final int MIN_DIFFICULTY_BITS = 0x01FFFFFF;
    public static final HashFunction HASH_FUNCTION = Hashing.sha256();

    public final long index;
    public final HashCode previousHash;
    public final long timestamp;
    public final int difficultyBits;
    public final long nunce;
    public final byte[] data;
    public final HashCode hash;

    public Block(long index, HashCode previousHash, long timestamp, int difficultyBits, long nunce, byte[] data, HashCode hash) {
        this.index = index;
        this.previousHash = previousHash;
        this.timestamp = timestamp;
        this.difficultyBits = difficultyBits;
        this.nunce = nunce;
        this.data = data;
        this.hash = hash;
    }

    public static int createDifficultyBits(int numBits) {
        numBits = Math.max(8, Math.min(248, numBits));
        int shift = (numBits-8) / 8;

        int remain = (numBits - 8) % 24;
        int value = (1 << (24-remain))-1;

        return shift << 24 | (0x00FFFFFF & value);
    }

    public static boolean meetsDifficulty(int difficultyBits, byte[] hash) {
        int shift = Math.min(((0xFF000000 & difficultyBits) >>> 24), 31-3);
        int target = (0x00FFFFFF & difficultyBits);

        for(int i=0;i<shift;i++) {
            if(hash[i] != 0x00) {
                return false;
            }
        }

        int value = 0;
        value |= hash[shift] & 0xFF;
        value <<= 8;
        value |= hash[shift+1] & 0xFF;
        value <<= 8;
        value |= hash[shift+2] & 0xFF;
        //value <<= 8;
        //value |= hash[shift+3] & 0xFF;

        //long value = Utils.unsignedIntToLong(hash, shift);
        return value < target;

    }

    public static HashCode computeHash(long index, HashCode previousHash, long timestamp, int difficultyBits, long nunce, byte[] data) {
        Hasher hasher = HASH_FUNCTION.newHasher();
        hasher.putLong(index);
        if(index != 0) {
            hasher.putBytes(previousHash.asBytes());
        }
        hasher.putLong(timestamp);
        hasher.putInt(difficultyBits);
        hasher.putLong(nunce);
        hasher.putBytes(data);
        return hasher.hash();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || obj.getClass() != Block.class) {
            return false;
        }

        Block other = (Block) obj;
        return this.hash.equals(other.hash);
    }

    @Override
    public int hashCode() {
        return this.hash.hashCode();
    }

    @Override
    public String toString() {
        String hexValue = hash.toString();
        return String.format("%07d: %s", index, hexValue.substring(hexValue.length()-5));
    }
}
