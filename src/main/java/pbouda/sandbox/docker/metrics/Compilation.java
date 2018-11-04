package pbouda.sandbox.docker.metrics;

import sun.management.ManagementFactoryHelper;
import sun.management.VMManagement;

public class Compilation {

    public void print() {
        VMManagement vm = ManagementFactoryHelper.getVMManagement();
        vm.getCompilerName();
        vm.getTotalCompileTime();
        vm.getSafepointCount();
        vm.getTotalSafepointTime();
        vm.isCompilationTimeMonitoringSupported();
    }
}
