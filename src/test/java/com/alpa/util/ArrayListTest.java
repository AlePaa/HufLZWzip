/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpa.util;

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
public class ArrayListTest {

    public ArrayListTest() {
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
    public void testConstructorWithNegativeParametersShouldThrowException() {
        try {
            ArrayList testList = new ArrayList(-10);
            fail("Constructor did not throw an exception with illegal arguments");
        } catch (IllegalArgumentException ex) {
            assertNotNull(ex);
        }

    }

    /**
     * Test of add method, of class ArrayList.
     */
    @Test
    public void testAdd() {
        ArrayList<Integer> testList = new ArrayList();
        testList.add(1);
        assertEquals(1, testList.size());
        testList.add(2);
        assertEquals(2, testList.size());
        testList.add(3);
        assertEquals(3, testList.size());
        Integer expResult = 2;
        assertEquals(expResult, testList.get(1));
    }

    @Test
    public void testAddTriggersGrow() {
        ArrayList<Integer> testList = new ArrayList(2);
        testList.add(1);
        testList.add(2);
        testList.add(3);
        assertEquals(3, testList.size());
        assertEquals((Integer) 3, testList.get(2));
    }

    /**
     * Test of set method, of class ArrayList.
     */
    @Test
    public void testSet() {
        ArrayList<Integer> testList = new ArrayList();
        testList.add(1);
        assertEquals(new Integer(1), testList.get(0));
        testList.set(0, 2);
        assertEquals(new Integer(2), testList.get(0));
    }

    /**
     * Test of get method, of class ArrayList.
     */
    @Test
    public void testGet() {
        ArrayList<Integer> testList = createList(4);
        Integer expResult = 2;
        assertEquals(expResult, testList.get(2));
    }

    @Test
    public void testTrimtoSize() {
        ArrayList<Integer> testList = createList(5);
        assertEquals(5, testList.size());
        assertEquals(10, testList.length());
        testList.remove(1);
        testList.remove(3);
        testList.trimToSize();

        assertEquals(3, testList.size());
        assertEquals(3, testList.length());
    }

    /**
     * Test of size method, of class ArrayList.
     */
    @Test
    public void testSize() {
        ArrayList<Integer> testList = new ArrayList();
        testList.add(1);
        assertEquals(1, testList.size());
        testList.add(2);
        assertEquals(2, testList.size());
    }

    /**
     * Test of isEmpty method, of class ArrayList.
     */
    @Test
    public void testIsEmpty() {
        ArrayList<Integer> testList = new ArrayList();
        assertEquals(true, testList.isEmpty());
        testList.add(2);
        assertEquals(false, testList.isEmpty());
        testList.remove(0);
        assertEquals(true, testList.isEmpty());
    }

    /**
     * Test of remove method, of class ArrayList.
     */
    @Test
    public void testRemove() {
        ArrayList<Integer> testList = new ArrayList();
        testList.add(1);
        assertEquals(1, testList.size());
        testList.remove(0);
        assertEquals(0, testList.size());
    }

    @Test
    public void testRemovingOverPermittedIndexThrowsException() {
        ArrayList<Integer> testList = new ArrayList(2);
        try {
            testList.remove(5);
            fail("No exception was thrown when removing over permitted index");
        } catch (IndexOutOfBoundsException ex) {
            assertNotNull(ex);
        }
    }

    @Test
    public void testIterate() {
        ArrayList<Integer> testList = createList(5);
        int counter = 0;
        for (int i : testList) {
            assertEquals(i, counter);
            counter++;
        }
    }

    @Test
    public void testAddSetString() {
        ArrayList<String> testList = new ArrayList();
        String stringToTest = "Oikea ja Vasen";
        testList.add(stringToTest);
        assertEquals(stringToTest, testList.get(0));
        String setString = "Vasen ja Oikea";
        testList.set(0, setString);
        assertEquals(setString, testList.get(0));
    }

    @Test
    public void testConstructorWithListParameter() {
        ArrayList<Integer> otherList = createList(4);
        ArrayList<Integer> testList = new ArrayList(otherList);
        assertEquals(4, testList.size());
        assertEquals(new Integer(1), testList.get(1));
        assertEquals(new Integer(3), testList.get(3));
        int counter = 0;
        for (int i : testList) {
            assertEquals(i, counter);
            counter++;
        }
    }

    @Test
    public void testArrayListInception() {
        ArrayList<ArrayList<Integer>> testList = new ArrayList();
        ArrayList<Integer> innerList = createList(5);
        testList.add(innerList);
        ArrayList<Integer> result = testList.get(0);
        int counter = 0;
        for (int i : result) {
            assertEquals(i, counter);
            counter++;
        }
    }

    public ArrayList createList(int i) {
        ArrayList testList = new ArrayList();
        for (int j = 0; j < i; j++) {
            testList.add(j);
        }
        return testList;
    }

}