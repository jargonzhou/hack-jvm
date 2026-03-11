# JVM Tools

* [ASM](./ASM/ASM.md): ASM is an all purpose Java bytecode manipulation and analysis framework.
* [BTrace](https://github.com/btraceio/btrace):  BTrace is a safe, dynamic tracing tool for the Java platform. BTrace can be used to dynamically trace a running Java program (similar to DTrace for OpenSolaris applications and OS). BTrace dynamically instruments the classes of the target application to inject tracing code ("bytecode tracing").
* [Byte Buddy](./Byte%20Buddy.md): Byte Buddy is a code generation and manipulation library for creating and modifying Java classes during the runtime of a Java application and without the help of a compiler.
* [CGLIB](https://github.com/cglib/cglib): cglib - Byte Code Generation Library is high level API to generate and transform Java byte code. It is used by AOP, testing, data access frameworks to generate dynamic proxy objects and intercept field access. IMPORTANT NOTE: cglib is unmaintained and does not work well (or possibly at all?) in newer JDKs, particularly JDK17+. If you need to support newer JDKs, we will accept well-tested well-thought-out patches... but you'll probably have better luck migrating to something like ByteBuddy. 
* [IBM HeapAnalyzer](https://www.ibm.com/support/pages/ibm-heapanalyzer): IBM HeapAnalyzer is a graphical tool for discovering possible Java heap leaks.
* [janino](./tools/example-janino/README.md): Janino is a super-small, super-fast Java™ compiler.
* [Java Microbenchmark Harness (JMH)](https://github.com/openjdk/jmh): JMH is a Java harness/马具 for building, running, and analysing nano/micro/milli/macro benchmarks written in Java and other languages targeting the JVM.
* [JavaPoet](./JavaPoet.md): Java library used to generate Java source files.
* [JD-GUI](https://github.com/java-decompiler/jd-gui): a standalone graphical utility that displays Java sources from CLASS files.
* [jitwatch](https://github.com/AdoptOpenJDK/jitwatch): Log analyser / visualiser for Java HotSpot JIT compiler. Inspect inlining decisions, hot methods, bytecode, and assembly. View results in the JavaFX user interface.
* [JJava](./JJava/JJava.md): JJava is an evolution of the earlier [IJava kernel](https://github.com/SpencerPark/IJava), that is no longer maintained by its authors.
* [JOL: Java Object Layout](https://openjdk.org/projects/code-tools/jol/): JOL (Java Object Layout) is the tiny toolbox to analyze object layout schemes in JVMs. These tools are using Unsafe, JVMTI, and Serviceability Agent (SA) heavily to decoder the actual object layout, footprint, and references. This makes JOL much more accurate than other tools relying on heap dumps, specification assumptions, etc. - example: [Memory Layout of Objects in Java](https://www.baeldung.com/java-memory-layout)
* [Memory Analyzer (MAT)](https://eclipse.dev/mat/): The Eclipse Memory Analyzer is a fast and feature-rich Java heap analyzer that helps you find memory leaks and reduce memory consumption.
* [SLF4J](https://www.slf4j.org/index.html): The Simple Logging Facade for Java (SLF4J) serves as a simple facade or abstraction for various logging frameworks (e.g. `java.util.logging`, **logback**, **log4j**) allowing the end user to plug in the desired logging framework at deployment time. - [Mapped Diagnostic Context (MDC) support in Logback](https://logback.qos.ch/manual/mdc.html)
* [SpotBugs](https://github.com/spotbugs/spotbugs): SpotBugs is FindBugs' successor. A tool for static analysis to look for bugs in Java code.

# JDWP: Java Debug Wire Protocol
- [Java Debug Wire Protocol - 17](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-spec.html)
- [Java Debug Wire Protocol Transport Interface (jdwpTransport) - 17](https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-transport.html)

[`java -agentlib:libname=options`](https://docs.oracle.com/en/java/javase/17/docs/specs/man/java.html)
```shell
-agentlib:jdwp=transport=dt_socket,server=y,address=8000
```

# JVMCI: JVM Compiler Interface
- [JEP 243: Java-Level JVM Compiler Interface](https://openjdk.org/jeps/243): Develop a Java based JVM compiler interface (JVMCI) enabling a compiler written in Java to be used by the JVM as a dynamic compiler.
- [graal-jvmci-8](https://github.com/graalvm/graal-jvmci-8): This is a fork of http://hg.openjdk.java.net/jdk8u/jdk8u/hotspot that includes JVMCI.

# JVMTI: JVM Tool Interface
- [Java 8 JVM Tool Interface](https://docs.oracle.com/javase/8/docs/platform/jvmti/jvmti.html): The JVM tool interface (JVM TI) is a native programming interface for use by tools. It provides both a way to inspect the state and to control the execution of applications running in the Java virtual machine (JVM). JVM TI supports the full breadth of tools that need access to JVM state, including but not limited to: profiling, debugging, monitoring, thread analysis, and coverage analysis tools. Note: JVM TI was introduced at JDK 5.0. JVM TI replaced the Java Virtual Machine Profiler Interface (JVMPI) and the Java Virtual Machine Debug Interface (JVMDI) which, as of JDK 6, are no longer provided.
- [Creating a Debugging and Profiling Agent with JVMTI](https://www.oracle.com/technical-resources/articles/javase/jvmti.html), 2004

example: [JVMTI](../codes/example-jvmti/README.md)

Agent Command Line Options: `-agentlib:<agent-lib-name>=<options>`, `-agentpath:<path-to-agent>=<options>`

# Libraries
* [AspectJ](https://github.com/eclipse-aspectj/aspectj): a seamless aspect-oriented extension to the Javatm programming language.
* [AssertJ](https://github.com/assertj/assert): Fluent testing assertions for Java and the JVM.
* [Gson](https://github.com/google/gson): A Java serialization/deserialization library to convert Java Objects into JSON and back.
* [Guice](https://github.com/google/guice): Guice (pronounced 'juice') is a lightweight dependency injection framework for Java 11 and above, brought to you by Google.
* [Jackson](https://github.com/FasterXML/jackson): Jackson has been known as "the Java JSON library" or "the best JSON parser for Java". Or simply as "JSON for Java".
* [Javassist](https://github.com/jboss-javassist/javassist): Javassist (JAVA programming ASSISTant) makes Java bytecode manipulation simple. It is a class library for editing bytecodes in Java.
* [JJWT](https://github.com/jwtk/jjwt): JJWT aims to be the easiest to use and understand library for creating and verifying JSON Web Tokens (JWTs) and JSON Web Keys (JWKs) on the JVM and Android.
* [jopt-simple](https://github.com/jopt-simple/jopt-simple): Java library for parsing command line options.
- [JThumbnail](https://github.com/makbn/JThumbnail): A thumbnail generation Java library for Office,PDF,HTML,Text,MP3,MPEG and Image documents.
- [JUnit](https://junit.org/): The programmer-friendly testing framework for Java and the JVM.
- [thumbnailator](https://github.com/coobird/thumbnailator): Thumbnailator - a thumbnail generation library for Java.
* [UidGenerator](https://github.com/baidu/uid-generator): UidGenerator is a Java implemented, [Snowflake](https://github.com/twitter/snowflake) based unique ID generator.
* [VMLens](https://github.com/vmlens/vmlens): Deterministic Unit Tests for Multithreaded Java
