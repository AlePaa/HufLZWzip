package com.alpa.huflzwzip.algo;

import com.alpa.huflzwzip.datastruct.HuffmanTree;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Huffman implements Compressor {

    @Override
    public void encode(BufferedInputStream input) throws IOException {
        HashMap<Character, Integer> frequencies = new HashMap();
        // Parse input data and count the frequencies of distinct byte values
        while (input.read() != -1) {
            Character current = (char) input.read();

            if (frequencies.get(current) != null) {
                int count = frequencies.get(current);
                frequencies.put(current, count + 1);
            } else {
                frequencies.put(current, 1);
            }
        }

        // Create the leaf nodes and add them to the queue
        PriorityQueue<HuffmanTree> leafQueue = new PriorityQueue();
    }

    @Override
    public void decode(BufferedInputStream input) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
