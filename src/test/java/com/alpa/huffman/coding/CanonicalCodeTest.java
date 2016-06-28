/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpa.huffman.coding;

import com.alpa.huffman.coding.Leaf;
import com.alpa.huffman.coding.CodeTree;
import com.alpa.huffman.coding.Branch;
import com.alpa.huffman.coding.CanonicalCode;
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
public class CanonicalCodeTest {

    public CanonicalCodeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCanonicalCodeConstructor() {
        CodeTree test = new CodeTree(new Branch(new Leaf(4), new Leaf(3)), 5);
        CanonicalCode testCanon = new CanonicalCode(test, 257);
        assertNotNull(testCanon);
    }

    @Test
    public void testGetSymbolLimit() {
        CodeTree test = new CodeTree(new Branch(new Leaf(4), new Leaf(3)), 5);
        CanonicalCode testCanon = new CanonicalCode(test, 257);
        assertEquals(257, testCanon.getSymbolLimit());
    }

    /**
     * Test of getCodeLength method, of class CanonicalCode.
     */
    @Test
    public void testGetCodeLength() {
        CodeTree test = new CodeTree(new Branch(new Leaf(4), new Leaf(3)), 5);
        CanonicalCode testCanon = new CanonicalCode(test, 257);
        assertEquals(1, testCanon.getCodeLength(4));
    }

    /**
     * Test of toCodeTree method, of class CanonicalCode.
     */
    @Test
    public void testToCodeTree() {
        CodeTree test = new CodeTree(new Branch(new Leaf(4), new Leaf(3)), 5);
        CanonicalCode testCanon = new CanonicalCode(test, 257);
        CodeTree out = testCanon.toCodeTree();
        assertNotNull(out);

    }

    @Test
    public void testCanonicalCodeConstructorWithFrequenciesParameter() {
        int[] testInput = {1, 2, 2};
        CanonicalCode testCanon = new CanonicalCode(testInput);
        assertNotNull(testCanon);
    }

    @Test
    public void testCanonicalCodeThrowsExceptionWithNegativeLengths() {
        int[] testInput = {1, 2, -2};
        try {
            CanonicalCode testCanon = new CanonicalCode(testInput);
            fail("Constructor should throw an exception when given negative lengths");
        } catch (IllegalArgumentException ex) {
            assertNotNull(ex);
        }
    }

    @Test
    public void testCanonicalCodeConstructorThrowsExceptionWithNullInput() {
        int[] testInput = null;
        try {
            CanonicalCode testCanon = new CanonicalCode(testInput);
            fail("constructor should throw an exception when null input");
        } catch (NullPointerException ex) {
            assertNotNull(ex);
        }
    }

    @Test
    public void testCanonicalCodeConstructorThrowsExceptionWithInputLessThanTwo() {
        int[] testInput = {1};
        try {
            CanonicalCode testCanon = new CanonicalCode(testInput);
            fail("constructor should throw an exception when input is less than 2");
        } catch (IllegalArgumentException ex) {
            assertNotNull(ex);
        }
    }

}
