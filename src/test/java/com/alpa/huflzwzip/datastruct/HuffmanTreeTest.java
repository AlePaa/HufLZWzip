/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpa.huflzwzip.datastruct;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alpa
 */
public class HuffmanTreeTest {

    private HuffmanLeaf left, right;
    private HuffmanBranch branch;
    
    @Before
    public void setUp() {
        left = new HuffmanLeaf('a', 5);
        right = new HuffmanLeaf('b', 6);
        branch = new HuffmanBranch(left, right);
    }

    /**
     * Test of getSymbol method, of class HuffmanTree.
     */
    @Test
    public void testGetSymbolLeaf() {
        char expResult = 'b';
        assertEquals(expResult, right.symbol);
    }

    /**
     * Test of getFrequency method, of class HuffmanTree.
     */
    @Test
    public void testGetFrequencyLeaf() {
        int expResult = 5;
        assertEquals(expResult, left.freq);
    }

    /**
     * Test of getRightChild method, of class HuffmanTree.
     */
    @Test
    public void testGetRightChildBranch() {
        HuffmanTree result = branch.rightChild;
        assertEquals(right, result);
    }

    /**
     * Test of getLeftChild method, of class HuffmanTree.
     */
    @Test
    public void testGetLeftChildBranch() {
        HuffmanTree result = branch.leftChild;
        assertEquals(left, result);
    }

    @Test
    public void testGetBranchFrequency() {
        assertEquals(11, branch.freq);
    }

    @Test
    public void testCompareToLess() {
        assertEquals(-1, left.compareTo(right));
    }

    @Test
    public void testCompareToMore() {
        assertEquals(1, right.compareTo(left));
    }

    @Test
    public void testCompareToEqual() {
        HuffmanLeaf eqTree = new HuffmanLeaf('a', 5);
        assertEquals(0, left.compareTo(eqTree));
    }
    
}
