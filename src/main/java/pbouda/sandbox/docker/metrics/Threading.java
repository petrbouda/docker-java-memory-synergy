package pbouda.sandbox.docker.metrics;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;

import static java.lang.System.out;

public class Threading {

    public static void print() {

        ThreadMXBean threads = ManagementFactory.getThreadMXBean();
        out.println("######################################");
        out.println("THREADS");

        out.println("Threads / Started Threads: " + threads.getThreadCount() + " / " + threads.getTotalStartedThreadCount());
        out.println("CPU Time / User Time: " + threads.getCurrentThreadCpuTime() + " / " + threads.getCurrentThreadUserTime());

        Arrays.stream(threads.dumpAllThreads(true, true))
                .forEach(thread -> out.println("Thread Name: " + thread.getThreadName()));
    }
}
