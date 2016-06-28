package com.alpa.huffman.coding;

import com.alpa.util.ArrayList;

/**
 * The code tree for Huffman coding
 */
public final class CodeTree {

    public final Branch root;
    private ArrayList<ArrayList<Integer>> codes;

    /**
     * @param root the root of the tree
     * @param symbolLimit the symbol limit
     * @throws NullPointerException
     * @throws IllegalArgumentException
     *
     */
    public CodeTree(Branch root, int symbolLimit) {
        if (root == null) {
            throw new NullPointerException();
        }
        if (symbolLimit < 2) {
            throw new IllegalArgumentException("At least 2 symbols needed");
        }

        this.root = root;
        codes = new ArrayList<>();  // Initialize the entire list as null
        for (int i = 0; i < symbolLimit; i++) {
            codes.add(null);
        }
        buildCodeList(root, new ArrayList<>());
    }

    // Recursive helper function for the constructor
    private void buildCodeList(Tree node, ArrayList<Integer> prefix) {
        if (node instanceof Branch) {
            Branch internalNode = (Branch) node;

            prefix.add(0);
            buildCodeList(internalNode.left, prefix);
            prefix.remove(prefix.size() - 1);

            prefix.add(1);
            buildCodeList(internalNode.right, prefix);
            prefix.remove(prefix.size() - 1);

        } else if (node instanceof Leaf) {
            Leaf leaf = (Leaf) node;
            if (leaf.symbol >= codes.size()) {
                throw new IllegalArgumentException("Symbol exceeds symbol limit");
            }
            if (codes.get(leaf.symbol) != null) {
                throw new IllegalArgumentException("Symbol has more than one code");
            }
            codes.set(leaf.symbol, new ArrayList<>(prefix));

        } else {
            throw new AssertionError("Illegal node type");
        }
    }

    /**
     * @param symbol the symbol to query
     * @return a list representation of the encoding
     * @throws IllegalArgumentException
     */
    public ArrayList<Integer> getCode(int symbol) {
        if (symbol < 0) {
            throw new IllegalArgumentException("Illegal symbol");
        } else if (codes.get(symbol) == null) {
            throw new IllegalArgumentException("No code for given symbol");
        } else {
            return codes.get(symbol);
        }
    }
}
