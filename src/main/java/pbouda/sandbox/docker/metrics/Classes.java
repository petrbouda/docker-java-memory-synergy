package pbouda.sandbox.docker.metrics;

import sun.management.ManagementFactoryHelper;

import java.lang.management.ClassLoadingMXBean;

import static java.lang.System.out;

public class Classes {

    public static void print() {
        out.println("Loaded Classes");
        out.println("-------------------------");
        ClassLoadingMXBean classLoading = ManagementFactoryHelper.getClassLoadingMXBean();
        out.println("Total # of loaded classes (from the RuntimeInfo start): " + classLoading.getTotalLoadedClassCount());
        out.println("Total # of unloaded classes: " + classLoading.getUnloadedClassCount());
        out.println("Current # of loaded classes: " + classLoading.getLoadedClassCount());
    }
}
