package pbouda.sandbox.docker.metrics;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;

import static java.lang.System.out;

public class Classes {

    public static void print() {
        out.println("Loaded Classes");
        out.println("-------------------------");
        ClassLoadingMXBean classLoading = ManagementFactory.getClassLoadingMXBean();
        out.println("Total # of loaded classes (from the RuntimeInfo start): " + classLoading.getTotalLoadedClassCount());
        out.println("Total # of unloaded classes: " + classLoading.getUnloadedClassCount());
        out.println("Current # of loaded classes: " + classLoading.getLoadedClassCount());
    }
}
