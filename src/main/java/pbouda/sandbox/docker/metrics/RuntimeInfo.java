package pbouda.sandbox.docker.metrics;

import sun.management.ManagementFactoryHelper;
import sun.management.VMManagement;

import java.lang.management.RuntimeMXBean;

import static java.lang.System.out;

public class RuntimeInfo {

    public static void print() {
        ManagementFactoryHelper.getHotspotRuntimeMBean();
        VMManagement vm = ManagementFactoryHelper.getVMManagement();

        RuntimeMXBean runtime = ManagementFactoryHelper.getRuntimeMXBean();
        out.println("#######################");
        out.println("RUNTIME INFO");

        out.println(vm.getVmName());
        out.println(vm.getVmVersion() + ", " + vm.getCompilerName());
        out.println("PID @ Hostname: " + runtime.getName());

        out.println();
        out.println("Arguments:");
        vm.getVmArguments().forEach(out::println);
    }
}
