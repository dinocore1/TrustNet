package com.devsmart.trustnet;

import com.google.common.io.BaseEncoding;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.BeforeClass;
import org.junit.Test;

import java.security.KeyPair;
import java.security.Security;

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

}
