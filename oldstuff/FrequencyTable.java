package com.alpa.huflzwzip.huffman.coding;

import java.util.PriorityQueue;
import java.util.Queue;

public final class FrequencyTable {

    private int[] frequencies;

    /**
     * @param freqs the array of frequencies
     * @throws NullPointerException
     */
    public FrequencyTable(int[] freqs) {
        if (freqs == null) {
            throw new NullPointerException();
        }
        if (freqs.length < 2) {
            throw new IllegalArgumentException("At least 2 symbols needed");
        }
        frequencies = freqs.clone();  // Defensive copy 
        for (int x : frequencies) {
            if (x < 0) {
                throw new IllegalArgumentException("Negative frequency");
            }
        }
    }

    /**
     * @return the number of symbols in this frequency table
     */
    public int getSymbolLimit() {
        return frequencies.length;
    }

    /**
     * @param symbol the symbol to query
     * @return the frequency of the specified symbol
     * @throws IllegalArgumentException if the symbol is out of range
     */
    public int get(int symbol) {
        checkSymbol(symbol);
        return frequencies[symbol];
    }

    /**
     * @param symbol the symbol to be modified
     * @param freq new frequency of the symbol
     * @throws IllegalArgumentException
     */
    public void set(int symbol, int freq) {
        checkSymbol(symbol);
        if (freq < 0) {
            throw new IllegalArgumentException("Negative frequency");
        }
        frequencies[symbol] = freq;
    }

    /**
     * @param symbol the symbol to be incremented
     * @throws IllegalArgumentException if the symbol is out of range
     * @throws IllegalStateException
     */
    public void increment(int symbol) {
        checkSymbol(symbol);
        if (frequencies[symbol] == Integer.MAX_VALUE) {
            throw new IllegalStateException("Frequency over limit");
        }
        frequencies[symbol]++;
    }

    private void checkSymbol(int symbol) {
        if (symbol < 0 || symbol >= frequencies.length) {
            throw new IllegalArgumentException("Symbol out of range");
        }
    }

    /**
     * @return an optimal code tree for this frequency table
     */
    public CodeTree buildCodeTree() {
        Queue<NodeWithFrequency> pqueue = new PriorityQueue<>();

        // Add leaves for symbols with non-zero frequency
        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] > 0) {
                pqueue.add(new NodeWithFrequency(new Leaf(i), i, frequencies[i]));
            }
        }

        // Pad with zero-frequency symbols until queue has at least 2 items
        for (int i = 0; i < frequencies.length && pqueue.size() < 2; i++) {
            if (frequencies[i] == 0) {
                pqueue.add(new NodeWithFrequency(new Leaf(i), i, 0));
            }
        }
        if (pqueue.size() < 2) {
            throw new AssertionError();
        }

        // Repeatedly tie together two nodes with the lowest frequency
        while (pqueue.size() > 1) {
            NodeWithFrequency x = pqueue.remove();
            NodeWithFrequency y = pqueue.remove();
            pqueue.add(new NodeWithFrequency(
                    new Branch(x.node, y.node),
                    Math.min(x.lowestSymbol, y.lowestSymbol),
                    x.frequency + y.frequency));
        }

        // Return the remaining node
        return new CodeTree((Branch) pqueue.remove().node, frequencies.length);
    }

    // Helper structure for buildCodeTree()
    private static class NodeWithFrequency implements Comparable<NodeWithFrequency> {

        public final Tree node;
        public final int lowestSymbol;
        public final long frequency;

        public NodeWithFrequency(Tree n, int l, long f) {
            node = n;
            lowestSymbol = l;
            frequency = f;
        }

        @Override
        public int compareTo(NodeWithFrequency o) {
            if (frequency < o.frequency) {
                return -1;
            } else if (frequency > o.frequency) {
                return 1;
            } else if (lowestSymbol < o.lowestSymbol) {
                return -1;
            } else if (lowestSymbol > o.lowestSymbol) {
                return 1;
            } else {
                return 0;
            }
        }

    }

}
