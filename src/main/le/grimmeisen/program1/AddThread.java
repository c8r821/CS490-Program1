package main.le.grimmeisen.program1;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The AddThread class acts as the producer for threaded tasks
 */
public class AddThread extends Thread {
    /**
     * A reference to the synchronized task heap object
     */
    private final MinHeap heap;

    /**
     * The number of batch adds that will be performed
     */
    private final int batchNum;

    /**
     * The number of tasks to add with each batch
     */
    private final int batchSize;

    /**
     * The minimum value the random priority can be set to
     */
    private final int priorityMin;

    /**
     * The maximum value the random priority can be set to
     */
    private final int priorityMax;

    /**
     * The duration the producer should wait between batches
     */
    private final long sleepInterval;

    /**
     * The minimum "runtime" for produced tasks
     */
    private final long sleepMin;

    /**
     * The maximum "runtime" for produced tasks
     */
    private final long sleepMax;

    int pid = 0;

    /**
     * Parameterized constructor that allows for setting all configurations
     */
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

    /**
     * The thread entry-point, will perform batch adds as specified by the parameters passed in
     */
    @Override
    public void run() {
        for (int batch = 0; batch < batchNum; batch++) {
            for (int i = 0; i < batchSize; i++) {
                Node process = new Node(
                    pid++,
                    (int) Math.floor(Math.random() * priorityMax + priorityMin),
                    (long) Math.floor(Math.random() * sleepMax + sleepMin)
                );

                heap.push(process);

                System.out.printf(" + Producer added process %s at time %s\n", process.toString(), LocalTime.now().format(DateTimeFormatter.ISO_TIME));
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
