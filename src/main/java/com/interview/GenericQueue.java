package com.interview;

public class GenericQueue<T> implements UnboundedQueue<T>{
    private T[] data;
    private int front, size, back;

    public final static int DEFAULT_INITIAL_ARRAY_SIZE = 16;
    /**
     * MAX_ARRAY_SIZE is set to Integer.MAX_VALUE - 8 to prevent OutOfMemoryErrors.
     */
    public static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    public GenericQueue() {
        this(DEFAULT_INITIAL_ARRAY_SIZE);
    }

    public GenericQueue(int capacity) throws IllegalArgumentException {
        if (capacity < 1) {
            throw new IllegalArgumentException("Queue capacity must be 1 or greater");
        }
        if (capacity > MAX_ARRAY_SIZE) {
            throw new IllegalArgumentException("Stack capacity is greater then maximum array size");
        }
        @SuppressWarnings("unchecked")
        T[] tempData = (T[]) new Object[capacity];
        size = 0;
        front = 0;
        back = 0;
        data = tempData;
    }

    // resize the underlying array
    private void resize(int capacity) {
        assert capacity >= size;
        T[] copy = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            copy[i] = data[(front + i) % data.length];
        }
        data = copy;
        front = 0;
        back = size;
    }

    @Override
    public boolean add(T e) {
        if (size == data.length) {
            resize(2 * data.length);   // double size of array if necessary
        }
        synchronized (data) {
            data[back++] = e;                        // add item
            if (back == data.length) back = 0;          // wrap-around
            size++;
            return true;
        }
    }

    @Override
    public boolean offer(T e) {
        if (size == data.length) {
            return false;
        }
        synchronized (data) {
            data[back++] = e;                        // add item
            if (back == data.length) back = 0;          // wrap-around
            size++;
            return true;
        }
    }

    @Override
    public T remove() throws QueueEmptyException {
        if (size == 0) {
            throw new QueueEmptyException("Queue does not contain any items.");
        }
        synchronized (data) {
            T item = data[front];
            data[front] = null;                            // to avoid loitering
            size--;
            front++;
            if (front == data.length) front = 0;           // wrap-around
            // shrink size of array if necessary
            if (size > 0 && size == data.length / 4) resize(data.length / 2);
            return item;
        }
    }

    @Override
    public T poll() {
        if (size == 0) {
            return null;
        }
        synchronized (data) {
            T item = data[front];
            data[front] = null;                            // to avoid loitering
            size--;
            front++;
            if (front == data.length) front = 0;           // wrap-around
            // shrink size of array if necessary
            if (size > 0 && size == data.length / 4) resize(data.length / 2);
            return item;
        }
    }

    @Override
    public T element() throws QueueEmptyException {
        if (size == 0) {
            throw new QueueEmptyException("Queue does not contain any items.");
        }
        return data[front];
    }

    @Override
    public T peek() {
        if (size != 0) {
            return data[front];
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }
}