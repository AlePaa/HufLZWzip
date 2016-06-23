package com.alpa.huflzwzip.huffman.coding;

import com.alpa.huflzwzip.huffman.io.BitOutputStream;
import java.io.IOException;
import com.alpa.huflzwzip.datastruct.ArrayList;

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
            System.out.println("writing " + b);
            output.write(b);
        }
    }

}
