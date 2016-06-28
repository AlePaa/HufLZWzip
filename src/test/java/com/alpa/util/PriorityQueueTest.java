/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpa.util;

import java.util.Iterator;
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
public class PriorityQueueTest {

    public PriorityQueueTest() {
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
     * Test of isEmpty method, of class PriorityQueue.
     */
    @Test
    public void testIsEmpty() {
        PriorityQueue test = new PriorityQueue();
        assertEquals(true, test.isEmpty());
    }

    /**
     * Test of size method, of class PriorityQueue.
     */
    @Test
    public void testSize() {
        PriorityQueue<Integer> instance = new PriorityQueue<>();
        assertEquals(0, instance.size());
        instance.insert(1);
        instance.insert(2);
        assertEquals(2, instance.size());
    }

    /**
     * Test of insert method, of class PriorityQueue.
     */
    @Test
    public void testInsert() {
        PriorityQueue<Integer> instance = new PriorityQueue<>();
        instance.insert(5);
        assertEquals(1, instance.size());
        assertEquals(new Integer(5), instance.delMin());
    }

    /**
     * Test of delMin method, of class PriorityQueue.
     */
    @Test
    public void testDelMin() {
        PriorityQueue<Integer> instance = new PriorityQueue<>();
        instance.insert(7);
        instance.insert(5);
        instance.insert(6);
        assertEquals(new Integer(5), instance.delMin());
    }
}
