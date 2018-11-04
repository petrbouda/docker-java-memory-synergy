# JVM DOCKER MEMORY DEFAULTS

- JVM 11 with no special JVM flags

```
docker run -it --memory 512m -v $(pwd)/target/classes:/src -w /src openjdk:11-jre java \
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

Native Memory Tracking:

Total: reserved=1467921KB, committed=41061KB
-                 Java Heap (reserved=131072KB, committed=8192KB)
                            (mmap: reserved=131072KB, committed=8192KB)

-                     Class (reserved=1056951KB, committed=5047KB)
```

- No JVM and Docker flags

=> My limit for Docker-Machine is 8GB
=> Container takes the limit almost everything and use it also for calculation Heap size

```
docker run -it -v $(pwd)/target/classes:/src -w /src openjdk:11-jre java \
-XX:+UnlockDiagnosticVMOptions -XX:NativeMemoryTracking=summary -XX:+PrintNMTStatistics \
--add-exports=java.management/sun.management=docker.limits \
--module-path . --module docker.limits/pbouda.sandbox.docker.MemoryFootprint

HEAP MEMORY
------------------------
INIT: 126 MB
USED: 1 MB
COMMITTED: 126 MB
MAX: 1994 MB

NON-HEAP MEMORY
------------------------
INIT: 7 MB
USED: 2 MB
COMMITTED: 12 MB
MAX: -1 MB

Native Memory Tracking:

Total: reserved=3501136KB, committed=208652KB
-                 Java Heap (reserved=2041856KB, committed=129024KB)
                            (mmap: reserved=2041856KB, committed=129024KB)

-                     Class (reserved=1056948KB, committed=5044KB)
```

```
CONTAINER ID        NAME                CPU %               MEM USAGE / LIMIT     MEM %               NET I/O             BLOCK I/O           PIDS
ae26a5d283f2        objective_pasteur   0.16%               17.02MiB / 7.786GiB   0.21%               688B / 0B           0B / 0B             17

```

- JVM 9 or JVM 10 with `-XX:-UseContainerSupport`

```
docker run -it -m 2g openjdk:9-jre java -XX:+PrintCommandLineFlags -version

-XX:G1ConcRefinementThreads=4 -XX:InitialHeapSize=130634048 -XX:MaxHeapSize=2090144768 
-XX:+PrintCommandLineFlags -XX:ReservedCodeCacheSize=251658240 -XX:+SegmentedCodeCache
-XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseG1GC
openjdk version "9.0.4"
OpenJDK Runtime Environment (build 9.0.4+12-Debian-4)
OpenJDK 64-Bit Server VM (build 9.0.4+12-Debian-4, mixed mode)
```

- JVM 10

```
docker run -it -m 2g openjdk:10-jre java -XX:+PrintCommandLineFlags -version

-XX:G1ConcRefinementThreads=4 -XX:InitialHeapSize=33554432 -XX:MaxHeapSize=536870912 
-XX:+PrintCommandLineFlags -XX:ReservedCodeCacheSize=251658240 -XX:+SegmentedCodeCache 
-XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseG1GC

openjdk version "10.0.2" 2018-07-17
OpenJDK Runtime Environment (build 10.0.2+13-Debian-2)
OpenJDK 64-Bit Server VM (build 10.0.2+13-Debian-2, mixed mode)
```

- -XshowSettings

```
-XshowSettings
-XshowSettings:all
-XshowSettings:vm
-XshowSettings:system
-XshowSettings:locale
-XshowSettings:properties
```

- Memory limits are `Unlimited`

```
docker run -it openjdk:11-jre java -XshowSettings:system -version
Operating System Metrics:
    Provider: cgroupv1
    Effective CPU Count: 4
    CPU Period: 0us
    CPU Quota: 0us
    CPU Shares: -1
    List of Processors, 4 total:
    0 1 2 3
    List of Effective Processors, 4 total:
    0 1 2 3
    List of Memory Nodes, 1 total:
    0
    List of Available Memory Nodes, 1 total:
    0
    CPUSet Memory Pressure Enabled: false
    Memory Limit: Unlimited
    Memory Soft Limit: Unlimited
    Memory & Swap Limit: Unlimited
    Kernel Memory Limit: Unlimited
    TCP Memory Limit: Unlimited
    Out Of Memory Killer Enabled: true

openjdk version "11" 2018-09-25
OpenJDK Runtime Environment (build 11+28-Debian-1)
OpenJDK 64-Bit Server VM (build 11+28-Debian-1, mixed mode, sharing)
```

- Memory limits are adjusted to 2G Docker Container

```
docker run -it -m 2g openjdk:11-jre java -XshowSettings:system -version
Operating System Metrics:
    Provider: cgroupv1
    Effective CPU Count: 4
    CPU Period: 0us
    CPU Quota: 0us
    CPU Shares: -1
    List of Processors, 4 total:
    0 1 2 3
    List of Effective Processors, 4 total:
    0 1 2 3
    List of Memory Nodes, 1 total:
    0
    List of Available Memory Nodes, 1 total:
    0
    CPUSet Memory Pressure Enabled: false
    Memory Limit: 2.00G
    Memory Soft Limit: Unlimited
    Memory & Swap Limit: 4.00G
    Kernel Memory Limit: Unlimited
    TCP Memory Limit: Unlimited
    Out Of Memory Killer Enabled: true

openjdk version "11" 2018-09-25
OpenJDK Runtime Environment (build 11+28-Debian-1)
OpenJDK 64-Bit Server VM (build 11+28-Debian-1, mixed mode, sharing)
```

#### Can I setup more -Xmx than Container Memory?

```
docker run -it --memory 512m -v $(pwd)/target/classes:/src -w /src openjdk:11-jre java -Xmx512m \
-XX:+UnlockDiagnosticVMOptions -XX:NativeMemoryTracking=summary -XX:+PrintNMTStatistics \
--add-exports=java.management/sun.management=docker.limits \
--module-path . --module docker.limits/pbouda.sandbox.docker.MemoryFootprint

HEAP MEMORY
------------------------
INIT: 8 MB
USED: 1 MB
COMMITTED: 7 MB
MAX: 494 MB

Native Memory Tracking:

Total: reserved=1862418KB, committed=41062KB
-                 Java Heap (reserved=524288KB, committed=8192KB)
                            (mmap: reserved=524288KB, committed=8192KB)
```

```
docker run -it --memory 512m -v $(pwd)/target/classes:/src -w /src openjdk:11-jre java -Xmx1g \
-XX:+UnlockDiagnosticVMOptions -XX:NativeMemoryTracking=summary -XX:+PrintNMTStatistics \
--add-exports=java.management/sun.management=docker.limits \
--module-path . --module docker.limits/pbouda.sandbox.docker.MemoryFootprint

HEAP MEMORY
------------------------
INIT: 8 MB
USED: 1 MB
COMMITTED: 7 MB
MAX: 989 MB

Native Memory Tracking:

Total: reserved=2391456KB, committed=44112KB
-                 Java Heap (reserved=1048576KB, committed=8192KB)
                            (mmap: reserved=1048576KB, committed=8192KB)
```