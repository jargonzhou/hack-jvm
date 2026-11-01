# Java Performance

* Oaks, Scott. Java Performance. 2020, 2. edition. O'Reilly Media.
  * JDK 8, 11.

| #    | Title                                     |
| :--- | :---------------------------------------- |
| 1    | Introduction                              |
| 2    | An Approach to Performance Testing        |
| 3    | A Java Performance Toolbox                |
| 4    | Working with the JIT Compiler             |
| 5    | An Introduction to Garbage Collection     |
| 6    | Garbage Collection Algorithms             |
| 7    | Heap Memory Best Practices                |
| 8    | Native Memory Best Practices              |
| 9    | Threading and Synchronization Performance |
| 10   | Java Servers                              |
| 11   | Database Performance Best Practices       |
| 12   | Java SE API Tips                          |


# Java Performance

books:

# Tools
- jcmd
- jconsole
- jmap
- jinfo
- jstack
- jstat
- jvisualvm


- Eclipse Memory Analyzer

- [Faban](http://faban.org/)： Faban is a free and open source performance workload creation and execution framework. Faban is used in performance, scalability and load testing of almost any type of server application. If the application accepts requests on a network, Faban can measure it.
- Apache JMeter
- Gatling
- Micro Focus LoadRunner


- Java Instrument: [Guide to Java Instrumentation](https://www.baeldung.com/java-instrumentation), [Instrumentation API](https://docs.oracle.com/javase/7/docs/api/java/lang/instrument/package-summary.html), [Attach API](https://docs.oracle.com/javase/7/docs/jdk/api/attach/spec/com/sun/tools/attach/package-summary.html)
- JProfiler
- [BTrace](https://github.com/btraceio/btrace)
> BTrace - a safe, dynamic tracing tool for the Java platform

## jmh: Java Microbenchmark Harness
- Code: [Java Microbenchmark Harness (JMH)](https://github.com/openjdk/jmh)

> JMH(Java Microbenchmark Harness)
>
> JMH is a Java harness for building, running, and analysing nano/micro/milli/macro benchmarks written in Java and other languages targeting the JVM.

actions: 
- [example-jmh](./tools/example-jmh/README.md)
```shell
mvn archetype:generate \
      -DinteractiveMode=false \
      -DarchetypeGroupId=org.openjdk.jmh \
      -DarchetypeArtifactId=jmh-java-benchmark-archetype \
      -DgroupId=[GROUP_ID] \
      -DartifactId=[ARTIFACT_ID] \
      -Dversion=1.0
```

```shell
$ mvn clean verify
$ java -jar target/benchmarks.jar -h
```
examples:
- [How to Warm Up the JVM](https://www.baeldung.com/java-jvm-warmup)
- [Microbenchmarking with Java](https://www.baeldung.com/java-microbenchmark-harness)

| File                                     | Description                                                                     |
| :--------------------------------------- | :------------------------------------------------------------------------------ |
| JMHSample_01_HelloWorld.java             | `@Benchmark`, `Runner`                                                          |
| JMHSample_02_BenchmarkModes.java         | `@BenchmarkMode`, `@OutputTimeUnit`                                             |
| JMHSample_03_States.java                 | `@State` on inner `static` class.                                               |
| JMHSample_04_DefaultState.java           | `@State` on class.                                                              |
| JMHSample_05_StateFixtures.java          | `@State` with `@Setup` and `@TearDown`                                          |
| JMHSample_06_FixtureLevel.java           | `@TearDown(Level.Iteration)`                                                    |
| JMHSample_07_FixtureLevelInvocation.java | `@Setup(Level.Invocation)`                                                      |
| JMHSample_08_DeadCode.java               | Dead-Code Elimination (DCE)                                                     |
| JMHSample_09_Blackholes.java             | `Blackhole`                                                                     |
| JMHSample_10_ConstantFold.java           | constant-folding                                                                |
| JMHSample_11_Loops.java                  | `@OperationsPerInvocation`                                                      |
| JMHSample_12_Forking.java                | `@Fork`                                                                         |
| JMHSample_13_RunToRun.java               | `@Fork` allows to estimate run-to-run variance                                  |
| JMHSample_15_Asymmetric.java             | `@State(Scope.Group)`, `@Group`, `@GroupThreads`                                |
| JMHSample_16_CompilerControl.java        | `@CompilerControl`                                                              |
| JMHSample_17_SyncIterations.java         | `syncIterations(true)`                                                          |
| JMHSample_18_Control.java                | `Control#stopMeasurement()`                                                     |
| JMHSample_20_Annotations.java            | `@Warmup`, `@Measurement`                                                       |
| JMHSample_21_ConsumeCPU.java             | `Blackhole#consumeCPU`                                                          |
| JMHSample_22_FalseSharing.java           | `@States` are automatically padded, some tricks.                                |
| JMHSample_23_AuxCounters.java            | `@AuxCounters` for user counters.                                               |
| JMHSample_24_Inheritance.java            | `@Benchmark` in inheritance.                                                    |
| JMHSample_25_API_GA.java                 | Genetic Algorithms for tail-call optimizations(TCO) JVM args.                   |
| JMHSample_26_BatchSize.java              | `batchSize` for workload with no steady state.                                  |
| JMHSample_27_Params.java                 | `@Param` for configuration.                                                     |
| JMHSample_28_BlackholeHelpers.java       | `Blackhole` in helper methods.                                                  |
| JMHSample_29_StatesDAG.java              | `@State` DAG.                                                                   |
| JMHSample_30_Interrupts.java             | `timeout` for `InterruptedException`.                                           |
| JMHSample_31_InfraParams.java            | `BenchmarkParams`, `ThreadParams`.                                              |
| JMHSample_32_BulkWarmup.java             | `WarmupMode.BULK`                                                               |
| JMHSample_33_SecurityManager.java        | for `SecurityManager`                                                           |
| JMHSample_34_SafeLooping.java            | safe looping tricks.                                                            |
| JMHSample_35_Profilers.java              | `-prof` profilers, with example `stack` and `gc`.                               |
| JMHSample_36_BranchPrediction.java       | explore branch prediction with `-prof perfnorm`.                                |
| JMHSample_37_CacheAccess.java            | explore cache access with `-prof perfnorm`.                                     |
| JMHSample_38_PerInvokeSetup.java         | explore mistake in non-steady-state benchmarks.                                 |
| JMHSample_39_MemoryAccess.java           | explore memory access in benchmark. introduce a tool Java Object Layout (JOL) . |

Annotations:

| Annotation                 | Description |
| :------------------------- | :---------- |
| `@AuxCounters`             |             |
| `@Benchmark`               |             |
| `@BenchmarkMode`           |             |
| `@CompilerControl`         |             |
| `@Fork`                    |             |
| `@Group`                   |             |
| `@GroupThreads`            |             |
| `@Level`                   |             |
| `@Measurement`             |             |
| `@Mode`                    |             |
| `@OperationsPerInvocation` |             |
| `@OutputTimeUnit`          |             |
| `@Param`                   |             |
| `@Scope`                   |             |
| `@Setup`                   |             |
| `@State`                   |             |
| `@TearDown`                |             |
| `@Threads`                 |             |
| `@Timeout`                 |             |
| `@Warmup`                  |             |


Runner:

# Tuning Flags
## Flags to tune the just-in-time compiler

| Flag                                 | What it does                                                               | When to use it                                                                                                                                       | See also                           |
| ------------------------------------ | -------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------- |
| `-server`                            | This flag no longer has any effect; it is silently ignored.                | N/A                                                                                                                                                  | Tiered Compilation                 |
| `-client`                            | This flag no longer has any effect; it is silently ignored.                | N/A                                                                                                                                                  | Tiered Compilation                 |
| `-XX:+TieredCompilation`             | Uses tiered compilation.                                                   | Always, unless you are severely constrained for memory.                                                                                              | Tiered Compilation                 |
| `-XX:ReservedCodeCacheSize=`_`<MB>`_ | Reserves space for code compiled by the JIT compiler.                      | When running a large program and you see a warning that you are out of code cache.                                                                   | Tuning the Code Cache              |
| `-XX:InitialCodeCacheSize=`_`<MB>`_  | Allocates the initial space for code compiled by the JIT compiler.         | If you need to preallocate the memory for the code cache (which is uncommon).                                                                        | Tuning the Code Cache              |
| `-XX:CompileThreshold=`_`<N>`_       | Sets the number of times a method or loop is executed before compiling it. | This flag is no longer recommended.                                                                                                                  | Compilation Thresholds             |
| `-XX:+PrintCompilation`              | Provides a log of operations by the JIT compiler.                          | When you suspect an important method isn’t being compiled or are generally curious as to what the compiler is doing.                                 | Inspecting the Compilation Process |
| `-XX:CICompilerCount=`_`<N>`_        | Sets the number of threads used by the JIT compiler.                       | When too many compiler threads are being started. This primarily affects large machines running many JVMs.                                           | Compilation Threads                |
| `-XX:+DoEscapeAnalysis`              | Enables aggressive optimizations by the compiler.                          | On rare occasions, this can trigger crashes, so it is sometimes recommended to be disabled. Don’t disable it unless you know it is causing an issue. | Escape Analysis                    |
| `-XX:UseAVX=`_`<N>`_                 | Sets the instruction set for use on Intel processors.                      | You should set this to 2 in early versions of Java 11; in later versions, it defaults to 2.                                                          | CPU-Specific Code                  |
| `-XX:AOTLibrary=`_`<path>`_          | Uses the specified library for ahead-of-time compilation.                  | In limited cases, may speed up initial program execution. Experimental in Java 11 only.                                                              | Ahead-of-Time Compilation          |

## Flags to choose the GC algorithm

| Flag                      | What it does                                                                                                                                                                         | When to use it                                                                                                   | See also                                  |
| ------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ---------------------------------------------------------------------------------------------------------------- | ----------------------------------------- |
| `-XX:+UseSerialGC`        | Uses a simple, single-threaded GC algorithm.                                                                                                                                         | For single-core virtual machines and containers, or for small (100 MB) heaps.                                    | The serial garbage collector              |
| `-XX:+UseParallelGC`      | Uses multiple threads to collect both the young and old generations while application threads are stopped.                                                                           | Use to tune for throughput rather than responsiveness; default in Java 8.                                        | The throughput collector                  |
| `-XX:+UseG1GC`            | Uses multiple threads to collect the young generation while application threads are stopped, and background thread(s) to remove garbage from the old generation with minimal pauses. | When you have available CPU for the background thread(s) and you do not want long GC pauses. Default in Java 11. | The G1 GC collector                       |
| `-XX:+UseConcMarkSweepGC` | Uses background thread(s) to remove garbage from the old generation with minimal pauses.                                                                                             | No longer recommended; use G1 GC instead.                                                                        | The CMS collector                         |
| `-XX:+UseParNewGC`        | With CMS, uses multiple threads to collect the young generation while application threads are stopped.                                                                               | No longer recommended; use G1 GC instead.                                                                        | The CMS collector                         |
| `-XX:+UseZGC`             | Uses the experimental Z Garbage Collector (Java 12 only).                                                                                                                            | To have shorter pauses for young GC, which is collected concurrently.                                            | Concurrent Compaction: ZGC and Shenandoah |
| `-XX:+UseShenandoahGC`    | Uses the experimental Shenandoah Garbage Collector (Java 12 OpenJDK only).                                                                                                           | To have shorter pauses for young GC, which is collected concurrently.                                            | Concurrent Compaction: ZGC and Shenandoah |
| `-XX:+UseEpsilonGC`       | Uses the experimental Epsilon Garbage Collector (Java 12 only).                                                                                                                      | If your app never needs to perform GC.                                                                           | No Collection: Epsilon GC                 |

## Flags common to all GC algorithms

| Flag                                   | What it does                                                                                                                                                                       | When to use it                                                                                                                                                                                                                                                                                                                                                                                | See also                                          |
| -------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------- |
| `-Xms`                                 | Sets the initial size of the heap.                                                                                                                                                 | When the default initial size is too small for your application.                                                                                                                                                                                                                                                                                                                              | Sizing the Heap                                   |
| `-Xmx`                                 | Sets the maximum size of the heap.                                                                                                                                                 | When the default maximum size is too small (or possibly too large) for your application.                                                                                                                                                                                                                                                                                                      | Sizing the Heap                                   |
| `-XX:NewRatio`                         | Sets the ratio of the young generation to the old generation.                                                                                                                      | Increase this to reduce the proportion of the heap given to the young generation; lower it to increase the proportion of the heap given to the young generation. This is only an initial setting; the proportion will change unless adaptive sizing is turned off. As the young-generation size is reduced, you will see more frequent young GCs and less frequent full GCs (and vice versa). | Sizing the Generations                            |
| `-XX:NewSize`                          | Sets the initial size of the young generation.                                                                                                                                     | When you have finely tuned your application requirements.                                                                                                                                                                                                                                                                                                                                     | Sizing the Generations                            |
| `-XX:MaxNewSize`                       | Sets the maximum size of the young generation.                                                                                                                                     | When you have finely tuned your application requirements.                                                                                                                                                                                                                                                                                                                                     | Sizing the Generations                            |
| `-Xmn`                                 | Sets the initial and maximum size of the young generation.                                                                                                                         | When you have finely tuned your application requirements.                                                                                                                                                                                                                                                                                                                                     | Sizing the Generations                            |
| ``-XX:MetaspaceSize=_`N`_``            | Sets the initial size of the metaspace.                                                                                                                                            | For applications that use a lot of classes, increase this from the default.                                                                                                                                                                                                                                                                                                                   | Sizing Metaspace                                  |
| ``-XX:MaxMetaspaceSize=_`N`_``         | Sets the maximum size of the metaspace.                                                                                                                                            | Lower this number to limit the amount of native space used by class metadata.                                                                                                                                                                                                                                                                                                                 | Sizing Metaspace                                  |
| ``-XX:ParallelGCThreads=_`N`_``        | Sets the number of threads used by the garbage collectors for foreground activities (e.g., collecting the young generation, and for throughput GC, collecting the old generation). | Lower this value on systems running many JVMs, or in Docker containers on Java 8 before update 192. Consider increasing it for JVMs with very large heaps on very large systems.                                                                                                                                                                                                              | Controlling Parallelism                           |
| `-XX:+UseAdaptiveSizePolicy`           | When set, the JVM will resize various heap sizes to attempt to meet GC goals.                                                                                                      | Turn this off if the heap sizes have been finely tuned.                                                                                                                                                                                                                                                                                                                                       | Adaptive sizing                                   |
| `-XX:+PrintAdaptiveSizePolicy`         | Adds information about how generations are resized to the GC log.                                                                                                                  | Use this flag to gain an understanding of how the JVM is operating. When using G1, check this output to see if full GCs are triggered by humongous object allocation.                                                                                                                                                                                                                         | Adaptive sizing                                   |
| `-XX:+PrintTenuringDistribution`       | Adds tenuring information to the GC logs.                                                                                                                                          | Use the tenuring information to determine if and how the tenuring options should be adjusted.                                                                                                                                                                                                                                                                                                 | Tenuring and Survivor Spaces                      |
| ``-XX:InitialSurvivorRatio=_`N`_``     | Sets the amount of the young generation set aside for survivor spaces.                                                                                                             | Increase this if short-lived objects are being promoted into the old generation too frequently.                                                                                                                                                                                                                                                                                               | Tenuring and Survivor Spaces                      |
| ``-XX:MinSurvivorRatio=_`N`_``         | Sets the adaptive amount of the young generation set aside for survivor spaces.                                                                                                    | Decreasing this value reduces the maximum size of the survivor spaces (and vice versa).                                                                                                                                                                                                                                                                                                       | Tenuring and Survivor Spaces                      |
| ``-XX:TargetSurvivorRatio=_`N`_``      | The amount of free space the JVM attempts to keep in the survivor spaces.                                                                                                          | Increasing this value reduces the size of the survivor spaces (and vice versa).                                                                                                                                                                                                                                                                                                               | Tenuring and Survivor Spaces                      |
| ``-XX:InitialTenuringThreshold=_`N`_`` | The initial number of GC cycles the JVM attempts to keep an object in the survivor spaces.                                                                                         | Increase this number to keep objects in the survivor spaces longer, though be aware that the JVM will tune it.                                                                                                                                                                                                                                                                                | Tenuring and Survivor Spaces                      |
| ``-XX:MaxTenuringThreshold=_`N`_``     | The maximum number of GC cycles the JVM attempts to keep an object in the survivor spaces.                                                                                         | Increase this number to keep objects in the survivor spaces longer; the JVM will tune the actual threshold between this value and the initial threshold.                                                                                                                                                                                                                                      | Tenuring and Survivor Spaces                      |
| `-XX:+DisableExplicitGC>`              | Prevents calls to `System.gc()` from having any effect.                                                                                                                            | Use to prevent bad applications from explicitly performing GC.                                                                                                                                                                                                                                                                                                                                | Causing and Disabling Explicit Garbage Collection |
| `-XX:-AggressiveHeap`                  | Enables a set of tuning flags that are “optimized for machines with a large amount of memory running a single JVM with a large heap.                                               | It is better not to use this flag, and instead use specific flags as necessary.                                                                                                                                                                                                                                                                                                               | AggressiveHeap                                    |

## Flags controlling GC logging

| Flag                             | What it does                                                                                       | When to use it                                                                                                                                                                                                               | See also                         |
| -------------------------------- | -------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------- |
| `-Xlog:gc*`                      | Controls GC logging in Java 11.                                                                    | GC logging should always be enabled, even in production. Unlike the following set of flags for Java 8, this flag controls all options to Java 11 GC logging; see the text for a mapping of options for this to Java 8 flags. | GC Tools                         |
| `-verbose:gc`                    | Enables basic GC logging in Java 8.                                                                | GC logging should always be enabled, but other, more detailed logs are generally better.                                                                                                                                     | GC Tools                         |
| `-Xloggc:`_`<path>`_             | In Java 8, directs the GC log to a special file rather than standard output.                       | Always, the better to preserve the information in the log.                                                                                                                                                                   | GC Tools                         |
| `-XX:+PrintGC`                   | Enables basic GC logging in Java 8.                                                                | GC logging should always be enabled, but other, more detailed logs are generally better.                                                                                                                                     | GC Tools                         |
| `-XX:+PrintGCDetails`            | Enables detailed GC logging in Java 8.                                                             | Always, even in production (the logging overhead is minimal).                                                                                                                                                                | GC Tools                         |
| `-XX:+PrintGCTimeStamps`         | Prints a relative timestamp for each entry in the GC log in Java 8.                                | Always, unless datestamps are enabled.                                                                                                                                                                                       | GC Tools                         |
| `-XX:+PrintGCDateStamps`         | Prints a time-of-day stamp for each entry in the GC log in Java 8.                                 | Has slightly more overhead than timestamps, but may be easier to process.                                                                                                                                                    | GC Tools                         |
| `-XX:+PrintReferenceGC`          | Prints information about soft and weak reference processing during GC in Java 8.                   | If the program uses a lot of those references, add this flag to determine their effect on the GC overhead.                                                                                                                   | Soft, Weak, and Other References |
| `-XX:+UseGCLogFileRotation`      | Enables rotations of the GC log to conserve file space in Java 8.                                  | In production systems that run for weeks at a time when the GC logs can be expected to consume a lot of space.                                                                                                               | GC Tools                         |
| ``-XX:NumberOfGCLogFiles=_`N`_`` | When logfile rotation is enabled in Java 8, indicates the number of logfiles to retain.            | In production systems that run for weeks at a time when the GC logs can be expected to consume a lot of space.                                                                                                               | GC Tools                         |
| ``-XX:GCLogFileSize=_`N`_``      | When logfile rotation is enabled in Java 8, indicates the size of each logfile before rotating it. | In production systems that run for weeks at a time when the GC logs can be expected to consume a lot of space.                                                                                                               | GC Tools                         |

## Flags for the throughput collector

| Flag                           | What it does                                                                                                                                | When to use it                                                                                                         | See also                             |
| ------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------- | ------------------------------------ |
| ``-XX:MaxGCPauseMillis=_`N`_`` | Hints to the throughput collector how long pauses should be; the heap is dynamically sized to attempt to meet that goal.                    | As a first step in tuning the throughput collector if the default sizing it calculates doesn’t meet application goals. | Adaptive and Static Heap Size Tuning |
| ``-XX:GCTimeRatio=_`N`_``      | Hints to the throughput collector how much time you are willing to spend in GC; the heap is dynamically sized to attempt to meet that goal. | As a first step in tuning the throughput collector if the default sizing it calculates doesn’t meet application goals. | Adaptive and Static Heap Size Tuning |

## Flags for the G1 collector

| Flag                                         | What it does                                                                                                            | When to use it                                                                                                 | See also                               |
| -------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------- | -------------------------------------- |
| ``-XX:MaxGCPauseMillis=_`N`_``               | Hints to the G1 collector how long pauses should be; the G1 algorithm is adjusted to attempt to meet that goal.         | As a first step in tuning the G1 collector; increase this value to attempt to prevent full GCs.                | Tuning G1 GC                           |
| ``-XX:ConcGCThreads=_`N`_``                  | Sets the number of threads to use for G1 background scanning.                                                           | When lots of CPU is available and G1 is experiencing concurrent mode failures.                                 | Tuning G1 GC                           |
| ``-XX:InitiatingHeapOccupancyPercent=_`N`_`` | Sets the point at which G1 background scanning begins.                                                                  | Lower this value if G1 is experiencing concurrent mode failures.                                               | Tuning G1 GC                           |
| ``-XX:G1MixedGCCountTarget=_`N`_``           | Sets the number of mixed GCs over which G1 attempts to free regions previously identified as containing mostly garbage. | Lower this value if G1 is experiencing concurrent mode failures; increase it if mixed GC cycles take too long. | Tuning G1 GC                           |
| ``-XX:G1MixedGCCountTarget=_`N`_``           | Sets the number of mixed GCs over which G1 attempts to free regions previously identified as containing mostly garbage. | Lower this value if G1 is experiencing concurrent mode failures; increase it if mixed GC cycles take too long. | Tuning G1 GC                           |
| ``-XX:G1HeapRegionSize=_`N`_``               | Sets the size of a G1 region.                                                                                           | Increase this value for very large heaps, or when the application allocates very, very large objects.          | G1 GC region sizes                     |
| `-XX:+UseStringDeduplication`                | Allows G1 to eliminate duplicate strings.                                                                               | Use for programs that have a lot of duplicate strings and when interning is impractical.                       | Duplicate Strings and String Interning |

## Flags for the CMS collector

| Flag                                        | What it does                                                                                                | When to use it                                                                  | See also                        |
| ------------------------------------------- | ----------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------- | ------------------------------- |
| `-XX:CMSInitiating​OccupancyFraction``=_N_` | Determines when CMS should begin background scanning of the old generation.                                 | When CMS experiences concurrent mode failures, reduces this value.              | Understanding the CMS Collector |
| `-XX:+UseCMSInitiating​OccupancyOnly`       | Causes CMS to use only `CMSInitiatingOccupancyFraction` to determine when to start CMS background scanning. | Whenever `CMSInitiatingOccupancyFraction` is specified.                         | Understanding the CMS Collector |
| ``-XX:ConcGCThreads=_`N`_``                 | Sets the number of threads to use for CMS background scanning.                                              | When lots of CPU is available and CMS is experiencing concurrent mode failures. | Understanding the CMS Collector |
| `-XX:+CMSIncrementalMode`                   | Runs CMS in incremental mode.                                                                               | No longer supported.                                                            | N/A                             |

## Flags for memory management

| Flag                                  | What it does                                                                                                  | When to use it                                                                                                                                                                          | See also                               |
| ------------------------------------- | ------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------- |
| `-XX:+HeapDumpOnOutOfMemoryError`     | Generates a heap dump when the JVM throws an out-of-memory error.                                             | Enable this flag if the application throws out-of-memory errors due to the heap space or permgen, so the heap can be analyzed for memory leaks.                                         | Out-of-Memory Errors                   |
| `-XX:HeapDumpPath=<path>`             | Specifies the filename where automatic heap dumps should be written.                                          | To specify a path other than _java_pid<pid>.hprof_ for heap dumps generated on out-of-memory errors or GC events (when those options have been enabled).                                | Out-of-Memory Errors                   |
| `-XX:GCTimeLimit=<N>`                 | Specifies the amount of time the JVM can spend performing GC without throwing an `OutOfMemoryException`.      | Lower this value to have the JVM throw an OOME sooner when the program is executing too many GC cycles.                                                                                 | Out-of-Memory Errors                   |
| `-XX:HeapFreeLimit=<N>`               | Specifies the amount of memory the JVM must free to prevent throwing an `OutOfMemoryException`.               | Lower this value to have the JVM throw an OOME sooner when the program is executing too many GC cycles.                                                                                 | Out-of-Memory Errors                   |
| ``-XX:SoftRefLRUPolicyMSPerMB=_`N`_`` | Controls how long soft references survive after being used.                                                   | Decrease this value to clean up soft references more quickly, particularly in low-memory conditions.                                                                                    | Soft, Weak, and Other References       |
| ``-XX:MaxDirectMemorySize=_`N`_``     | Controls how much native memory can be allocated via the `allocateDirect()` method of the `ByteBuffer` class. | Consider setting this if you want to limit the amount of direct memory a program can allocate. It is no longer necessary to set this flag to allocate more than 64 MB of direct memory. | Native NIO buffers                     |
| `-XX:+UseLargePages`                  | Directs the JVM to allocate pages from the operating system’s large page system, if applicable.               | If supported by the OS, this option will generally improve performance.                                                                                                                 | Large Pages                            |
| ``-XX:+StringTableSize=_`N`_``        | Sets the size of the hash table the JVM uses to hold interned strings.                                        | Increase this value if the application performs a significant amount of string interning.                                                                                               | Duplicate Strings and String Interning |
| `-XX:+UseCompressedOops`              | Emulates 35-bit pointers for object references.                                                               | This is the default for heaps that are less than 32 GB in size; there is never an advantage to disabling it.                                                                            | Compressed Oops                        |
| `-XX:+PrintTLAB`                      | Prints summary information about TLABs in the GC log.                                                         | When using a JVM without support for JFR, use this to ensure that TLAB allocation is working efficiently.                                                                               | Thread-local allocation buffers        |
| ``-XX:TLABSize=_`N`_``                | Sets the size of the TLABs.                                                                                   | When the application is performing a lot of allocation outside TLABs, use this value to increase the TLAB size.                                                                         | Thread-local allocation buffers        |
| `-XX:-ResizeTLAB`                     | Disables resizing of TLABs.                                                                                   | Whenever `TLABSize` is specified, make sure to disable this flag.                                                                                                                       | Thread-local allocation buffers        |

## Flags for native memory tracking

| Flag                           | What it does                                                          | When to use it                                                      | See also               |
| ------------------------------ | --------------------------------------------------------------------- | ------------------------------------------------------------------- | ---------------------- |
| `-XX:NativeMemoryTracking=_X_` | Enable Native Memory Tracking.                                        | When you need to see what memory the JVM is using outside the heap. | Native Memory Tracking |
| `-XX:+PrintNMTStatistics`      | Prints Native Memory Tracking statistics when the program terminates. | When you need to see what memory the JVM is using outside the heap. | Native Memory Tracking |

## Flags for thread handling

| Flag                 | What it does                                      | When to use it                                                               | See also                  |
| -------------------- | ------------------------------------------------- | ---------------------------------------------------------------------------- | ------------------------- |
| `-Xss<N>`            | Sets the size of the native stack for threads.    | Decrease this size to make more memory available for other parts of the JVM. | Tuning Thread Stack Sizes |
| `-XX:-BiasedLocking` | Disables the biased locking algorithm of the JVM. | Can help performance of thread pool–based applications.                      | Biased Locking            |

## Miscellaneous JVM flags

| Flag                         | What it does                                                                  | When to use it                                                                                                                                      | See also           |
| ---------------------------- | ----------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------ |
| `-XX:+CompactStrings`        | Uses 8-bit string representations when possible (Java 11 only).               | Default; always use.                                                                                                                                | Compact Strings    |
| `-XX:-StackTraceInThrowable` | Prevents the stack trace from being gathered whenever an exception is thrown. | On systems with very deep stacks where exceptions are frequently thrown (and where fixing the code to throw fewer exceptions is not a possibility). | Exceptions         |
| `-Xshare`                    | Controls class data sharing.                                                  | Use this flag to make new CDS archives for application code.                                                                                        | Class Data Sharing |

## Flags for Java Flight Recorder

| Flag                            | What it does                                                             | When to use it                                                                                                                                                                                                           | See also             |
| ------------------------------- | ------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | -------------------- |
| `-XX:+FlightRecorder`           | Enables Java Flight Recorder.                                            | Enabling Flight Recorder is always recommended, as it has little overhead unless an actual recording is happening (in which case, the overhead will vary depending on the features used, but still be relatively small). | Java Flight Recorder |
| `-XX:+FlightRecorderOptions`    | Sets options for a default recording via the command line (Java 8 only). | Control how a default recording can be made for the JVM.                                                                                                                                                                 | Java Flight Recorder |
| `-XX:+StartFlightRecorder`      | Starts the JVM with the given Flight Recorder options.                   | Control how a default recording can be made for the JVM.                                                                                                                                                                 | Java Flight Recorder |
| `-XX:+UnlockCommercialFeatures` | Allows the JVM to use commercial (non-open-source) features.             | If you have the appropriate license, setting this flag is required to enable Java Flight Recorder in Java 8.                                                                                                             | Java Flight Rec      |
