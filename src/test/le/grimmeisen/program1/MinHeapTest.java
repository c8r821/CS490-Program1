package test.le.grimmeisen.program1;

import main.le.grimmeisen.program1.MinHeap;
import main.le.grimmeisen.program1.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the MinHeap implementation
 */
class MinHeapTest {
    MinHeap test;

    @BeforeEach
    void setUp() {
        test = new MinHeap();
        test.push(new Node(1, 3, 100));
        test.push(new Node(2, 2, 50));
        test.push(new Node(3, 5, 50));
        test.push(new Node(4, 1, 50));
        test.push(new Node(5, 4, 50));
    }

    @Test
    void read() {
        assertEquals(test.read().getPid(), 4);
    }

    @Test
    void push() {
        test.push(new Node(999, 999, 999));

        assertEquals(test.data.get(5).getPid(), 999);
    }

    @Test
    void pop() {
        Node high = test.pop();
        assertEquals(high.getPriority(), 1);

        Node second = test.pop();
        assertEquals(second.getPriority(), 2);

        Node third = test.pop();
        assertEquals(third.getPriority(), 3);

        Node fourth = test.pop();
        assertEquals(fourth.getPriority(), 4);

        Node low = test.pop();
        assertEquals(low.getPriority(), 5);
    }
}