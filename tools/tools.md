# JVM Tools

* [ASM](https://asm.ow2.io): ASM is an all purpose Java bytecode manipulation and analysis framework. It can be used to modify existing classes or to dynamically generate classes, directly in binary form. ASM provides some common bytecode transformations and analysis algorithms from which custom complex transformations and code analysis tools can be built. ASM offers similar functionality as other Java bytecode frameworks, but is focused on performance. Because it was designed and implemented to be as small and as fast as possible, it is well suited for use in dynamic systems (but can of course be used in a static way too, e.g. in compilers).
* [Byte Buddy](https://bytebuddy.net/#/): Byte Buddy is a code generation and manipulation library for creating and modifying Java classes during the runtime of a Java application and without the help of a compiler. Other than the code generation utilities that ship with the Java Class Library, Byte Buddy allows the creation of arbitrary classes and is not limited to implementing interfaces for the creation of runtime proxies. Furthermore, Byte Buddy offers a convenient API for changing classes either manually, using a Java agent or during a build.
* [CGLIB](https://github.com/cglib/cglib): cglib - Byte Code Generation Library is high level API to generate and transform Java byte code. It is used by AOP, testing, data access frameworks to generate dynamic proxy objects and intercept field access. IMPORTANT NOTE: cglib is unmaintained and does not work well (or possibly at all?) in newer JDKs, particularly JDK17+. If you need to support newer JDKs, we will accept well-tested well-thought-out patches... but you'll probably have better luck migrating to something like ByteBuddy. 
* [janino](./tools/example-janino/README.md): Janino is a super-small, super-fast Java™ compiler.
* [JJava](./JJava/JJava.md): JJava is an evolution of the earlier [IJava kernel](https://github.com/SpencerPark/IJava), that is no longer maintained by its authors.
* [JOL: Java Object Layout](https://openjdk.org/projects/code-tools/jol/): JOL (Java Object Layout) is the tiny toolbox to analyze object layout schemes in JVMs. These tools are using Unsafe, JVMTI, and Serviceability Agent (SA) heavily to decoder the actual object layout, footprint, and references. This makes JOL much more accurate than other tools relying on heap dumps, specification assumptions, etc. - example: [Memory Layout of Objects in Java](https://www.baeldung.com/java-memory-layout)

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
