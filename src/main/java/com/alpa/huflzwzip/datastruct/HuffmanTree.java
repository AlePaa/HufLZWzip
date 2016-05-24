package com.alpa.huflzwzip.datastruct;

public class HuffmanTree implements Comparable<HuffmanTree> {

    private static int symbol;
    private int freq;

    private HuffmanTree leftChild, rightChild;

    // Leaf constructor
    HuffmanTree(int s, int f) {
        symbol = s;
        freq = f;
    }

    // Branch constructor
    HuffmanTree(HuffmanTree l, HuffmanTree r) {
        leftChild = l;
        rightChild = r;
        freq = leftChild.getFrequency() + rightChild.getFrequency();
    }

    public int getSymbol() {
        return symbol;
    }

    public int getFrequency() {
        return freq;
    }

    public HuffmanTree getRightChild() {
        return rightChild;
    }

    public HuffmanTree getLeftChild() {
        return leftChild;
    }

    public boolean isLeaf() {
        return (leftChild == null && rightChild == null);
    }

    @Override
    public int compareTo(HuffmanTree t) {
        if (t.getFrequency() > freq) {
            return -1;
        } else if (t.getFrequency() < freq) {
            return 1;
        } else {
            return 0;
        }
    }
}
