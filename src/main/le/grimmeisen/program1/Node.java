package main.le.grimmeisen.program1;

/**
 * Node class simulating a process
 */
public class Node {
    /**
     * The process id - a unique identifier for the process
     */
    private final int pid;

    /**
     * The priority indicates the order in which processes should be executed
     */
    private final int priority;

    /**
     * The busy wait time used to simulate process execution
     */
    private final long timeslice;

    /**
     * Default constructor
     */
    public Node() {
        pid = -1;
        priority = -1;
        timeslice = -1;
    }

    /**
     * Parameterized constructor
     */
    public Node(int pid, int priority, long timeslice) {
        this.pid = pid;
        this.priority = priority;
        this.timeslice = timeslice;
    }

    /**
     * PID Getter
     * @return Process id
     */
    public int getPid() {
        return pid;
    }

    /**
     * Priority getter
     * @return Process priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Timeslice getter
     * @return Process timeslice
     */
    public long getTimeslice() {
        return timeslice;
    }

    /**
     * Outputs process details
     * @return A string representing the node
     */
    public String toString() {
        return String.format("{ pid: %d, priority: %d, timeslice: %d }", pid, priority, timeslice);
    }
}
