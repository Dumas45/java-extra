package moj.paket.extra;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * IO Utils
 * @author dumas45
 */
public class IO {
    public static byte[] getBytes(InputStream in) throws IOException {
        final int INIT_SIZE = 1 << 10;

        byte[] buffer = new byte[INIT_SIZE];
        int position = 0;

        byte[] bytes = new byte[INIT_SIZE];
        int read;

        while ((read = in.read(bytes)) != -1) {
            if (read > 0 && read <= buffer.length - position) {
                System.arraycopy(bytes, 0, buffer, position, read);
                position += read;
            } else {
                int cap = (cap = buffer.length) + (cap < 1 << 20 ? cap : cap / 5);
                if (cap < position + read) {
                    throw new RuntimeException("Capacity Overflow");
                }
                byte[] temp = new byte[cap];
                System.arraycopy(buffer, 0, temp, 0, position);
                buffer = temp;
                System.arraycopy(bytes, 0, buffer, position, read);
                position += read;
            }
        }

        return Arrays.copyOf(buffer, position);
    }
}
