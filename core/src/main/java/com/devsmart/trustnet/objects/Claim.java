package com.devsmart.trustnet.objects;

import com.devsmart.trustnet.Hashable;
import com.google.common.base.Charsets;
import com.google.common.hash.Hasher;

public class Claim implements Hashable {

    public final Actor subject;
    public final String verb;
    public final String object;
    public final long timestamp;

    public Claim(Actor subject, String verb, String object, long timestamp) {
        this.subject = subject;
        this.verb = verb;
        this.object = object;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", subject, verb, object);
    }

    @Override
    public void addToHash(Hasher hasher) {
        hasher.putString("claim", Charsets.UTF_8);
        subject.addToHash(hasher);
        hasher.putString(verb, Charsets.UTF_8);
        hasher.putString(object, Charsets.UTF_8);
        hasher.putLong(timestamp);
    }
}
