package pbouda.sandbox.docker.metrics;

import sun.management.ManagementFactoryHelper;

import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

import static java.lang.System.out;

public class Memory {

    public static void printSummary() {
        MemoryMXBean memoryMXBean = ManagementFactoryHelper.getMemoryMXBean();
        printMemoryUsage(memoryMXBean.getHeapMemoryUsage(), "HEAP MEMORY");
        printMemoryUsage(memoryMXBean.getNonHeapMemoryUsage(), "NON-HEAP MEMORY");
    }

    public static void printDetail() {
        ManagementFactoryHelper.getMemoryPoolMXBeans()
                .forEach(memoryPool -> printMemoryUsage(memoryPool.getUsage(), memoryPool.getName()));

        ManagementFactoryHelper.getRuntimeMXBean();
    }

    private static void printMemoryUsage(MemoryUsage nonHeapMemory, String memoryName) {
        out.println();
        out.println(memoryName);
        out.println("------------------------");
        out.println("INIT: " + toMB(nonHeapMemory.getInit()));
        out.println("USED: " + toMB(nonHeapMemory.getUsed()));
        out.println("COMMITTED: " + toMB(nonHeapMemory.getCommitted()));
        out.println("MAX: " + toMB(nonHeapMemory.getMax()));
    }

    private static String toMB(long bytes) {
        return (bytes >> 20) + " MB";
    }
}
