package pbouda.sandbox.docker;

import pbouda.sandbox.docker.metrics.Memory;

public class MemoryFootprint {
    public static void main(String[] args) throws InterruptedException {
        Memory.printSummary();
        Thread.currentThread().join();
    }
}
