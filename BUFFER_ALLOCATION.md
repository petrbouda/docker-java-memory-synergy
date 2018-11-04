# BUFFER ALLOCATION

- Can I see in Native Memory Tracking an allocation of DirectBuffers?

#### DirectBuffer - 512MB, Container - 256MB

```
docker run -it --memory 256m -v $(pwd)/target/classes:/src -w /src openjdk:11-jre java \
-XX:+UnlockDiagnosticVMOptions -XX:NativeMemoryTracking=summary -XX:+PrintNMTStatistics \
--add-exports=java.management/sun.management=docker.limits \
--module-path . --module docker.limits/pbouda.sandbox.docker.BufferAllocation

Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
	at java.base/java.nio.Bits.reserveMemory(Bits.java:175)
	at java.base/java.nio.DirectByteBuffer.<init>(DirectByteBuffer.java:118)
	at java.base/java.nio.ByteBuffer.allocateDirect(ByteBuffer.java:317)
	at docker.limits/pbouda.sandbox.docker.BufferAllocation.main(BufferAllocation.java:8)
```

#### DirectBuffer - 512MB, Container - 512MB

```
docker run -it --memory 512m  ..

Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
	at java.base/java.nio.Bits.reserveMemory(Bits.java:175)
	at java.base/java.nio.DirectByteBuffer.<init>(DirectByteBuffer.java:118)
	at java.base/java.nio.ByteBuffer.allocateDirect(ByteBuffer.java:317)
	at docker.limits/pbouda.sandbox.docker.BufferAllocation.main(BufferAllocation.java:8)
```

#### DirectBuffer - 512MB, Container - 1GB, Xmx - 256MB

```
docker run -it --memory 1g -v $(pwd)/target/classes:/src -w /src openjdk:11-jre java -Xmx256m ..

Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
	at java.base/java.nio.Bits.reserveMemory(Bits.java:175)
	at java.base/java.nio.DirectByteBuffer.<init>(DirectByteBuffer.java:118)
	at java.base/java.nio.ByteBuffer.allocateDirect(ByteBuffer.java:317)
	at docker.limits/pbouda.sandbox.docker.BufferAllocation.main(BufferAllocation.java:8)
```

#### DirectBuffer - 512MB, Container - 1GB, Xmx - 700MB

=> Direct buffer allocated in OTHER section
=> JVM still needs appropriate size of memory to be able to allocate it.
    - Xmx = 529M is absolute minimal size

```
-                     Other (reserved=524298KB, committed=524298KB)
                            (malloc=524298KB #3)
```

```
docker run -it --memory 1g -v $(pwd)/target/classes:/src -w /src openjdk:11-jre java -Xmx700m \
-XX:+UnlockDiagnosticVMOptions -XX:NativeMemoryTracking=summary -XX:+PrintNMTStatistics \
--add-exports=java.management/sun.management=docker.limits \
--module-path . --module docker.limits/pbouda.sandbox.docker.BufferAllocation

Direct allocation: 536870912

Native Memory Tracking:

Total: reserved=2579787KB, committed=573507KB
-                 Java Heap (reserved=716800KB, committed=16384KB)
                            (mmap: reserved=716800KB, committed=16384KB)

-                     Class (reserved=1056934KB, committed=5030KB)
                            (classes #945)
                            (  instance classes #832, array classes #113)
                            (malloc=166KB #1206)
                            (mmap: reserved=1056768KB, committed=4864KB)
                            (  Metadata:   )
                            (    reserved=8192KB, committed=4352KB)
                            (    used=909KB)
                            (    free=3443KB)
                            (    waste=0KB =0.00%)
                            (  Class space:)
                            (    reserved=1048576KB, committed=512KB)
                            (    used=109KB)
                            (    free=403KB)
                            (    waste=0KB =0.00%)

-                    Thread (reserved=12385KB, committed=841KB)
                            (thread #12)
                            (stack: reserved=12328KB, committed=784KB)
                            (malloc=43KB #64)
                            (arena=14KB #22)

-                      Code (reserved=247745KB, committed=7605KB)
                            (malloc=57KB #631)
                            (mmap: reserved=247688KB, committed=7548KB)

-                        GC (reserved=2376KB, committed=100KB)
                            (malloc=36KB #154)
                            (mmap: reserved=2340KB, committed=64KB)

-                  Compiler (reserved=133KB, committed=133KB)
                            (malloc=2KB #44)
                            (arena=131KB #5)

-                  Internal (reserved=507KB, committed=507KB)
                            (malloc=467KB #987)
                            (mmap: reserved=40KB, committed=40KB)

-                     Other (reserved=524298KB, committed=524298KB)
                            (malloc=524298KB #3)

-                    Symbol (reserved=1107KB, committed=1107KB)
                            (malloc=747KB #2493)
                            (arena=360KB #1)

-    Native Memory Tracking (reserved=116KB, committed=116KB)
                            (malloc=4KB #52)
                            (tracking overhead=112KB)

-        Shared class space (reserved=17144KB, committed=17144KB)
                            (mmap: reserved=17144KB, committed=17144KB)

-               Arena Chunk (reserved=174KB, committed=174KB)
                            (malloc=174KB)

-                   Logging (reserved=4KB, committed=4KB)
                            (malloc=4KB #179)

-                 Arguments (reserved=18KB, committed=18KB)
                            (malloc=18KB #476)

-                    Module (reserved=48KB, committed=48KB)
                            (malloc=48KB #820)
```

```
CONTAINER ID        NAME                CPU %               MEM USAGE / LIMIT   MEM %               NET I/O             BLOCK I/O           PIDS
0f7828250b48        pensive_dijkstra    0.13%               525.7MiB / 1GiB     51.34%              898B / 0B           0B / 0B             12
```