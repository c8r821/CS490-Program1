package main.le.grimmeisen.program1;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A synchronized MinHeap implementation using a dynamically sized ArrayList and Java's synchronized keyword
 */
public class MinHeap {
    /**
     * Data storage for elements in the MinHeap
     */
    public final ArrayList<Node> data = new ArrayList<>();

    /**
     * Helper function to return the priority of a node at the given index
     * @param index Index of the node
     * @return The given node's priority
     */
    private int getPriority(int index) {
        return data.get(index).getPriority();
    }

    /**
     * Helper function to return the parent of a given node
     * @param index Index of the child node
     * @return Index of the parent node
     */
    private int parent(int index) {
        return index / 2;
    }

    /**
     * Helper function to return the left child of a given node
     * @param index Index of the parent node
     * @return Index of the left child node
     */
    private int left(int index) {
        return 2 * index;
    }

    /**
     * Helper function to return the right child of a given node
     * @param index Index of the parent node
     * @return Index of the right child node
     */
    private int right(int index) {
        return 2 * index + 1;
    }

    /**
     * Helper function to determine if a node is a leaf
     * @param index Index of the node
     * @return Boolean indicating leaf status
     */
    private boolean isLeaf(int index) {
        int size = data.size();

        return index >= (size / 2) && index <= size;
    }

    /**
     * Reorganizes the heap after an element is added, brings lowest value to the root
     * @param index Index to perform reheap operation from
     */
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

    /**
     * Synchronized method for reading root node
     * @return Root node
     */
    public synchronized Node read() {
        return data.get(0);
    }

    /**
     * Synchronized method for adding a node to the minheap
     * @param node Node to be added
     */
    public synchronized void push(Node node) {
        data.add(node);

        int position = data.size() - 1;

        while (getPriority(position) < getPriority(parent(position))) {
            Collections.swap(data, position, parent(position));
            position = parent(position);
        }
    }

    /**
     * Synchronized method for reading and removing root node
     * @return Root node
     */
    public synchronized Node pop() {
        Node node = data.get(0);
        Node last = data.remove(data.size() - 1);
        if (data.size() > 0) {
            data.set(0, last);
        }

        reheap(0);
        return node;
    }

    /**
     * Synchronized method to determine if minheap is empty
     * @return Whether minheap is empty
     */
    public synchronized boolean isEmpty() {
        return data.size() == 0;
    }
}
