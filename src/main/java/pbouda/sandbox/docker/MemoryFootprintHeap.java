package pbouda.sandbox.docker;

import pbouda.sandbox.docker.metrics.Memory;

public class MemoryFootprintHeap {
    public static void main(String[] args) {
        Memory.printHeapSummary();
    }
}
