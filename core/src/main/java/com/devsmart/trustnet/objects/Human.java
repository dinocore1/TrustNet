package com.devsmart.trustnet.objects;

import com.devsmart.trustnet.Hashable;
import com.google.common.base.Charsets;
import com.google.common.hash.Hasher;

public class Human implements Hashable {

    public final String name;

    public Human(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "human: " + name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }


    @Override
    public void addToHash(Hasher hasher) {
        hasher.putString("human", Charsets.UTF_8);
        hasher.putString(name, Charsets.UTF_8);
    }
}
