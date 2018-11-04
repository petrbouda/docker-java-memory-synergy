# MAX RAM OPTION

`Default Xmx = MaxRAM / MaxRAMFraction`

```
java -XX:+PrintFlagsFinal -version | grep MaxRAM
 uint64_t MaxRAM                                   = 137438953472                           {pd product} {default}
    uintx MaxRAMFraction                           = 4                                         {product} {default}
   double MaxRAMPercentage                         = 25.000000                                 {product} {default}
```

```
docker run -it --memory 512m -v $(pwd)/target/classes:/src -w /src openjdk:11-jre java -XX:MaxRAM=512m \
-XX:+UnlockDiagnosticVMOptions -XX:NativeMemoryTracking=summary -XX:+PrintNMTStatistics \
--add-exports=java.management/sun.management=docker.limits \
--module-path . --module docker.limits/pbouda.sandbox.docker.MemoryFootprint

HEAP MEMORY
------------------------
INIT: 8 MB
USED: 1 MB
COMMITTED: 7 MB
MAX: 123 MB

NON-HEAP MEMORY
------------------------
INIT: 7 MB
USED: 2 MB
COMMITTED: 12 MB
MAX: -1 MB
```

- Useful Option at the time of missing `Container Support` in Java, now it does not make any sense, Heap is calculated even from container's limits

```
Native Memory Tracking:

Total: reserved=1470611KB, committed=43751KB
-                 Java Heap (reserved=131072KB, committed=8192KB)
                            (mmap: reserved=131072KB, committed=8192KB)

-                     Class (reserved=1056951KB, committed=5047KB)
                            (classes #979)
                            (  instance classes #865, array classes #114)
                            (malloc=183KB #1376)
                            (mmap: reserved=1056768KB, committed=4864KB)
                            (  Metadata:   )
                            (    reserved=8192KB, committed=4352KB)
                            (    used=1016KB)
                            (    free=3337KB)
                            (    waste=0KB =0.00%)
                            (  Class space:)
                            (    reserved=1048576KB, committed=512KB)
                            (    used=128KB)
                            (    free=384KB)
                            (    waste=0KB =0.00%)

-                    Thread (reserved=12385KB, committed=845KB)
                            (thread #12)
                            (stack: reserved=12328KB, committed=788KB)
                            (malloc=43KB #64)
                            (arena=14KB #22)

-                      Code (reserved=247755KB, committed=7615KB)
                            (malloc=67KB #634)
                            (mmap: reserved=247688KB, committed=7548KB)

-                        GC (reserved=469KB, committed=73KB)
                            (malloc=37KB #165)
                            (mmap: reserved=432KB, committed=36KB)

-                  Compiler (reserved=135KB, committed=135KB)
                            (malloc=4KB #61)
                            (arena=131KB #5)

-                  Internal (reserved=510KB, committed=510KB)
                            (malloc=470KB #1020)
                            (mmap: reserved=40KB, committed=40KB)

-                     Other (reserved=10KB, committed=10KB)
                            (malloc=10KB #2)

-                    Symbol (reserved=1126KB, committed=1126KB)
                            (malloc=766KB #2595)
                            (arena=360KB #1)

-    Native Memory Tracking (reserved=122KB, committed=122KB)
                            (malloc=4KB #52)
                            (tracking overhead=118KB)

-        Shared class space (reserved=17144KB, committed=17144KB)
                            (mmap: reserved=17144KB, committed=17144KB)

-               Arena Chunk (reserved=2862KB, committed=2862KB)
                            (malloc=2862KB)

-                   Logging (reserved=4KB, committed=4KB)
                            (malloc=4KB #179)

-                 Arguments (reserved=18KB, committed=18KB)
                            (malloc=18KB #476)

-                    Module (reserved=48KB, committed=48KB)
                            (malloc=48KB #820)
```