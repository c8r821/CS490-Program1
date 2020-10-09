package main.le.grimmeisen.program1;

import java.util.ArrayList;
import java.util.Collections;

public class MinHeap {
    private final ArrayList<Node> data = new ArrayList<>();
    private final Object lock = new Object();

    private int getPriority(int index) {
        return data.get(index).getPriority();
    }

    private int parent(int index) {
        return index / 2;
    }

    private int left(int index) {
        return 2 * index;
    }

    private int right(int index) {
        return 2 * index + 1;
    }

    private boolean isLeaf(int index) {
        int size = data.size();

        return index >= (size / 2) && index <= size;
    }

    private void reheap(int index) {
        if (!isLeaf(index)) {
            if (getPriority(index) > getPriority(left(index)) || getPriority(index) > getPriority(right(index))) {
                if (getPriority(left(index)) < getPriority(right(index))) {
                    Collections.swap(data, index, left(index));
                    reheap(left(index));
                } else {
                    Collections.swap(data, index, right(index));
                    reheap(right(index));
                }
            }
        }
    }

    public synchronized Node read() {
        return data.get(0);
    }

    public synchronized void push(Node node) {
        data.add(node);

        int position = data.size() - 1;

        while (getPriority(position) < getPriority(parent(position))) {
            Collections.swap(data, position, parent(position));
            position = parent(position);
        }
    }

    public synchronized Node pop() {
        Node node = data.get(0);
        Node last = data.remove(data.size() - 1);
        if (data.size() > 0) {
            data.set(0, last);
        }

        reheap(0);
        return node;
    }

    public synchronized boolean isEmpty() {
        return data.size() == 0;
    }

    public synchronized void minify() {
        int last = data.size();

        for (int i = last / 2; i > 0; i--) {
            reheap(i);
        }
    }

    public synchronized String toString() {
        return data.toString();
    }
}
