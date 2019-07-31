package pbouda.sandbox.docker.metrics;

import java.lang.management.CompilationMXBean;
import java.lang.management.ManagementFactory;

public class Compilation {

    public void print() {
        CompilationMXBean compilation = ManagementFactory.getCompilationMXBean();
        compilation.getName();
        compilation.getTotalCompilationTime();
        compilation.isCompilationTimeMonitoringSupported();
    }
}
