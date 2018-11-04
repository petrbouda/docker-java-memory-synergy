# java-docker-limits

Figure out how you Java project in Docker behaves in your cluster/scheduler/environment.

```
mvn clean install

docker run docker-limits
```

Base image is Java 11 OpenJDK, feel free to change to your version.

Prints
- Runtime arguments
- Information about 
  - Java inside
  - Available processors (e.g. to know how many threads is configured for ForkJoinPool#common)
  - Type of the JIT Compiler
  - Type of the Garbage Collector
  - All types of Memory Pools inside JVM
  - Detailed calculated memory for all Memory Pools

```
docker run docker-limits
#######################
RUNTIME INFO
#######################
OpenJDK 64-Bit Server VM
Oracle Corporation
Version 11+28-Debian-1
HotSpot 64-Bit Tiered Compilers
PID @ Hostname: 8@05667ad8b790
# of Safepoints: 1

Arguments:
--add-exports=java.management/sun.management=docker.limits
--module-path=app/modules
-Djdk.module.main=docker.limits
#######################

Available Processors
-------------------------
4

######################################
THREADS
######################################
# of Threads: 5
Total # of started Threads: 5
Current CPU Time: 165509344
Current User Time: 110000000
Thread Name: main
Thread Name: Reference Handler
Thread Name: Finalizer
Thread Name: Signal Dispatcher
Thread Name: Common-Cleaner
######################################

##################################
MEMORY SUMMARY
##################################

HEAP MEMORY
------------------------
INIT: 126 MB
USED: 3 MB
COMMITTED: 126 MB
MAX: 1994 MB

NON-HEAP MEMORY
------------------------
INIT: 7 MB
USED: 3 MB
COMMITTED: 12 MB
MAX: -1 MB

##################################
MEMORY DETAIL
##################################

CodeHeap 'non-nmethods'
------------------------
INIT: 2 MB
USED: 1 MB
COMMITTED: 2 MB
MAX: 5 MB

CodeHeap 'profiled nmethods'
------------------------
INIT: 2 MB
USED: 0 MB
COMMITTED: 2 MB
MAX: 117 MB

CodeHeap 'non-profiled nmethods'
------------------------
INIT: 2 MB
USED: 0 MB
COMMITTED: 2 MB
MAX: 117 MB

Metaspace
------------------------
INIT: 0 MB
USED: 1 MB
COMMITTED: 4 MB
MAX: -1 MB

Compressed Class Space
------------------------
INIT: 0 MB
USED: 0 MB
COMMITTED: 0 MB
MAX: 1024 MB

G1 Eden Space
------------------------
INIT: 15 MB
USED: 3 MB
COMMITTED: 15 MB
MAX: -1 MB

G1 Survivor Space
------------------------
INIT: 0 MB
USED: 0 MB
COMMITTED: 0 MB
MAX: -1 MB

G1 Old Gen
------------------------
INIT: 111 MB
USED: 0 MB
COMMITTED: 111 MB
MAX: 1994 MB
```
