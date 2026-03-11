# Terminology of JVM

# Bytecode Modification

The fundamental mechanism involves intercepting and transforming class files (bytecode) as they are loaded into the JVM, or even redefining already loaded classes. 

# HotSpot
* https://en.wikipedia.org/wiki/HotSpot
* https://github.com/openjdk/jdk/tree/master/src/hotspot

HotSpot, released as **Java HotSpot Performance Engine**, is a Java virtual machine for desktop and server computers, developed by Sun Microsystems which was purchased by and became a division of Oracle Corporation in 2010. Its features include improved performance via methods such as just-in-time compilation and adaptive optimization. It is the de facto reference Java Virtual Machine.

# Instrumentation
* https://docs.oracle.com/javase/8/docs/technotes/guides/instrumentation/index.html

The process of adding bytecodes to methods, typically to collect data for analysis or monitoring.

The `java.lang.instrument` package in Java provides services for instrumenting programs running on the JVM by modifying the bytecodes of methods. This capability is used by tools such as profilers, code coverage analyzers, and monitoring agents to gather data without changing the core application logic.

# Java Agent

A special component, packaged as a JAR file, that uses the interfaces in this package to perform instrumentation.

# Java Attach API
* https://docs.oracle.com/javase/8/docs/technotes/guides/attach/index.html

The Attach API is an extension that provides a mechanism to attach to a Java virtual machine. A tool written in the Java Language, uses this API to attach to a target virtual machine and load its tool agent into that virtual machine. For example, a management console might have a management agent which it uses to obtain management information from instrumented objects in a virtual machine. If the management console is required to manage an application that is running in a virtual machine that does not include the management agent, then this API can be used to attach to the target virtual machine and load the agent.

- `com.sun.tools.attach`
- `com.sun.tools.attach.spi`

# Java Class Library
* https://en.wikipedia.org/wiki/Java_Class_Library

The Java Class Library (JCL) is a set of dynamically loadable libraries that Java Virtual Machine (JVM) languages can call at run time. It is the standard library of Java and other JVM languages, and is thus sometimes called the Java Standard Library.

It uses the namespaces `java.*`, `javax.*`, and `jdk.*`. Some bundled classes may reside in namespaces like `sun.*` or `com.sun.*` (for Sun Microsystems), but they are not considered part of the standard library. The `javax` namespace was primarily associated with Java Platform, Enterprise Edition as extension packages to the Java standard library.

JCL is almost entirely written in Java, except for the parts that need direct access to the hardware and operating system (such as for I/O or bitmap graphics), which are instead written in C++.

Almost all of JCL is stored in a single Java archive file called "rt.jar" which is provided with JRE and JDK distributions. The Java Class Library (rt.jar) is located in the default bootstrap classpath[3] and does not have to appear in the classpath declared for the application. The runtime uses the bootstrap class loader to find the JCL.

The Java Module System (part of the Java 9 release) broke the monolithic "rt.jar" JAR file and modularized the JCL itself in several modules with specified dependencies.

# Java Community Process/JCP
see [JCP.md](./JCP.md)

# Java Development Kit
* https://en.wikipedia.org/wiki/Java_Development_Kit

The Java Development Kit (JDK) is a software development kit for development of a Java platform application.

# Java EE/Enterprise Edition
* https://www.oracle.com/java/technologies/java-ee-glance.html

Java Platform, Enterprise Edition (Java EE) is the standard in community-driven enterprise software. Java EE is developed using the Java Community Process, with contributions from industry experts, commercial and open source organizations, Java User Groups, and countless individuals. Each release integrates new features that align with industry needs, improves application portability, and increases developer productivity.

# Java Platform Module System
* https://en.wikipedia.org/wiki/Java_Platform_Module_System

The Java Platform Module System (JPMS) specifies a distribution format for collections of Java code and associated resources.
It also specifies a repository for storing these collections, or **modules**, and identifies how they can be discovered, loaded and checked for integrity. 
It includes features such as namespaces with the aim of fixing some of the shortcomings in the existing JAR format, especially the JAR Hell, which can lead to issues such as classpath and class loading problems.

# Java SE/Standard Edition
## Java SE 8
* https://docs.oracle.com/javase/8/docs/index.html

JDK 8 is a superset of JRE 8, and contains everything that is in JRE 8, plus tools such as the compilers and debuggers necessary for developing applets and applications. JRE 8 provides the libraries, the Java Virtual Machine (JVM), and other components to run applets and applications written in the Java programming language. Note that the JRE includes components not required by the Java SE specification, including both standard and non-standard Java components.

Java Conceptual Diagram
- Java Language/Java语言
- Tools & Tool APIs/工具和工具API
    - java: Java runtime launcher
    - javac: Java compiler
    - javadoc: Java documentation generator
    - jar: Java archive tool
    - javap: Java class file disassembler
    - jdeps: Java class dependency analyzer
    - Scripting tools
    - Security tools
    - Monitoring: Monitoring and Management tools
    - JConsole: jconsole
    - Java VisualVM: Java VisualVM
    - JMC: Java Mission Control
    - JFR: Java Flight Recorder
    - JPDA: Java Platform Debugger Architecture
    - JVM TI: Java Virtual Machine Tool Interface
    - IDL: Interface Definition Language and RMI-IIOP tools
    - RMI: Remote Method Invocation tools
    - Java DB
    - Deployment: Deployment, Plug-in and Web Start tools
    - Internationalization: Internationalization tools
    - Web Services: Java Web Services Tools
    - Troubleshoot tools
- Deployment/部署
    - Java Web Start
    - Applet / Java Plug-in: Java Plug-In for browsers
- User Interface Toolkits/用户界面工具集
    - JavaFX
    - Swing: Graphical user interface components
    - Java 2D: 2D graphics, text and images
    - AWT: Abstract Window Toolkit
    - Accessibility: Assistive technologies for user interfaces
    - Drag and Drop: Drag and drop data transfer
    - Input Methods: Input Method Framework
    - Image I/O: Image input/output API
    - Print Service: Print service API
    - Sound: MIDI API
- Integration Libraries/集成库
    - IDL: CORBA Interface Definition Language API
    - JDBC: Java Database Connectivity API
    - JNDI: Java Naming and Directory Interface API
    - RMI: Remote Method Invocation API
    - RMI-IIOP: RMI interfaces over IIOP
    - Scripting: Scripting for the Java Platform
- Other Base Libraries/其他基础库
    - Beans: Java Beans component API
    - Security API
    - Serialization: Object Serialization
    - Extension Mechanism: Package extension mechanism
    - JMX: Java Management Extensions
    - XML JAXP: Java API for XML Processing
    - Networking API
    - Override Mechanism: Endorsed Standards Override Mechanism
    - JNI: Java Native Interface
    - Data and Time
    - Input/Output: Input/Output API
    - Internationalization of applications
- lang and util Base Libraries/语言和工具基础库
    - lang and util: `java.lang` and `java.util` packages
    - Math classes
    - Collections framework
    - Ref Objects: Reference Objects API
    - Regular Expressions
    - Logging API
    - Management: JVM Monitoring and Management
    - Instrumentation
    - Concurrency Utilities
    - Reflection API
    - Versioning: Package version identification
    - Preferences API
    - JAR: Java archive file format
    - Zip: Zip and gzip file formats
- Java Virtual Machine/Java虚拟机
    - Java Hotspot Client and Server VM

## Java SE 21
* https://docs.oracle.com/en/java/javase/21/index.html

# JEP: JDK Enhancement Proposals
see [JEP.md](./JEP.md)

# JNI: Java Native Interface
* https://docs.oracle.com/javase/8/docs/technotes/guides/jni/index.html

Java Native Interface (JNI) is a standard programming interface for writing Java native methods and embedding the Java virtual machine into native applications. The primary goal is binary compatibility of native method libraries across all Java virtual machine implementations on a given platform.

See Also
* Liang, Sheng. **Java Native Interface: Programmer's Guide and Reference**. 1999, 1st. edition. Addison-Wesley.

# JPDA: Java Platform Debugger Architecture
* https://docs.oracle.com/en/java/javase/21/docs/specs/jpda/architecture.html

JPDA is a multi-tiered debugging architecture that allows tools developers to easily create debugger applications which run portably across platforms, virtual machine (VM) implementations and JDK versions.

JPDA consists of three layers:
- JVM TI(Java VM Tool Interface): Defines the debugging services a VM provides.
- JDWP(Java Debug Wire Protocol): Defines the communication between debuggee and debugger processes.
- JDI(Java Debug Interface): Defines a high-level Java language interface which tool developers can easily use to write remote debugger applications.

# JVM TI: JVM Tool Interface
* https://en.wikipedia.org/wiki/Java_Virtual_Machine_Tools_Interface
* https://docs.oracle.com/javase/8/docs/technotes/guides/jvmti/

The JVM Tool Interface (JVMTI) is a low-level, native (C/C++) programming interface for building tools to inspect and control Java applications running in the Java Virtual Machine (JVM), supporting tasks like debugging, profiling, monitoring, and code coverage analysis. It allows agents (native libraries) to attach to a JVM at runtime to get deep insights into its state, replacing older interfaces (JVMDI/JVMPI) and providing comprehensive access to VM events and data. 
- JVMDI: Java Virtual Machine Debug Interface
- JVMPI: Java Virtual Machine Profiling Interface

See Also
* [Creating a Debugging and Profiling Agent with JVMTI](https://www.oracle.com/technical-resources/articles/javase/jvmti.html) By C.K Prasad, Rajesh Ramchandani, Gopinath Rao, and Kim Levesque, June 24, 2004.

# OpenJDK
* https://en.wikipedia.org/wiki/OpenJDK

OpenJDK (Open Java Development Kit) is a free and open-source implementation of the Java Platform, Standard Edition (Java SE).

The OpenJDK project produces a number of components: most importantly the virtual machine (HotSpot), the Java Class Library and the Java compiler (javac).

# Technology Compatibility Kit
* https://en.wikipedia.org/wiki/Technology_Compatibility_Kit

In Java, a Technology Compatibility Kit (TCK or JCK) is a suite of tests that at least nominally checks a particular alleged implementation of a Java Specification Request (JSR) for compliance. It is one of the three required pieces for a ratified JSR in the Java Community Process, which are:
- the JSR specification
- the JSR reference implementation
- the Technology Compatibility Kit (TCK)