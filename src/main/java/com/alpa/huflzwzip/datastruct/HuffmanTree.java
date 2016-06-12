package com.alpa.huflzwzip.datastruct;

public class HuffmanTree implements Comparable<HuffmanTree> {

    public int freq;
    
    /*
    Huffman encoding uses a priority queueing in which the least frequent
    symbol is in front of the queue
     */
    @Override
    public int compareTo(HuffmanTree t) {
        return freq - t.freq;
    }
}
