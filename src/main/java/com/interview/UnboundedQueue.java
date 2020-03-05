package com.interview;

public interface UnboundedQueue<E> {

    boolean add(E e);

    boolean offer(E e);

    E remove() throws QueueEmptyException;

    E poll();

    E element() throws QueueEmptyException;

    E peek();

    int size();
}
