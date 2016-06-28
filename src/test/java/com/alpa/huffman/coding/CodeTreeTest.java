/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpa.huffman.coding;

import com.alpa.util.ArrayList;
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
public class CodeTreeTest {

    public CodeTreeTest() {
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

    /**
     * Test of getCode method, of class CodeTree.
     */
    @Test
    public void testCodeTreeConstructorThrowsExceptionWithNullInput() {
        try {
            CodeTree test = new CodeTree(null, 10);
            fail("constructor should throw an exception when input branch is null");
        } catch (NullPointerException ex) {
            assertNotNull(ex);
        }
    }

    @Test
    public void testCodeTreeConstructorThrowsExceptioWhenSymbolLimitTooSmall() {
        try {
            CodeTree test = new CodeTree(new Branch(new Leaf(4), new Leaf(3)), 1);
            fail("constructor should throw an exception when symbol limit is below 2");
        } catch (IllegalArgumentException ex) {
            assertNotNull(ex);
        }
    }

    @Test
    public void testBuildCodeTreeThrowsExceptioWhenOverSymbolLimit() {
        try {
            CodeTree test = new CodeTree(new Branch(new Leaf(4), new Leaf(3)), 3);
            fail("constructor should throw an exception a tree value is over symbol limit");
        } catch (IllegalArgumentException ex) {
            assertNotNull(ex);
        }
    }

    @Test
    public void testBuildCodeTreeThrowsExceptioWhenSymbolHasMoreThanOneCode() {
        try {
            CodeTree test = new CodeTree(new Branch(new Leaf(2), new Leaf(2)), 3);
            fail("constructor should throw an exception when a symbol has more than one code");
        } catch (IllegalArgumentException ex) {
            assertNotNull(ex);
        }
    }

    @Test
    public void testGetCode() {
        CodeTree test = new CodeTree(new Branch(new Leaf(4), new Leaf(3)), 5);
        ArrayList<Integer> out = test.getCode(3);
        assertNotNull(out);
    }

    @Test
    public void testGetCodeFailsWhenNegativeParameter() {
        CodeTree test = new CodeTree(new Branch(new Leaf(4), new Leaf(3)), 5);
        try {
            ArrayList<Integer> out = test.getCode(-9);
            fail("getCode should fail when given negative parameters");
        } catch (IllegalArgumentException ex) {
            assertNotNull(ex);
        }
    }
}
