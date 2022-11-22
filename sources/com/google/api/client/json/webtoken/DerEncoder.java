package com.google.api.client.json.webtoken;

import com.google.api.client.util.Preconditions;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import java.math.BigInteger;
import java.util.Arrays;

public class DerEncoder {
    private static byte DER_TAG_ASN1_INTEGER = 2;
    private static byte DER_TAG_SIGNATURE_OBJECT = Ev3Constants.Opcode.MOVE8_8;

    static byte[] encode(byte[] signature) {
        boolean z;
        if (signature.length == 64) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkState(z);
        byte[] int1 = new BigInteger(1, Arrays.copyOfRange(signature, 0, 32)).toByteArray();
        byte[] int2 = new BigInteger(1, Arrays.copyOfRange(signature, 32, 64)).toByteArray();
        byte[] der = new byte[(int1.length + 6 + int2.length)];
        der[0] = DER_TAG_SIGNATURE_OBJECT;
        der[1] = (byte) (der.length - 2);
        der[2] = DER_TAG_ASN1_INTEGER;
        der[3] = (byte) int1.length;
        System.arraycopy(int1, 0, der, 4, int1.length);
        int offset = int1.length + 4;
        der[offset] = DER_TAG_ASN1_INTEGER;
        der[offset + 1] = (byte) int2.length;
        System.arraycopy(int2, 0, der, offset + 2, int2.length);
        return der;
    }
}
