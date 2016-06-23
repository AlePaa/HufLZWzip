package com.alpa.huflzwzip.huffman.coding;

import com.alpa.huflzwzip.huffman.io.BitInputStream;
import java.io.IOException;

public final class HuffmanDecoder {

    private BitInputStream input;

    public CodeTree codeTree;

    /**
     * @param i
     * @throws NullPointerException
     */
    public HuffmanDecoder(BitInputStream i) {
        if (i == null) {
            throw new NullPointerException();
        }
        input = i;
    }

    /**
     * @return the next symbol read from the stream
     * @throws IOException
     * @throws NullPointerException
     */
    public int read() throws IOException {
        System.out.println("reading..");
        if (codeTree == null) {
            throw new NullPointerException("Code tree is null");
        }

        Branch currentNode = codeTree.root;
        while (true) {
            int temp = input.readNoEof();
            Tree nextNode;
            switch (temp) {
                case 0:
                    nextNode = currentNode.left;
                    break;
                case 1:
                    nextNode = currentNode.right;
                    break;
                default:
                    throw new AssertionError("Invalid value from readNoEof()");
            }

            if (nextNode instanceof Leaf) {
                return ((Leaf) nextNode).symbol;
            } else if (nextNode instanceof Branch) {
                currentNode = (Branch) nextNode;
            } else {
                throw new AssertionError("Illegal node type");
            }
        }
    }

}
