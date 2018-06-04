package com.devsmart.trustnet;

import com.google.common.base.Charsets;

import java.security.*;

public class AsymetricKeyScheme {

    public static KeyPair genKey(String password) throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("ECIES");
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        sr.setSeed(password.getBytes(Charsets.UTF_8));
        kpg.initialize(256, sr);
        return kpg.generateKeyPair();
    }

    public static Signature initSignature(PrivateKey key) throws Exception {
        Signature sig = Signature.getInstance("ECDSA");
        sig.initSign(key);
        return sig;
    }

    public static Signature initVerify(PublicKey key) throws Exception {
        Signature sig = Signature.getInstance("ECDSA");
        sig.initVerify(key);
        return sig;
    }

    public static PublicKey decodePublicKey(String s) {
        return null;
    }

}
