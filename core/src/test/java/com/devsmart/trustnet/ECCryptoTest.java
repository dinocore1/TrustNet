package com.devsmart.trustnet;

import com.google.common.base.Charsets;
import com.google.common.io.BaseEncoding;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.BeforeClass;
import org.junit.Test;

import java.security.KeyPair;
import java.security.Security;
import java.security.Signature;

import static org.junit.Assert.*;

public class ECCryptoTest {

    @BeforeClass
    public static void setup() {
        Security.addProvider(new BouncyCastleProvider());
    }

    @Test
    public void testECKeyPar() throws Exception {

        KeyPair kp1 = AsymetricKeyScheme.genKey("password");
        KeyPair kp2 = AsymetricKeyScheme.genKey("password");
        assertArrayEquals(kp1.getPublic().getEncoded(), kp2.getPublic().getEncoded());

        KeyPair kp3 = AsymetricKeyScheme.genKey("baddog");
        assertNotEquals(BaseEncoding.base64().encode(kp1.getPublic().getEncoded()), BaseEncoding.base64().encode(kp3.getPublic().getEncoded()));

    }

    @Test
    public void testSignature() throws Exception {

        KeyPair kp = AsymetricKeyScheme.genKey("password");

        byte[] message = "this is a message. its in plain text".getBytes(Charsets.UTF_8);
        byte[] signature;

        {
            Signature sig = AsymetricKeyScheme.initSignature(kp.getPrivate());
            sig.update(message);
            signature = sig.sign();
        }


        Signature verify = AsymetricKeyScheme.initVerify(kp.getPublic());
        verify.update(message);
        boolean isVerify = verify.verify(signature);

        assertTrue(isVerify);

    }

    @Test
    public void testSignatureNegitive() throws Exception {

        KeyPair kp = AsymetricKeyScheme.genKey("password");

        byte[] message = "this is a message. its in plain text".getBytes(Charsets.UTF_8);
        byte[] signature;

        {
            Signature sig = AsymetricKeyScheme.initSignature(kp.getPrivate());
            sig.update(message);
            signature = sig.sign();
        }


        message[3] = '3';

        Signature verify = AsymetricKeyScheme.initVerify(kp.getPublic());
        verify.update(message);
        boolean isVerify = verify.verify(signature);

        assertFalse(isVerify);
    }

}
