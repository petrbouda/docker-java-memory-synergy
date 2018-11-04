package pbouda.sandbox.docker;

import pbouda.sandbox.docker.metrics.Threading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadAllocation {
    public static void main(String[] args) throws InterruptedException {
        long numberOfThreads = args.length == 0
                ? Long.MAX_VALUE
                : Long.valueOf(args[0]);

        Runtime.getRuntime().addShutdownHook(new Thread(Threading::print));

        List<Thread> threads = new ArrayList<>();
        AtomicInteger counter = new AtomicInteger();
        for (int i = 0; i < numberOfThreads; i++) {
            Thread thread = new Thread(() -> {
                counter.incrementAndGet();

                try {
                    Thread.currentThread().join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
            threads.add(thread);
        }

        System.out.println("DONE ! - " + counter.get() + " " + threads.size());
        Thread.currentThread().join();
    }
}
