package test.le.grimmeisen.program1;

import main.le.grimmeisen.program1.MinHeap;
import main.le.grimmeisen.program1.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MinHeapTest {
    MinHeap test;

    private void addNodes() {
        test.push(new Node(1, 3, 100));
        test.push(new Node(2, 2, 50));
        test.push(new Node(3, 5, 50));
        test.push(new Node(4, 1, 50));
        test.push(new Node(5, 4, 50));
    }

    @BeforeEach
    void setUp() {
        test = new MinHeap();
    }

    @Test
    void read() {
        addNodes();

        assertEquals(test.read().getPid(), 4);
    }

    @Test
    void push() {
        addNodes();
        System.out.println(test);
    }

    @Test
    void pop() {
        addNodes();
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

    @Test
    void minify() {
    }
}