package pbouda.sandbox.docker.metrics;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;

public class GarbageCollection {

    private static void printSummary() {
        var gcBeans = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean bean : gcBeans) {
            bean.getCollectionCount();
            bean.getCollectionTime();
            bean.getMemoryPoolNames();
        }
    }

    public static void main(String[] args) {
        printSummary();
    }
}
