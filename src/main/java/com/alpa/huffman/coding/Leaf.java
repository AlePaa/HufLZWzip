package com.alpa.huffman.coding;

/**
 * A leaf node in a Huffman tree
 */
public final class Leaf implements Tree {

    public final int symbol;

    public Leaf(int s) {
        if (s < 0) {
            throw new IllegalArgumentException("Symbol value must not be a negative value");
        }
        symbol = s;
    }

    @Override
    public boolean isLeaf() {
        return true;
    }
}
