package com.devsmart.trustnet;

import com.google.common.base.Charsets;
import com.google.common.hash.Hasher;

public class Human implements Hashable {

    public final String firstName;
    public final String lastName;

    public Human(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "human: " + firstName + " " + lastName;
    }

    @Override
    public int hashCode() {
        return firstName.hashCode() ^ lastName.hashCode();
    }


    @Override
    public void addToHash(Hasher hasher) {
        hasher.putString(firstName, Charsets.UTF_8);
        hasher.putString(lastName, Charsets.UTF_8);
    }
}
