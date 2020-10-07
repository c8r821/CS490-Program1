package main.me.grimmeisen.program1;

public class Node {
    private int pid;
    private int priority;
    private long timeslice;

    public Node() {
        pid = -1;
        priority = -1;
        timeslice = -1;
    }

    public Node(int pid, int priority, long timeslice) {
        this.pid = pid;
        this.priority = priority;
        this.timeslice = timeslice;
    }

    public int getPid() {
        return pid;
    }

    public int getPriority() {
        return priority;
    }

    public long getTimeslice() {
        return timeslice;
    }

    public String toString() {
        return String.format("{ pid: %d, priority: %d, timeslice: %d }", pid, priority, timeslice);
    }
}
