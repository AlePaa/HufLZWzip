package com.alpa.huffman.coding;

/**
 * An inner node in a Huffman tree
 */
public final class Branch implements Tree {

    public final Tree left;

    public final Tree right;

    public Branch(Tree l, Tree r) {
        if (l == null || r == null) {
            throw new NullPointerException();
        }
        left = l;
        right = r;
    }

    @Override
    public boolean isLeaf() {
        return false;
    }
}
