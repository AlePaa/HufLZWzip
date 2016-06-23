package com.alpa.huflzwzip.huffman.coding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class CanonicalCode {

    private int[] codeLengths;

    /**
     * @param codeL an array of the symbol code lengths
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public CanonicalCode(int[] codeL) {
        if (codeL == null) {
            throw new NullPointerException();
        }
        if (codeL.length < 2) {
            throw new IllegalArgumentException("Minimum of 2 symbols needed");
        }
        for (int c : codeL) {
            if (c < 0) {
                throw new IllegalArgumentException("Code length must not be less than zero");
            }
        }
        codeLengths = codeL.clone();
        Arrays.sort(codeLengths);
        int currentLevel = codeLengths[codeLengths.length - 1];
        int nodeAtLevel = 0;
        for (int i = codeLengths.length - 1; i >= 0 && codeLengths[i] > 0; i--) {
            int c = codeLengths[i];
            while (c < currentLevel) {
                if (nodeAtLevel % 2 != 0) {
                    throw new IllegalArgumentException();
                }
                nodeAtLevel /= 2;
                currentLevel--;
            }
            nodeAtLevel++;
        }
        while (currentLevel > 0) {
            if (nodeAtLevel % 2 != 0) {
                throw new IllegalArgumentException();
            }
            nodeAtLevel /= 2;
            currentLevel--;
        }
        if (nodeAtLevel < 1) {
            throw new IllegalArgumentException();
        }
        if (nodeAtLevel > 1) {
            throw new IllegalArgumentException();
        }

        System.arraycopy(codeL, 0, codeLengths, 0, codeL.length);
    }

    /**
     * @param tree the input code tree
     * @param symbolLimit
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public CanonicalCode(CodeTree tree, int symbolLimit) {
        if (tree == null) {
            throw new NullPointerException();
        }
        if (symbolLimit < 2) {
            throw new IllegalArgumentException("A minimum of 2 symbols needed");
        }
        codeLengths = new int[symbolLimit];
        buildCodeLengths(tree.root, 0);
    }

    private void buildCodeLengths(Tree tree, int depth) {
        if (tree instanceof Branch) {
            Branch branch = (Branch) tree;
            buildCodeLengths(branch.left, depth + 1);
            buildCodeLengths(branch.right, depth + 1);
        } else if (tree instanceof Leaf) {
            int symbol = ((Leaf) tree).symbol;
            if (codeLengths[symbol] != 0) {
                throw new AssertionError();
            }
            if (symbol >= codeLengths.length) {
                throw new IllegalArgumentException("Maximum number of symbols reached");
            }
            codeLengths[symbol] = depth;
        } else {
            throw new AssertionError();
        }
    }

    /**
     * @return the symbol limit
     */
    public int getSymbolLimit() {
        return codeLengths.length;
    }

    /**
     * @param symbol
     * @return the symbol's code length
     * @throws IllegalArgumentException
     */
    public int getCodeLength(int symbol) {
        if (symbol < 0 || symbol >= codeLengths.length) {
            throw new IllegalArgumentException();
        }
        return codeLengths[symbol];
    }

    /**
     * @return the converted Canonical Huffman code tree
     */
    public CodeTree toCodeTree() {
        List<Tree> trees = new ArrayList<>();
        for (int i = max(codeLengths); i >= 0; i--) {
            if (trees.size() % 2 != 0) {
                throw new AssertionError();
            }
            List<Tree> newNodes = new ArrayList<>();

            if (i > 0) {
                for (int j = 0; j < codeLengths.length; j++) {
                    if (codeLengths[j] == i) {
                        newNodes.add(new Leaf(j));
                    }
                }
            }

            for (int j = 0; j < trees.size(); j += 2) {
                newNodes.add(new Branch(trees.get(j), trees.get(j + 1)));
            }
            trees = newNodes;
        }

        if (trees.size() != 1) {
            throw new AssertionError();
        }
        return new CodeTree((Branch) trees.get(0), codeLengths.length);
    }

    private static int max(int[] array) {
        int retVal = array[0];
        for (int x : array) {
            retVal = Math.max(x, retVal);
        }
        return retVal;
    }
}
