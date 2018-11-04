package pbouda.sandbox.docker;

import java.nio.ByteBuffer;

public class BufferAllocation {

    public static void main(String[] args) throws Exception {
        ByteBuffer humonguousBuffer = ByteBuffer.allocateDirect(512 * 1024 * 1024);

        System.out.println("Direct allocation: " + humonguousBuffer.capacity());

        Thread.currentThread().join();
    }
}
