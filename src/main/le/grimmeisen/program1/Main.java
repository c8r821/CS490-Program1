package main.le.grimmeisen.program1;

public class Main {
    public static final long RUN_SLEEP_INTERVAL = 1000;

    public static final int ADD_BATCH_NUM = 5;
    public static final int ADD_BATCH_SIZE = 20;

    public static final int ADD_PRIORITY_MIN = 1;
    public static final int ADD_PRIORITY_MAX = 100;

    public static final long ADD_SLEEP_INTERVAL = 10000;

    public static final long ADD_SLEEP_MIN = 250;
    public static final long ADD_SLEEP_MAX = 1000;

    public static void main(String[] args) throws InterruptedException {
	    MinHeap processes = new MinHeap();

        RunThread r1 = new RunThread("Consumer-1", processes, RUN_SLEEP_INTERVAL);
        RunThread r2 = new RunThread("Consumer-2", processes, RUN_SLEEP_INTERVAL);

        r1.start();
        r2.start();

        AddThread a1 = new AddThread(processes, ADD_BATCH_NUM, ADD_BATCH_SIZE, ADD_PRIORITY_MIN, ADD_PRIORITY_MAX, ADD_SLEEP_INTERVAL, ADD_SLEEP_MIN, ADD_SLEEP_MAX);

        a1.start();

        a1.join();
        r1.noNewWork();
        r2.noNewWork();

        r1.join();
        r2.join();
    }
}
