# JVM DOCKER MEMORY DEFAULTS

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
