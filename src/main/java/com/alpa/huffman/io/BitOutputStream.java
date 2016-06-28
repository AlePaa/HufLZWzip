package com.alpa.huffman.io;

import java.io.IOException;
import java.io.OutputStream;

public final class BitOutputStream {

    private OutputStream output;

    private int currentByte;

    private int bitsInCurrentByte;

    /**
     * @param out
     * @throws NullPointerException if the output stream is {@code null}
     */
    public BitOutputStream(OutputStream out) {
        if (out == null) {
            throw new NullPointerException();
        }
        output = out;
        currentByte = 0;
        bitsInCurrentByte = 0;
    }

    /**
     * @param currentBit
     * @throws IOException
     */
    public void write(int currentBit) throws IOException {
        if (currentBit != 0 && currentBit != 1) {
            throw new IllegalArgumentException("Non-bit argument");
        }
        currentByte = (currentByte << 1) | currentBit;
        bitsInCurrentByte++;
        if (bitsInCurrentByte == 8) {
            output.write(currentByte);
            currentByte = 0;
            bitsInCurrentByte = 0;
        }
    }

    /**
     * @throws IOException
     */
    public void close() throws IOException {
        while (bitsInCurrentByte != 0) {
            write(0);
        }
        output.close();
    }

}
