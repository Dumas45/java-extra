package moj.paket.extra.test;

import moj.paket.extra.IO;
import org.junit.jupiter.api.Assertions;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author dumas45
 */
class IOTest {

    @org.junit.jupiter.api.Test
    void getBytes_InputStream() throws Exception {
        byte[] bytes;
        assertArrayEquals(bytes = getPseudoRandomBytes(0), IO.getBytes(new ByteArrayInputStream(bytes)));

        assertArrayEquals(bytes = getPseudoRandomBytes(1023), IO.getBytes(new ByteArrayInputStream(bytes)));
        assertArrayEquals(bytes = getPseudoRandomBytes(1024), IO.getBytes(new ByteArrayInputStream(bytes)));
        assertArrayEquals(bytes = getPseudoRandomBytes(1025), IO.getBytes(new ByteArrayInputStream(bytes)));

        assertArrayEquals(bytes = getPseudoRandomBytes(8191), IO.getBytes(new ByteArrayInputStream(bytes)));
        assertArrayEquals(bytes = getPseudoRandomBytes(8192), IO.getBytes(new ByteArrayInputStream(bytes)));
        assertArrayEquals(bytes = getPseudoRandomBytes(8193), IO.getBytes(new ByteArrayInputStream(bytes)));

        int a = 1;
        int b = 1;
        while (a < 100000) {
            assertArrayEquals(bytes = getPseudoRandomBytes(a), IO.getBytes(new ByteArrayInputStream(bytes)));
            int t = a;
            a = a + b;
            b = t;
        }
    }

    private byte[] getPseudoRandomBytes(final int length) {
        if (length < 0 || length > 50_000_000) throw new IllegalArgumentException();
        byte[] bytes = new byte[length];
        int inc = length;
        while (inc % 4 == 0 || (inc - 2) % 4 == 0 || (inc - 1) % 54 == 0) inc++;
        for (int x = 19 * inc % 1031, i = 0; i < length; i++, x = Math.abs((17 * x + inc) % 37)) {
            bytes[i] = (byte) (x % 128);
        }
        return bytes;
    }
}