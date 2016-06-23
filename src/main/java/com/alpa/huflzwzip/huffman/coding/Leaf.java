package com.alpa.huflzwzip.huffman.coding;

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
