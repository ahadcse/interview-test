package com.interview;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class GenericQueueTest {

    @Test
    void testQueue() throws QueueEmptyException {

        UnboundedQueue<Integer> queue = new GenericQueue<>(5);

        queue.add(100);
        queue.add(200);

        Assertions.assertEquals(2, queue.size());

        int fromQueue = queue.poll();
        Assertions.assertEquals(100, fromQueue);

        fromQueue = queue.peek();
        Assertions.assertEquals(200, fromQueue);
        Assertions.assertEquals(1, queue.size());

        fromQueue = queue.element();
        Assertions.assertEquals(200, fromQueue);
        Assertions.assertEquals(1, queue.size());

        fromQueue = queue.remove();
        Assertions.assertEquals(200, fromQueue);
        Assertions.assertEquals(0, queue.size());
    }

}
