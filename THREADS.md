# THREAD ALLOCATION

- `Without SWAP => spawn thread ~= 100kB Memory`

#### Threads = 0

```
docker run -it --memory 64m -v $(pwd)/target/classes:/src -w /src openjdk:11-jre java \
-XX:+UnlockDiagnosticVMOptions -XX:NativeMemoryTracking=summary -XX:+PrintNMTStatistics \
--add-exports=java.management/sun.management=docker.limits \
--module-path . --module docker.limits/pbouda.sandbox.docker.ThreadAllocation 0

Spawn threads: 0

Total: reserved=1369366KB, committed=42054KB
-                    Thread (reserved=11352KB, committed=736KB)
                            (thread #11)
                            (stack: reserved=11300KB, committed=684KB)
                            (malloc=39KB #59)
                            (arena=13KB #20)
```

#### Threads 100

```
THREADS
Threads / Started Threads: 107 / 107
CPU Time / User Time: 12345711 / 10000000
Thread Name: main
Thread Name: Reference Handler
Thread Name: Finalizer
Thread Name: Signal Dispatcher
Thread Name: Common-Cleaner
Thread Name: Thread-1
Thread Name: Thread-2
.
.
Thread Name: Thread-100
Thread Name: SIGINT handler
Thread Name: Thread-0
```

- Reserved memory is even more then docker container can provide to JVM
=> `21MB in htop`

```
-                    Thread (reserved=115662KB, committed=11734KB)
                            (thread #112)
                            (stack: reserved=115128KB, committed=11200KB)
                            (malloc=403KB #564)
                            (arena=131KB #222)
```

```
CONTAINER ID        NAME                CPU %               MEM USAGE / LIMIT   MEM %               NET I/O             BLOCK I/O           PIDS
8181e8cd78c3        nostalgic_kepler    0.12%               29.01MiB / 64MiB    45.33%              758B / 0B           0B / 0B             112
```

```
top - 15:30:14 up  4:08,  0 users,  load average: 0.06, 0.09, 0.10
Tasks:   3 total,   1 running,   2 sleeping,   0 stopped,   0 zombie
%Cpu(s):  0.1 us,  0.0 sy,  0.0 ni, 99.9 id,  0.0 wa,  0.0 hi,  0.0 si,  0.0 st
MiB Mem :   7973.3 total,   3791.4 free,    340.7 used,   3841.1 buff/cache
MiB Swap:   2560.0 total,   2556.3 free,      3.7 used.   7336.5 avail Mem

  PID USER      PR  NI    VIRT    RES    SHR S  %CPU  %MEM     TIME+ COMMAND
    1 root      20   0 3667188  41944  25060 S   0.0   0.5   0:00.37 java
  119 root      20   0    5544   3404   2980 S   0.0   0.0   0:00.03 bash
  126 root      20   0   15756   3448   2968 R   0.0   0.0   0:00.01 top
```

### Threads 500

=> More total Committed memory then available memory in the Container
=> Much bigger swapped memory inside the container
=> `BLOCK I/O` is going up

```
Total: reserved=1909000KB, committed=89128KB
-                 Java Heap (reserved=32768KB, committed=8192KB)
                            (mmap: reserved=32768KB, committed=8192KB)

-                    Thread (reserved=528772KB, committed=25472KB)
                            (thread #512)
                            (stack: reserved=526328KB, committed=23028KB)
                            (malloc=1844KB #2564)
                            (arena=600KB #1022)
```

```
CONTAINER ID        NAME                  CPU %               MEM USAGE / LIMIT   MEM %               NET I/O             BLOCK I/O           PIDS
ddcf4d7fd938        compassionate_wiles   0.25%               63.6MiB / 64MiB     99.38%              828B / 0B           9.46MB / 25.3MB     512
```

```
top - 15:28:55 up  4:06,  0 users,  load average: 0.11, 0.11, 0.11
Tasks:   3 total,   1 running,   2 sleeping,   0 stopped,   0 zombie
%Cpu(s):  0.0 us,  0.1 sy,  0.0 ni, 99.9 id,  0.0 wa,  0.0 hi,  0.0 si,  0.0 st
MiB Mem :   7973.3 total,   3758.1 free,    374.4 used,   3840.8 buff/cache
MiB Swap:   2560.0 total,   2536.4 free,     23.6 used.   7302.9 avail Mem

  PID USER      PR  NI    VIRT    RES    SHR S  %CPU  %MEM     TIME+ COMMAND
    1 root      20   0 4078388  30780  25036 S   0.3   0.4   0:00.81 java
  527 root      20   0    5544   3476   3044 S   0.0   0.0   0:00.03 bash
  536 root      20   0   15756   3328   2848 R   0.0   0.0   0:00.00 top
```

### Threads 800

```
Total: reserved=2232345KB, committed=99509KB
-                 Java Heap (reserved=32768KB, committed=8192KB)
                            (mmap: reserved=32768KB, committed=8192KB)

-                    Thread (reserved=838604KB, committed=21856KB)
                            (thread #812)
                            (stack: reserved=834728KB, committed=17980KB)
                            (malloc=2924KB #4064)
                            (arena=952KB #1622)
```

```
CONTAINER ID        NAME                CPU %               MEM USAGE / LIMIT   MEM %               NET I/O             BLOCK I/O           PIDS
641fda392879        reverent_jepsen     0.32%               63.64MiB / 64MiB    99.44%              828B / 0B           11.5MB / 46.7MB     812
```

### Threads 900

```
-                    Thread (reserved=941881KB, committed=23929KB)
                            (thread #912)
                            (stack: reserved=937528KB, committed=19576KB)
                            (malloc=3284KB #4564)
                            (arena=1069KB #1822)
```

```
top - 15:25:31 up  4:03,  0 users,  load average: 0.09, 0.14, 0.13
Tasks:   3 total,   1 running,   2 sleeping,   0 stopped,   0 zombie
%Cpu(s):  0.1 us,  0.0 sy,  0.0 ni, 99.9 id,  0.0 wa,  0.0 hi,  0.0 si,  0.0 st
MiB Mem :   7973.3 total,   3758.8 free,    374.2 used,   3840.3 buff/cache
MiB Swap:   2560.0 total,   2523.6 free,     36.4 used.   7303.1 avail Mem

  PID USER      PR  NI    VIRT    RES    SHR S  %CPU  %MEM     TIME+ COMMAND
    1 root      20   0 4490532  25060  25028 S   0.3   0.3   0:01.06 java
  918 root      20   0    5544   3492   3072 S   0.0   0.0   0:00.03 bash
  927 root      20   0   15756   3404   2928 R   0.0   0.0   0:00.03 top
```

```
CONTAINER ID        NAME                CPU %               MEM USAGE / LIMIT   MEM %               NET I/O             BLOCK I/O           PIDS
399f145ea857        adoring_davinci     0.41%               63.76MiB / 64MiB    99.62%              758B / 0B           13.2MB / 58.5MB     912
```

#### Threads 1000

=> Native Memory Tracking output is not generated

```
top - 15:34:20 up  4:12,  0 users,  load average: 0.06, 0.07, 0.08
Tasks:   3 total,   1 running,   2 sleeping,   0 stopped,   0 zombie
%Cpu(s):  0.2 us,  0.1 sy,  0.0 ni, 99.6 id,  0.0 wa,  0.0 hi,  0.1 si,  0.0 st
MiB Mem :   7973.3 total,   3745.5 free,    385.2 used,   3842.6 buff/cache
MiB Swap:   2560.0 total,   2497.6 free,     62.4 used.   7291.7 avail Mem

  PID USER      PR  NI    VIRT    RES    SHR S  %CPU  %MEM     TIME+ COMMAND
    1 root      20   0 4592520  25168  25168 S   0.3   0.3   0:01.13 java
 1019 root      20   0    5544   3476   3052 S   0.0   0.0   0:00.03 bash
 1026 root      20   0   15756   3400   2928 R   0.0   0.0   0:00.00 top
```

#### Threads 2000

=> 

#### Disabled SWAP in the container

- `Threads 100`

```
docker run -it --memory 64m --memory-swap 64m -v $(pwd)/target/classes:/src -w /src openjdk:11-jre java \
-XX:+UnlockDiagnosticVMOptions -XX:NativeMemoryTracking=summary -XX:+PrintNMTStatistics \
--add-exports=java.management/sun.management=docker.limits \
--module-path . --module docker.limits/pbouda.sandbox.docker.ThreadAllocation 100
```

```
Total: reserved=1480256KB, committed=58836KB
-                 Java Heap (reserved=32768KB, committed=8192KB)
                            (mmap: reserved=32768KB, committed=8192KB)
                            
-                    Thread (reserved=116704KB, committed=11852KB)
                            (thread #113)
                            (stack: reserved=116156KB, committed=11304KB)
                            (malloc=415KB #573)
                            (arena=132KB #224)
```

```
CONTAINER ID        NAME                CPU %               MEM USAGE / LIMIT   MEM %               NET I/O             BLOCK I/O           PIDS
63cc37fb090f        kind_ardinghelli    0.14%               28.84MiB / 64MiB    45.06%              828B / 0B           0B / 0B             112
```

- `Threads 200`

```
Total: reserved=1587122KB, committed=73298KB
-                 Java Heap (reserved=32768KB, committed=8192KB)
                            (mmap: reserved=32768KB, committed=8192KB)
                            
-                    Thread (reserved=219974KB, committed=22718KB)
                            (thread #213)
                            (stack: reserved=218956KB, committed=21700KB)
                            (malloc=768KB #1072)
                            (arena=250KB #424)
```

```
CONTAINER ID        NAME                CPU %               MEM USAGE / LIMIT   MEM %               NET I/O             BLOCK I/O           PIDS
f579e05775f4        flamboyant_leakey   0.17%               41.29MiB / 64MiB    64.51%              828B / 0B           0B / 0B             212
```

- `Threads 300`

```
Total: reserved=1694000KB, committed=87800KB
-                 Java Heap (reserved=32768KB, committed=8192KB)
                            (mmap: reserved=32768KB, committed=8192KB)

-                    Thread (reserved=323250KB, committed=33618KB)
                            (thread #313)
                            (stack: reserved=321756KB, committed=32124KB)
                            (malloc=1127KB #1569)
                            (arena=367KB #624)
```

```
CONTAINER ID        NAME                 CPU %               MEM USAGE / LIMIT   MEM %               NET I/O             BLOCK I/O           PIDS
5ff17bd5f9f1        determined_poitras   0.20%               53.73MiB / 64MiB    83.96%              758B / 0B           0B / 0B             312
```

- `Threads 350`

=> No Native Memory Tracking output

```
CONTAINER ID        NAME                CPU %               MEM USAGE / LIMIT   MEM %               NET I/O             BLOCK I/O           PIDS
9d6a3a7f9ebc        elegant_curie       0.22%               60.29MiB / 64MiB    94.21%              758B / 0B           0B / 0B             362
```

- `Threads 400`

=> Failed during a startup, no logs.