package com.alpa.huflzwzip.algo;

import com.alpa.huflzwzip.datastruct.HuffmanBranch;
import com.alpa.huflzwzip.datastruct.HuffmanLeaf;
import com.alpa.huflzwzip.datastruct.HuffmanTree;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
        HuffmanTree tree = buildTree(frequencies);
        
        
    }

    @Override
    public void decode(BufferedInputStream input
    ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public HuffmanTree buildTree(HashMap<Character, Integer> frequencies) {

        // Create the leaf nodes and add them to the queue
        PriorityQueue<HuffmanTree> leafQueue = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
            leafQueue.add(new HuffmanLeaf(entry.getKey(), entry.getValue()));
        }

        // Build the Huffman tree from the leaves
        PriorityQueue<HuffmanTree> assistQueue = new PriorityQueue<>();

        /*
        Choose two of the lightest trees from the queues and form
        a new branch with the two as leaves
         */
        while ((leafQueue.size() + assistQueue.size()) > 1) {
            HuffmanTree a;
            HuffmanTree b;

            // Choose the lightest trees for leaves a and b.
            // Placeholder until a more intelligent way of handling this is found.
            if (leafQueue.peek().freq <= assistQueue.peek().freq) {
                a = leafQueue.remove();
            } else {
                a = assistQueue.remove();
            }

            if (leafQueue.peek().freq <= assistQueue.peek().freq) {
                b = leafQueue.remove();
            } else {
                b = assistQueue.remove();
            }

            HuffmanTree branch = new HuffmanBranch(a, b);
            assistQueue.add(branch);
        }
        // Last node is the root node
        return assistQueue.remove();
    }
}
