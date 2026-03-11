# GraalVM
* https://www.graalvm.org
* https://github.com/graalvm
* https://github.com/oracle/graal

> GraalVM is a high-performance JDK distribution that compiles your Java applications ahead of time into standalone binaries. These binaries start instantly, provide peak performance with no warmup, and use fewer resources. You can use GraalVM just like any other Java Development Kit in your IDE.

# Concepts

JIT(Just-In-Time) compiler: GraalJIT

AOT(Ahead-Of-Time) compiler
 
Native Image utility: compile applications AOT(Ahead-Of-Time) into native executables.

The GraalVM Language Implementation Framework: Truffle
- Java, Java on Truffle (Espresso)
- Scala, Kotlin, ...
- JavaScript, Node.js, Ruby, Python, ...
- LLVM, WASM

optimizations
- partial escape analysis
- partial loop unrolling
- aggressive priority
- polymorphic inlining
- data flow analysis

benchmarks
- The Renaissance benchmark suite
- The Scalabench test suite

application frameworks
- Helidon
- Micronaut
- Quarkus
- Spring Boot
- Tomcat

GraalVM VS Code extension

[Native Image](https://github.com/oracle/graal/blob/master/docs/reference-manual/native-image/README.md)
- `org.graalvm.buildtools:native-maven-plugin`
- `native-image`: ex `/c/Users/zhouj/.sdkman/candidates/java/17.0.9-graalce/native-image.cmd`

# Truffle Language Implementation Framework
* https://www.graalvm.org/latest/graalvm-as-a-platform/language-implementation-framework/ - use Java 21
* [Graal Truffle tutorial](https://www.endoflineblog.com/graal-truffle-tutorial-part-0-what-is-truffle)

simplelanguage: A simple example language built using the Truffle API.
- Entry point: https://github.com/graalvm/simplelanguage/blob/master/language/src/main/java/com/oracle/truffle/sl/SLLanguage.java
- ANTLR syntax: https://github.com/graalvm/simplelanguage/blob/master/language/src/main/java/com/oracle/truffle/sl/parser/SimpleLanguage.g4


```shell
# Download from https://www.graalvm.org/downloads/
$ export JAVA_HOME=/home/zhoujiagen/graalvm-jdk-17
$ export PATH=/home/zhoujiagen/graalvm-jdk-17/bin:$PATH

$ export JAVA_HOME=/home/zhoujiagen/graalvm-jdk-21
$ export PATH=/home/zhoujiagen/graalvm-jdk-21/bin:$PATH

$ java --version
java 17.0.10 2024-01-16 LTS
Java(TM) SE Runtime Environment Oracle GraalVM 17.0.10+11.1 (build 17.0.10+11-LTS-jvmci-23.0-b27)
Java HotSpot(TM) 64-Bit Server VM Oracle GraalVM 17.0.10+11.1 (build 17.0.10+11-LTS-jvmci-23.0-b27, mixed mode, sharing)
$ native-image --version
native-image 17.0.10 2024-01-16
GraalVM Runtime Environment Oracle GraalVM 17.0.10+11.1 (build 17.0.10+11-LTS-jvmci-23.0-b27)
Substrate VM Oracle GraalVM 17.0.10+11.1 (build 17.0.10+11-LTS, serial gc, compressed references)

$ sudo apt install gcc
$ sudo apt install zlib1g-dev

$ native-image HelloWorld
========================================================================================================================
GraalVM Native Image: Generating 'helloworld' (executable)...
========================================================================================================================
[1/8] Initializing...                                                                                    (3.0s @ 0.17GB)
 Java version: 17.0.10+11-LTS, vendor version: Oracle GraalVM 17.0.10+11.1
 Graal compiler: optimization level: 2, target machine: x86-64-v3, PGO: ML-inferred
 C compiler: gcc (linux, x86_64, 11.4.0)
 Garbage collector: Serial GC (max heap size: 80% of RAM)
[2/8] Performing analysis...  [*****]                                                                    (4.7s @ 0.24GB)
   1,851 (59.10%) of  3,132 types reachable
   1,735 (46.19%) of  3,756 fields reachable
   7,725 (35.66%) of 21,664 methods reachable
     637 types,     0 fields, and   281 methods registered for reflection
      49 types,    32 fields, and    48 methods registered for JNI access
       4 native libraries: dl, pthread, rt, z
[3/8] Building universe...                                                                               (0.7s @ 0.34GB)
[4/8] Parsing methods...      [*]                                                                        (1.4s @ 0.34GB)
[5/8] Inlining methods...     [***]                                                                      (0.4s @ 0.35GB)
[6/8] Compiling methods...    [***]                                                                     (11.0s @ 0.83GB)
[7/8] Layouting methods...    [*]                                                                        (0.4s @ 0.31GB)
[8/8] Creating image...       [*]                                                                        (0.9s @ 0.39GB)
   2.75MB (42.10%) for code area:     3,489 compilation units
   3.46MB (53.01%) for image heap:   48,910 objects and 1 resources
 327.41kB ( 4.89%) for other data
   6.54MB in total
------------------------------------------------------------------------------------------------------------------------
Top 10 origins of code area:                                Top 10 object types in image heap:
   1.44MB java.base                                          548.50kB byte[] for code metadata
   1.13MB svm.jar (Native Image)                             415.72kB byte[] for java.lang.String
  68.88kB com.oracle.svm.svm_enterprise                      325.66kB java.lang.String
  33.89kB org.graalvm.nativeimage.base                       304.18kB java.lang.Class
  30.23kB org.graalvm.sdk                                    253.51kB byte[] for general heap data
  18.95kB jdk.internal.vm.ci                                 148.59kB java.util.HashMap$Node
  14.10kB jdk.internal.vm.compiler                           111.71kB char[]
   1.17kB jdk.proxy3                                          78.91kB java.lang.Object[]
   1.15kB jdk.proxy1                                          72.30kB com.oracle.svm.core.hub.DynamicHubCompanion
  360.00B jdk.proxy2                                          70.40kB byte[] for reflection metadata
   54.00B for 1 more packages                                442.25kB for 505 more object types
------------------------------------------------------------------------------------------------------------------------
Recommendations:
 G1GC: Use the G1 GC ('--gc=G1') for improved latency and throughput.
 PGO:  Use Profile-Guided Optimizations ('--pgo') for improved throughput.
 HEAP: Set max heap for improved and more predictable memory usage.
 CPU:  Enable more CPU features with '-march=native' for improved performance.
 QBM:  Use the quick build mode ('-Ob') to speed up builds during development.
------------------------------------------------------------------------------------------------------------------------
                        0.9s (3.9% of total time) in 62 GCs | Peak RSS: 1.45GB | CPU load: 8.58
------------------------------------------------------------------------------------------------------------------------
Produced artifacts:
 /mnt/d/workspace/graalvm/helloworld (executable)
========================================================================================================================
Finished generating 'helloworld' in 22.9s.

$ ./helloworld
Hello, World!
```


# See Also
* [Understanding How Graal Works - a Java JIT Compiler Written in Java](https://chrisseaton.com/truffleruby/jokerconf17/), 2017.