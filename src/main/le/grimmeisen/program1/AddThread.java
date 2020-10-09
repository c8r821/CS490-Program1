package main.le.grimmeisen.program1;

public class AddThread extends Thread {
    private final MinHeap heap;
    private final int batchNum;
    private final int batchSize;
    private final int priorityMin;
    private final int priorityMax;
    private final long sleepInterval;
    private final long sleepMin;
    private final long sleepMax;

    int pid = 0;

    public AddThread(MinHeap heap, int batchNum, int batchSize, int priorityMin, int priorityMax, long sleepInterval, long sleepMin, long sleepMax) {
        this.heap = heap;
        this.batchNum = batchNum;
        this.batchSize = batchSize;
        this.priorityMin = priorityMin;
        this.priorityMax = priorityMax;
        this.sleepInterval = sleepInterval;
        this.sleepMin = sleepMin;
        this.sleepMax = sleepMax;
    }

    @Override
    public void run() {
        for (int batch = 0; batch < batchNum; batch++) {
            for (int i = 0; i < batchSize; i++) {
                heap.push(
                    new Node(
                        pid++,
                        (int) Math.floor(Math.random() * priorityMax + priorityMin),
                        (long) Math.floor(Math.random() * sleepMax + sleepMin)
                    )
                );
            }

            try {
                sleep(sleepInterval);
            } catch (InterruptedException e) {
                System.out.println("AddThread was interrupted...exiting");
                break;
            }
        }
    }
}
