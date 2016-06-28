package com.alpa.huffman.coding;

import com.alpa.huffman.io.BitOutputStream;
import com.alpa.util.ArrayList;
import java.io.IOException;

/**
 * Writes encodings one bit at a time to the output stream
 */
public final class HuffmanEncoder {

    private BitOutputStream output;

    public CodeTree codeTree;

    /**
     * @param out
     * @throws NullPointerException
     */
    public HuffmanEncoder(BitOutputStream out) {
        if (out == null) {
            throw new NullPointerException();
        }
        output = out;
    }

    /**
     * @param symbol the symbol to be encoded
     * @throws IOException
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public void write(int symbol) throws IOException {
        if (codeTree == null) {
            throw new NullPointerException("Code tree is null");
        }
        ArrayList<Integer> bits = codeTree.getCode(symbol);

        for (int b : bits) {
            output.write(b);
        }
    }

}
