/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpa.huffman.coding;

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
public class FrequencyTableTest {

    public FrequencyTableTest() {
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
    public void testFrequencyTableConstructor() {
        int[] testInput = {1, 2, 3, 4, 5, 6};
        try {
            FrequencyTable testTable = new FrequencyTable(testInput);
            assertNotNull(testTable);
        } catch (Exception e) {
            fail("Constructor should not fail when given proper input");
        }
    }

    @Test
    public void testFrequencyTableConstructorShouldFailWhenNegativeValuesInInput() {
        int[] testInput = {1, 2, 3, -4, 5, 6};
        try {
            FrequencyTable testTable = new FrequencyTable(testInput);
            fail("Constructor should throw an exception when there's negative values in input");

        } catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void testFrequencyTableConstructorShouldFailWhenInputLessThanTwo() {
        int[] testInput = {1};
        try {
            FrequencyTable testTable = new FrequencyTable(testInput);
            fail("Constructor should throw an exception when size of input array less than 2");

        } catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void testFrequencyTableConstructorShouldFailWhenNullInput() {
        int[] testInput = null;
        try {
            FrequencyTable testTable = new FrequencyTable(testInput);
            fail("Constructor should throw an exception when given null input");
        } catch (NullPointerException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void testGetSymbolLimit() {
        int[] input = {1, 2, 3, 4, 5, 6};
        FrequencyTable testTable = new FrequencyTable(input);
        assertEquals(6, testTable.getSymbolLimit());

    }

    /**
     * Test of get method, of class FrequencyTable.
     */
    @Test
    public void testGet() {
        int[] input = {1, 2, 3, 4, 5, 6};
        FrequencyTable testTable = new FrequencyTable(input);
        assertEquals(6, testTable.get(5));
    }
    @Test
    public void testGettFailsWhenIllegalSymbol() {
        int[] input = {1, 2, 3, 4, 5, 6};
        FrequencyTable testTable = new FrequencyTable(input);
        try {
            testTable.get(7);
            fail("get should fail when outside symbol table");
        } catch (IllegalArgumentException ex) {
            assertNotNull(ex);
        }
    }

    /**
     * Test of set method, of class FrequencyTable.
     */
    @Test
    public void testSet() {
        int[] input = {1, 2, 3, 4, 5, 6};
        FrequencyTable testTable = new FrequencyTable(input);
        testTable.set(4, 10);
        assertEquals(10, testTable.get(4));
    }

    @Test
    public void testSetFailsWhenNegativeParameter() {
        int[] input = {1, 2, 3, 4, 5, 6};
        FrequencyTable testTable = new FrequencyTable(input);
        try {
            testTable.set(0, -1);
            fail("set should fail when given a negative parameter");
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
    }
    @Test
    public void testSettFailsWhenIllegalSymbol() {
        int[] input = {1, 2, 3, 4, 5, 6};
        FrequencyTable testTable = new FrequencyTable(input);
        try {
            testTable.set(7, 10);
            fail("set should fail when outside symbol table");
        } catch (IllegalArgumentException ex) {
            assertNotNull(ex);
        }
    }

    /**
     * Test of increment method, of class FrequencyTable.
     */
    @Test
    public void testIncrement() {
        int[] input = {1, 2, 3, 4, 5, 6};
        FrequencyTable testTable = new FrequencyTable(input);
        testTable.increment(0);
        assertEquals(2, testTable.get(0));
    }

    @Test
    public void testIncrementFailsWhenAtMaxInteger() {
        int[] input = {2147483647, 2, 3, 4, 5, 6};
        FrequencyTable testTable = new FrequencyTable(input);
        try {
            testTable.increment(0);
            fail("increment should fail when incrementing a max integer");
        } catch (IllegalStateException ex) {
            assertNotNull(ex);
        }
    }
    
    @Test
    public void testIncrementFailsWhenIllegalSymbol() {
        int[] input = {1, 2, 3, 4, 5, 6};
        FrequencyTable testTable = new FrequencyTable(input);
        try {
            testTable.increment(7);
            fail("increment should fail when outside symbol table");
        } catch (IllegalArgumentException ex) {
            assertNotNull(ex);
        }
    }

    /**
     * Test of buildCodeTree method, of class FrequencyTable.
     */
    @Test
    public void testBuildCodeTree() {
        int[] input = {1, 2, 3, 4, 5, 6};
        FrequencyTable testTable = new FrequencyTable(input);
        try {
            CodeTree output = testTable.buildCodeTree();
        } catch (Exception e) {
            fail("buildCodeTree should not throw an exception when frequencies are valid");
        }
    }

}
