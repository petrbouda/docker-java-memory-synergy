package pbouda.sandbox.docker.metrics;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

import static java.lang.System.out;

public class RuntimeInfo {

    public static void print() {
        ManagementFactory.getRuntimeMXBean();

        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        out.println("#######################");
        out.println("RUNTIME INFO");

        out.println(runtime.getVmName());
        out.println(runtime.getVmVersion());
        out.println("PID @ Hostname: " + runtime.getName());

        out.println();
        out.println("Arguments:");
        runtime.getInputArguments().forEach(out::println);
    }
}
