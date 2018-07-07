package com.devsmart.trustnet;

import com.devsmart.trustnet.objects.Actor;
import com.devsmart.trustnet.objects.Human;
import com.google.common.hash.Hasher;

public class TrustTransaction implements Hashable {


    public final Human src;
    public final Human dest;
    public final Trust amount;


    @Override
    public void addToHash(Hasher hasher) {

    }
}
