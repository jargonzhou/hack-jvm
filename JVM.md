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
see [GraalVM.md](./impls/GraalVM.md)

# See Also
* [JVM Anatomy Quarks](https://shipilev.net/jvm/anatomy-quarks/): "JVM Anatomy Quarks" is the on-going mini-post series, where every post is describing some elementary piece of knowledge about JVM. - Aleksey Shipilëv, JVM/Performance Geek.
* [The Java Memory Model](https://www.cs.umd.edu/~pugh/java/memoryModel/): This web page is a starting point for discussions of and information concerning the Java Memory Model (Chapter 17 of the Java Language Specification). The Java Memory Model defines how threads interact through memory. It used to be somewhat unclear and unnecessarily limiting, and so was revised. This is a reference page for that revision. The official site for JSR 133 - The Java(tm) Memory Model and Thread Specification Revision - is [here](http://www.jcp.org/en/jsr/detail?id=133).
* [The JSR-133 Cookbook for Compiler Writers](https://gee.cs.oswego.edu/dl/jmm/cookbook.html) by Doug Lea: This is an unofficial guide to implementing the new Java Memory Model (JMM) specified by JSR-133.
* [Virtual Threads](https://docs.oracle.com/en/java/javase/21/core/virtual-threads.html)
  * [JEP 444 Virtual Threads](https://openjdk.org/jeps/444)