package com.alpa.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A priority queue implementation using binary heap
 *
 * @param <K> the type of key used
 */
public class PriorityQueue<K> implements Iterable<K> {

    private K[] data;                  // Items are stored from index 1 to size
    private int size;
    private Comparator<K> comparator;  // optional comparator

    /**
     * Constructs an empty priority queue with the given capacity.
     *
     * @param i the initial capacity of the priority queue
     */
    public PriorityQueue(int i) {
        data = (K[]) new Object[i + 1];
        size = 0;
    }

    /**
     * Initializes an empty priority queue.
     */
    public PriorityQueue() {
        this(1);
    }

    /**
     * Initializes an empty priority queue with the given initial capacity,
     * using the given comparator.
     *
     * @param initCapacity the initial capacity of this priority queue
     * @param comparator the order to use when comparing keys
     */
    public PriorityQueue(int initCapacity, Comparator<K> comparator) {
        this.comparator = comparator;
        data = (K[]) new Object[initCapacity + 1];
        size = 0;
    }
    /**
     * Returns true if priority queue is empty.
     *
     * @return true if this priority queue is empty. Else return false.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of keys in the priority queue.
     *
     * @return the number of keys in the priority queue
     */
    public int size() {
        return size;
    }

    /**
     * Resizes the array
     *
     * @param capacity the new capacity
     */
    private void resize(int capacity) {
        assert capacity > size;
        K[] temp = (K[]) new Object[capacity];
        for (int i = 1; i <= size; i++) {
            temp[i] = data[i];
        }
        data = temp;
    }

    /**
     * Adds a new key to the priority queue.
     *
     * @param k the key to add to the priority queue
     */
    public void insert(K k) {
        // double size of array if necessary
        if (size == data.length - 1) {
            resize(2 * data.length);
        }

        data[++size] = k;
        swim(size);
        assert isMinHeap();
    }

    /**
     * Delete and return the smallest key.
     *
     * @return the smallest key in this priority queue
     * @throws NoSuchElementException
     */
    public K delMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue underflow");
        }
        exch(1, size);
        K min = data[size--];
        sink(1);
        data[size + 1] = null;         // avoid loitering and help with garbage collection
        if ((size > 0) && (size == (data.length - 1) / 4)) {
            resize(data.length / 2);
        }
        assert isMinHeap();
        return min;
    }

    private void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= size) {
            int j = 2 * k;
            if (j < size && greater(j, j + 1)) {
                j++;
            }
            if (!greater(k, j)) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }

    private boolean greater(int i, int j) {
        if (comparator == null) {
            return ((Comparable<K>) data[i]).compareTo(data[j]) > 0;
        } else {
            return comparator.compare(data[i], data[j]) > 0;
        }
    }

    private void exch(int i, int j) {
        K swap = data[i];
        data[i] = data[j];
        data[j] = swap;
    }

    private boolean isMinHeap() {
        return isMinHeap(1);
    }

    // Is one of the subheaps a min heap
    private boolean isMinHeap(int k) {
        if (k > size) {
            return true;
        }
        int left = 2 * k, right = 2 * k + 1;
        if (left <= size && greater(k, left)) {
            return false;
        }
        if (right <= size && greater(k, right)) {
            return false;
        }
        return isMinHeap(left) && isMinHeap(right);
    }

    /**
     * Returns an optional iterator
     *
     * @return an iterator that iterates the keys in ascending order
     */
    public Iterator<K> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<K> {

        private PriorityQueue<K> copy;

        // add all items to copy
        public HeapIterator() {
            if (comparator == null) {
                copy = new PriorityQueue<>(size());
            } else {
                copy = new PriorityQueue<>(size(), comparator);
            }
            for (int i = 1; i <= size; i++) {
                copy.insert(data[i]);
            }
        }

        public boolean hasNext() {
            return !copy.isEmpty();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public K next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return copy.delMin();
        }
    }
}
