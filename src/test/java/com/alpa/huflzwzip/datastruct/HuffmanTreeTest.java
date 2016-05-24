/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpa.huflzwzip.datastruct;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alpa
 */
public class HuffmanTreeTest {

    private HuffmanTree branch, left, right;

    @Before
    public void setUp() {
        left = new HuffmanTree(5, 5);
        right = new HuffmanTree(6, 6);
        branch = new HuffmanTree(left, right);
    }

    /**
     * Test of getSymbol method, of class HuffmanTree.
     */
    @Test
    public void testGetSymbolLeaf() {
        int expResult = 6;
        assertEquals(expResult, right.getSymbol());
    }

    /**
     * Test of getFrequency method, of class HuffmanTree.
     */
    @Test
    public void testGetFrequencyLeaf() {
        int expResult = 5;
        assertEquals(expResult, left.getFrequency());
    }

    /**
     * Test of getRightChild method, of class HuffmanTree.
     */
    @Test
    public void testGetRightChildBranch() {
        HuffmanTree result = branch.getRightChild();
        assertEquals(right, result);
    }

    /**
     * Test of getLeftChild method, of class HuffmanTree.
     */
    @Test
    public void testGetLeftChildBranch() {
        HuffmanTree result = branch.getLeftChild();
        assertEquals(left, result);
    }

    @Test
    public void testGetBranchFrequency() {
        assertEquals(11, branch.getFrequency());
    }

    @Test
    public void testLeafisLeaf() {
        assertEquals(true, left.isLeaf());
    }

    @Test
    public void testBranchisNotLeaf() {
        assertEquals(false, branch.isLeaf());
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
        HuffmanTree eqTree = new HuffmanTree(5, 5);
        assertEquals(0, left.compareTo(eqTree));
    }
}
