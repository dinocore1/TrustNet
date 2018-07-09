package com.devsmart.trustnet;

import com.devsmart.trustnet.objects.Human;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.BeforeClass;
import org.junit.Test;

import java.security.KeyPair;
import java.security.Security;

public class TrustTransactionTest {

    @BeforeClass
    public static void setup() {
        Security.addProvider(new BouncyCastleProvider());
    }

    @Test
    public void testSignature() throws Exception {

        KeyPair keyPair = AsymetricKeyScheme.genKey("password");

        Human src = new Human("Paul", keyPair.getPublic());
        Human dest = new Human("Mel", AsymetricKeyScheme.genKey("password2").getPublic());

        TrustTransaction t = new TrustTransaction(src, dest, Trust.ONE_TRUST, null);
        t.signature = t.sign(keyPair.getPrivate());
    }
}
