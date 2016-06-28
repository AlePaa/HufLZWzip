package com.alpa.huffman.coding;

import com.alpa.util.PriorityQueue;

/**
 * Keeps track of the frequencies of the symbols in input data
 */
public final class FrequencyTable {

    private int[] frequencies;

    /**
     * Constructs a FrequencyTable from an integer array of non-negative values
     *
     * @param freqs the array of frequencies
     * @throws NullPointerException
     */
    public FrequencyTable(int[] freqs) {
        if (freqs == null) {
            throw new NullPointerException();
        }
        if (freqs.length < 2) {
            throw new IllegalArgumentException("A minimum of 2 symbols needed");
        }
        frequencies = freqs.clone();  // Defensive copy 
        // Check that the array does not contain negative values
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
            throw new IllegalArgumentException("Negative frequencies not allowed");
        }
        frequencies[symbol] = freq;
    }

    /**
     * Increments the frequency of the symbol by one
     * @param symbol the symbol to be incremented
     * @throws IllegalArgumentException if the symbol is out of range
     * @throws IllegalStateException If if trying to increment Integer.MAX_VALUE
     */
    public void increment(int symbol) {
        checkSymbol(symbol);
        // Protect from integer overflow by checking that the highest possible integer
        // does not get incremented
        if (frequencies[symbol] == Integer.MAX_VALUE) {
            throw new IllegalStateException("Frequency over limit");
        }
        frequencies[symbol]++;
    }

    /**
     * Checks that the symbol is not out of array bounds
     *
     * @param symbol the array index to check
     */
    private void checkSymbol(int symbol) {
        if (symbol < 0 || symbol >= frequencies.length) {
            throw new IllegalArgumentException("Symbol out of range");
        }
    }

    /**
     * @return an optimal code tree for this frequency table
     */
    public CodeTree buildCodeTree() {
        PriorityQueue<FNode> pqueue = new PriorityQueue<>();

        // Add a leaf if the frequency of the symbol is not zero
        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] > 0) {
                pqueue.insert(new FNode(new Leaf(i), i, frequencies[i]));
            }
        }

        for (int i = 0; i < frequencies.length && pqueue.size() < 2; i++) {
            if (frequencies[i] == 0) {
                pqueue.insert(new FNode(new Leaf(i), i, 0));
            }
        }
        if (pqueue.size() < 2) {
            throw new AssertionError();
        }

        // Combine nodes until only the root remains
        while (pqueue.size() > 1) {
            FNode x = pqueue.delMin();
            FNode y = pqueue.delMin();
            pqueue.insert(new FNode(
                    new Branch(x.node, y.node),
                    Math.min(x.lowestSymbol, y.lowestSymbol),
                    x.frequency + y.frequency));
        }

        // Return the remaining node
        return new CodeTree((Branch) pqueue.delMin().node, frequencies.length);
    }

    // Helper structure for buildCodeTree()
    private static class FNode implements Comparable<FNode> {

        public final Tree node;
        public final int lowestSymbol;
        public final long frequency;

        public FNode(Tree n, int l, long f) {
            node = n;
            lowestSymbol = l;
            frequency = f;
        }

        @Override
        public int compareTo(FNode o) {
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
