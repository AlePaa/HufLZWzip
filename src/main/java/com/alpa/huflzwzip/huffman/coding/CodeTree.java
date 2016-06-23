package com.alpa.huflzwzip.huffman.coding;

import com.alpa.huflzwzip.datastruct.ArrayList;
public final class CodeTree {
	
	/* Fields and constructor */
	
	/**
	 * The root node of this code tree (not {@code null}).
	 */
	public final Branch root;
	
	// Stores the code for each symbol, or null if the symbol has no code.
	// For example, if symbol 5 has code 10011, then codes.get(5) is the list [1,0,0,1,1].
	private ArrayList<ArrayList<Integer>> codes;
	
	
	
	/**
	 * Constructs a code tree from the specified tree of nodes and specified symbol limit.
	 * Each symbol in the tree must have value strictly less than the symbol limit.
	 * @param root the root of the tree
	 * @param symbolLimit the symbol limit
	 * @throws NullPointerException if tree root is {@code null}
	 * @throws IllegalArgumentException if the symbol limit is less than 2, any symbol in the tree has
	 * a value greater or equal to the symbol limit, or a symbol value appears more than once in the tree
	 */
	public CodeTree(Branch root, int symbolLimit) {
		if (root == null) {
                    throw new NullPointerException();
                }
		if (symbolLimit < 2) {
                    throw new IllegalArgumentException("At least 2 symbols needed");
                }
		
		this.root = root;
		codes = new ArrayList<>();  // Initially all null
		for (int i = 0; i < symbolLimit; i++) {
                    codes.add(null);
                }
                System.out.println("Commence building of code list");
		buildCodeList(root, new ArrayList<>());  // Fill 'codes' with appropriate data
	}
	
	
	// Recursive helper function for the constructor
	private void buildCodeList(Tree node, ArrayList<Integer> prefix) {
		if (node instanceof Branch) {
                    System.out.println("Is a branch");
			Branch internalNode = (Branch)node;
			
			prefix.add(0);
			buildCodeList(internalNode.left , prefix);
			prefix.remove(prefix.size() - 1);
			
			prefix.add(1);
			buildCodeList(internalNode.right, prefix);
			prefix.remove(prefix.size() - 1);
			
		} else if (node instanceof Leaf) {
                    System.out.println("Is a leaf");
			Leaf leaf = (Leaf)node;
                        System.out.println("Leaf symbol "+leaf.symbol);
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
	 * Returns the Huffman code for the specified symbol, which is a list of 0s and 1s.
	 * @param symbol the symbol to query
	 * @return a list representation of the encoding
	 * @throws IllegalArgumentException if the symbol is negative, or no
	 * Huffman code exists for it (e.g. because it had a zero frequency)
	 */
	public ArrayList<Integer> getCode(int symbol) {
		if (symbol < 0) {
                    throw new IllegalArgumentException("Illegal symbol");
                } else if (codes.get(symbol) == null) {
                    throw new IllegalArgumentException("No code for given symbol");
                } else {
                    System.out.println("Trying to print symbol..");
                    System.out.println(codes.get(symbol));
                    return codes.get(symbol);
                }
	}
		
}
