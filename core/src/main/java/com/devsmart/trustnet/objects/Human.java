package com.devsmart.trustnet.objects;

import com.google.common.base.Charsets;
import com.google.common.hash.Hasher;
import com.google.common.io.BaseEncoding;

import java.security.PublicKey;

public class Human extends Actor {

    public final String name;

    public Human(String name, PublicKey publicKey) {
        super(publicKey);
        this.name = name;
    }

    @Override
    public String toString() {
        final int hash = publicKey.hashCode();
        String id = Integer.toHexString(hash);
        return "human: " + name + " : " + id;
    }

    @Override
    public int hashCode() {
        return super.hashCode() ^ name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || obj.getClass() != Human.class) {
            return false;
        }

        Human other = (Human) obj;
        return this.name.equals(other.name)
                && this.publicKey.equals(other.publicKey);
    }

    @Override
    public void addToHash(Hasher hasher) {
        hasher.putString("human", Charsets.UTF_8);
        hasher.putString(name, Charsets.UTF_8);
        super.addToHash(hasher);
    }
}
