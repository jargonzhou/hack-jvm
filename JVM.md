# JVM

<!--
# TODO
* [OpenJDK Code Tools Project](https://openjdk.org/projects/code-tools/)
  * [Java Object Layout (JOL)](https://github.com/openjdk/jol)
    * [How to run jol on Java 9? - StackOverflow](https://stackoverflow.com/questions/46583083/how-to-run-jol-on-java-9)
    * [Memory Layout of Objects in Java - Baeldung](https://www.baeldung.com/java-memory-layout)
    * [Memory Address of Objects in Java - Baeldung](https://www.baeldung.com/java-object-memory-address)
    * [Measuring Object Sizes in the JVM - Baeldung](https://www.baeldung.com/jvm-measuring-object-sizes)
* [Deep Dive Into JVM Tools: Dynamic Attach and Serviceability Agent - Baeldung](https://www.baeldung.com/jvm-dynamic-attach-serviceability-agent)
* [google/allocation-instrumenter](https://github.com/google/allocation-instrumenter): The Allocation Instrumenter is a Java agent written using the `java.lang.instrument` API and **ASM**. Each allocation in your Java program is instrumented; a user-defined callback is invoked on each allocation.
* [Guide to Java Instrumentation - ](https://www.baeldung.com/java-instrumentation) 
-->

# JVM Flags

```shell
java -XX:+PrintFlagsFinal -version | findstr ThreadStackSize
```

# GraalVM
* https://github.com/oracle/graal

> GraalVM is a high-performance JDK distribution that compiles your Java applications ahead of time into standalone binaries. These binaries start instantly, provide peak performance with no warmup, and use fewer resources. You can use GraalVM just like any other Java Development Kit in your IDE.

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

# See Also
