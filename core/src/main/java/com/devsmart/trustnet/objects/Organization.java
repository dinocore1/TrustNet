package com.devsmart.trustnet.objects;

import com.devsmart.trustnet.Hashable;
import com.google.common.base.Charsets;
import com.google.common.hash.Hasher;

public class Organization implements Hashable {

    public final String name;

    public Organization(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "org: " + name;
    }

    @Override
    public void addToHash(Hasher hasher) {
        hasher.putString("org", Charsets.UTF_8);
        hasher.putString(name, Charsets.UTF_8);
    }
}
