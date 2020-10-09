package main.le.grimmeisen.program1;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class RunThread extends Thread {
    private final MinHeap heap;
    private final String name;
    private final long sleepInterval;

    private volatile boolean hasNewWork = true;

    public RunThread(String name, MinHeap heap, long sleepInterval) {
        this.name = name;
        this.heap = heap;
        this.sleepInterval = sleepInterval;
    }

    public void noNewWork() {
        hasNewWork = false;
    }

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
                System.out.printf("%s failed to complete process %s with error message: %s\n", name, process.toString(), e.getMessage());
            }

            System.out.printf("%s completed process %s at time %s\n", name, process.toString(), LocalTime.now().format(DateTimeFormatter.ISO_TIME));
        }
    }
}
