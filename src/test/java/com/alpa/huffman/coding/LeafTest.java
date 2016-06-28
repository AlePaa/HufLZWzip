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
public class LeafTest {

    public LeafTest() {
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
     * Test of isLeaf method, of class Leaf.
     */
    @Test
    public void testLeafConstructorDoesNotAcceptNegativeValues() {
        try {
            Leaf testLeaf = new Leaf(-1);
            fail("Constructor did not throw an exception when given a negative argument");
        } catch (IllegalArgumentException ex) {
            assertNotNull(ex);
        }
    }

}
