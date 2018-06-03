package com.devsmart.trustnet.objects;

import com.devsmart.trustnet.Hashable;
import com.google.common.base.Charsets;
import com.google.common.hash.Hasher;

public class Vote implements Hashable {

    public final Claim claim;
    public final boolean isTrue;
    public final Human voter;

    public Vote(Claim claim, boolean isTrue, Human voter) {
        this.claim = claim;
        this.isTrue = isTrue;
        this.voter = voter;
    }

    @Override
    public void addToHash(Hasher hasher) {
        hasher.putString("vote", Charsets.UTF_8);
        claim.addToHash(hasher);
        hasher.putBoolean(isTrue);
        voter.addToHash(hasher);
    }
}
