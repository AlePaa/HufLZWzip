package com.alpa.huflzwzip.datastruct;

public class HuffmanTree implements Comparable<HuffmanTree> {

    private char symbol;
    private int freq;

    private final HuffmanTree leftChild, rightChild;

    // Leaf constructor
    public HuffmanTree(Character s, int f) {
        symbol = s;
        freq = f;
        leftChild = rightChild = null;
    }

    // Branch constructor
    public HuffmanTree(HuffmanTree l, HuffmanTree r) {
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

    public String findEncoding(Character s, String currentString) {

        if (isLeaf()) {
            if (s != symbol) {
                return "";
            } else {
                return currentString;
            }
        } else {
            leftChild.findEncoding(s, currentString + "0");
            rightChild.findEncoding(s, currentString + "1");
        }
        return currentString;
    }


    /*
    Huffman encoding uses a priority queueing in which the least frequent
    symbol is in front of the queue
     */
    @Override
    public int compareTo(HuffmanTree t) {
        if (t.getFrequency() > freq) {
            return 1;
        } else if (t.getFrequency() < freq) {
            return -1;
        } else {
            return 0;
        }
    }
}
