package com.alpa.huffman.io;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public final class BitInputStream {

    private InputStream input;

    private int currentByte;

    private int bitsLeft;

    /**
     * @param in the byte input stream
     * @throws NullPointerException
     */
    public BitInputStream(InputStream in) {
        if (in == null) {
            throw new NullPointerException();
        }
        input = in;
        currentByte = 0;
        bitsLeft = 0;
    }

    /**
     * @return 1, 0 or -1
     * @throws IOException
     */
    public int read() throws IOException {
        if (currentByte == -1) {
            return -1;
        }
        if (bitsLeft == 0) {
            currentByte = input.read();
            if (currentByte == -1) {
                return -1;
            }
            bitsLeft = 8;
        }
        if (bitsLeft <= 0) {
            throw new AssertionError();
        }
        bitsLeft--;
        return (currentByte >>> bitsLeft) & 1;
    }

    /**
     * @return either 1 or 0
     * @throws IOException
     * @throws EOFException
     */
    public int readNoEof() throws IOException {
        int result = read();
        if (result != -1) {
            return result;
        } else {
            throw new EOFException();
        }
    }

    /**
     * Close the stream
     * @throws IOException
     */
    public void close() throws IOException {
        input.close();
        currentByte = -1;
        bitsLeft = 0;
    }
}
