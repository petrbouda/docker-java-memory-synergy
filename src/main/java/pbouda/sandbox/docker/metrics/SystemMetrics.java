package pbouda.sandbox.docker.metrics;

import jdk.internal.platform.Metrics;

import static java.lang.System.out;

public class SystemMetrics {

    public static void print() {
        Metrics metrics = Metrics.systemMetrics();

        out.println(metrics.getEffectiveCpuCount());
        out.println(metrics.getMemoryLimit());
        out.println(metrics.getMemorySoftLimit());
        out.println(metrics.getTcpMemoryUsage());
    }
}
