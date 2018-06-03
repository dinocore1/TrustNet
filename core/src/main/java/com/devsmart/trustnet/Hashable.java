package com.devsmart.trustnet;

import com.google.common.hash.Hasher;

public interface Hashable {

    void addToHash(Hasher hasher);
}
