package com.devsmart.trustnet;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;

public class Block {

    public static final HashFunction HASH_FUNCTION = Hashing.sha256();

    public final long index;
    public final HashCode previousHash;
    public final long timestamp;
    public final byte[] data;
    public final HashCode hash;

    public Block(long index, HashCode previousHash, long timestamp, byte[] data, HashCode hash) {
        this.index = index;
        this.previousHash = previousHash;
        this.timestamp = timestamp;
        this.data = data;
        this.hash = hash;
    }

    public static HashCode computeHash(long index, HashCode previousHash, long timestamp, byte[] data) {
        Hasher hasher = HASH_FUNCTION.newHasher();
        hasher.putLong(index);
        hasher.putBytes(previousHash.asBytes());
        hasher.putLong(timestamp);
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
        return String.format("%010d: %s", index, hash.toString().substring(0, 5));
    }
}
