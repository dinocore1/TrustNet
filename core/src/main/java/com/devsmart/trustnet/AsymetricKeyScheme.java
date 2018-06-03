package com.devsmart.trustnet;

import com.google.common.base.Charsets;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.SecureRandom;

public class AsymetricKeyScheme {

    public static KeyPair genKey(String password) throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("ECIES");
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        sr.setSeed(password.getBytes(Charsets.UTF_8));
        kpg.initialize(256, sr);
        return kpg.generateKeyPair();
    }

    

    public static PublicKey decodePublicKey(String s) {
        return null;
    }

}
