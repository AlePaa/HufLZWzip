package com.alpa.huflzwzip.datastruct;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayList<E> implements Iterable<E> {

    private transient Object data[];
    private int size = 0;

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Initial capacity must not be negative");
        }
        data = new Object[capacity];
    }

    public ArrayList() {
        data = new Object[10];
    }

    public ArrayList(ArrayList<E> a) {
        data = a.toArray();
        size = data.length;

        if (data.getClass() != Object[].class) {
            data = Arrays.copyOf(data, size, Object[].class);
        }
    }

    public void trimToSize() {
        int oldCapacity = data.length;
        if (size < oldCapacity) {
            data = Arrays.copyOf(data, size);
        }
    }

    /**
     * Adds elements to the array
     *
     * @param e the element to add
     */
    public void add(Object e) {
        if (size == data.length) {
            grow();
        }
        data[size++] = e;
    }

    /**
     * Puts an element to the array index and returns its previous value
     *
     * @param index the index to operate on
     * @param e the object to be set
     * @return the previous element of the index
     */
    public E set(int index, Object e) {
        rangeCheck(index);
        E oldVal = data(index);
        data[index] = e;
        return oldVal;
    }

    /**
     * returns the element on a specific index
     *
     * @param index the index in the data array to look in
     * @return the element stored in the given index
     */
    @SuppressWarnings("unchecked")
    public E get(int index) {
        rangeCheck(index);

        return data(index);
    }

    /**
     *
     * @return the number of elements in the array
     */
    public int size() {
        return size;
    }

    /**
     *
     * @return true if the size of the array equals zero. Else return false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     *
     * @param index the index in the array to empty
     * @return the removed element of the index
     */
    public E remove(int index) {
        rangeCheck(index);

        E removedElement = (E) data[index];
        for (int i = index; i < size; i++) {
            data[i] = data[i + 1];
        }
        size--;
        return removedElement;
    }

    /**
     * double the size of the Array
     */
    private void grow() {
        int newSize = data.length * 2;
        data = Arrays.copyOf(data, newSize);
    }

    /**
     * @param index the index being operated on
     * @throws IndexOutofBoundsException if index is out of permitted range
     */
    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size " + index);
        }
    }

    /**
     *
     * @param index
     * @return
     */
    private E data(int index) {
        return (E) data[index];
    }

    public Object[] toArray() {
        return Arrays.copyOf(data, size);
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int currentIndex = 0;

            @Override
            public boolean hasNext() {
                if (currentIndex == size) {
                    return false;
                }
                return !(data[currentIndex] == null
                        && data[currentIndex + 1] != null);
            }

            @Override
            public E next() {
                return data(currentIndex++);
            }

            @Override
            public void remove() {
                for (int i = currentIndex; i < data.length - 1; i++) {
                    data[i] = data[i + 1];
                }
            }
        };
    }
}
