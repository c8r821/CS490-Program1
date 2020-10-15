package main.le.grimmeisen.program1;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The RunThread class acts as the consumer for threaded tasks
 */
public class RunThread extends Thread {
    /**
     * A reference to the synchronized task heap object
     */
    private final MinHeap heap;

    /**
     * The consumer's name, used for identification
     */
    private final String name;

    /**
     * The duration the consumer should wait between checks for work
     */
    private final long sleepInterval;

    /**
     * A shared flag that indicates the producers completion status
     */
    private volatile boolean hasNewWork = true;

    /**
     * Parameterized constructor that allows for setting all configurations
     */
    public RunThread(String name, MinHeap heap, long sleepInterval) {
        this.name = name;
        this.heap = heap;
        this.sleepInterval = sleepInterval;
    }

    /**
     * Method that allows the main thread to indicate the producer has finished its work
     */
    public void noNewWork() {
        hasNewWork = false;
        System.out.printf(" -- %s has been notified of producer completion\n", name);
    }

    /**
     * The thread entry-point, will execute tasks added to the heap by order of priority, waiting if no new work is present
     */
    @Override
    public void run() {
        while (hasNewWork || !heap.isEmpty()) {
            while (heap.isEmpty()) {
                try {
                    if (!hasNewWork) return;
                    sleep(sleepInterval);
                } catch (InterruptedException ignored) {}
            }

            Node process = heap.pop();
            try {
                sleep(process.getTimeslice());
            } catch (InterruptedException e) {
                System.out.printf(" - %s failed to complete process %s with error message: %s\n", name, process.toString(), e.getMessage());
            }

            System.out.printf(" - %s completed process %s at time %s\n", name, process.toString(), LocalTime.now().format(DateTimeFormatter.ISO_TIME));
        }
        System.out.printf(" -- %s has completed its tasks\n", name);
    }
}
