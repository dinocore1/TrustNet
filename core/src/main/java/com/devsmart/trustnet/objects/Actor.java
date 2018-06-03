package com.devsmart.trustnet.objects;

import com.devsmart.trustnet.Hashable;
import com.google.common.base.Charsets;
import com.google.common.hash.Hasher;
import com.google.common.io.BaseEncoding;

import java.security.PublicKey;

public class Actor implements Hashable {

    public final PublicKey publicKey;

    public Actor(PublicKey key) {
        this.publicKey = key;
    }

    @Override
    public String toString() {
        return "Actor: " + BaseEncoding.base64().encode(publicKey.getEncoded());
    }

    @Override
    public void addToHash(Hasher hasher) {
        hasher.putString("actor", Charsets.UTF_8);
        hasher.putBytes(publicKey.getEncoded());
    }
}
