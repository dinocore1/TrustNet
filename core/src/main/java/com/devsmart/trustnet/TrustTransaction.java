package com.devsmart.trustnet;

import com.devsmart.trustnet.objects.Human;
import com.devsmart.ubjson.UBObject;
import com.devsmart.ubjson.UBValueFactory;
import com.devsmart.ubjson.UBWriter;
import com.google.common.base.Charsets;
import com.google.common.hash.Hasher;
import com.google.common.io.BaseEncoding;

import java.io.ByteArrayOutputStream;
import java.security.PrivateKey;
import java.security.Signature;

public class TrustTransaction implements Hashable {


    public final Human src;
    public final Human dest;
    public final Trust amount;
    public byte[] signature;


    public TrustTransaction(Human src, Human dest, Trust amount, byte[] signature) {
        this.src = src;
        this.dest = dest;
        this.amount = amount;
        this.signature = signature;
    }

    private UBObject toObj() {
        UBObject retval = UBValueFactory.createObject();
        retval.put("src", UBValueFactory.createString(src.publicKey.getEncoded()));
        retval.put("dst", UBValueFactory.createString(dest.publicKey.getEncoded()));
        retval.put("amt", UBValueFactory.createInt(amount.intValue));
        retval.put("sig", UBValueFactory.createArrayOrNull(signature));
        return retval;
    }

    public byte[] sign(PrivateKey privateKey) throws Exception {

        Signature sig = AsymetricKeyScheme.initSignature(privateKey);

        StringBuilder str = new StringBuilder();
        str.append("send ");
        str.append(amount.toString());
        str.append(" from ");
        str.append(BaseEncoding.base64().encode(src.publicKey.getEncoded()));
        str.append(" to ");
        str.append(BaseEncoding.base64().encode(dest.publicKey.getEncoded()));

        sig.update(str.toString().getBytes(Charsets.UTF_8));

        /*
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        UBWriter writer = new UBWriter(out);
        writer.writeObject(toObj());
        writer.close();
        byte[] msg = out.toByteArray();


        sig.update(msg);
        */
        return sig.sign();
    }

    public boolean isSignatureValid() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        UBWriter writer = new UBWriter(out);
        writer.writeObject(toObj());
        writer.close();
        byte[] msg = out.toByteArray();

        Signature sig = AsymetricKeyScheme.initVerify(src.publicKey);
        sig.update(msg);
        return sig.verify(signature);
    }


    @Override
    public void addToHash(Hasher hasher) {
        src.addToHash(hasher);
        dest.addToHash(hasher);
        amount.addToHash(hasher);
        hasher.putBytes(signature);
    }
}
