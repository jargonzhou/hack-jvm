# JEP: JDK Enhancement Proposals
- https://openjdk.org/jeps/0

Updated	2026/01/07 05:36


## Process JEPs

|     |     |     |                                            |
| --- | --- | --- | ------------------------------------------ |
| P   | Act | 1   | JDK Enhancement-Proposal & Roadmap Process |
| P   | Act | 2   | JEP Template                               |
| P   | Act | 3   | JDK Release Process                        |
| P   | Act | 11  | Incubator Modules                          |
| P   | Act | 12  | Preview Features                           |

## Informational JEPs

|     |     |     |                                              |
| --- | --- | --- | -------------------------------------------- |
| I   | Act | 14  | The Tip & Tail Model of Library Development  |
| I   | Act | 293 | Guidelines for JDK Command-Line Tool Options |

## In-flight JEPs

|     |     |     |                        |     |                                                           |
| --- | --- | --- | ---------------------- | --- | --------------------------------------------------------- |
| F   | Can |     | core-svc/tools         | 528 | Post-Mortem Crash Analysis with jcmd                      |
| F   | Pro | 27  | security/javax.net.ssl | 527 | Post-Quantum Hybrid Key Exchange for TLS 1.3              |
| F   | Com | 26  | core/—                 | 525 | Structured Concurrency (Sixth Preview)                    |
| F   | Com | 26  | security/security      | 524 | PEM Encodings of Cryptographic Objects (Second Preview)   |
| F   | Can |     | hotspot/gc             | 523 | Make G1 the Default Garbage Collector in All Environments |
| F   | Com | 26  | hotspot/gc             | 522 | G1 GC: Improve Throughput by Reducing Synchronization     |
| F   | Com | 26  | core/net               | 517 | HTTP/3 for the HTTP Client API                            |
| F   | Com | 26  | client/awt             | 504 | Remove the Applet API                                     |
| F   | Com | 26  | core/—                 | 500 | Prepare to Make Final Mean Final                          |
| F   | Can |     | spec/lang              | 468 | Derived Record Creation (Preview)                         |
| F   | Can |     | hotspot/compiler       | 399 | Intermediate-Representation Graph Serialization           |
| F   | Can |     | tools/javac            | 303 | Intrinsics for the LDC and INVOKEDYNAMIC Instructions     |
| F   | Can |     | tools/javac            | 302 | Lambda Leftovers                                          |
| F   | Can |     | tools/javac            | 300 | Augment Use-Site Variance with Declaration-Site Defaults  |
| F   | Can |     | hotspot/compiler       | 234 | Additional Run-Time Compiler JVM Trace Events             |
| F   | Can |     | spec/lang              | 218 | Generics over Primitive Types                             |
| F   | Can |     | core/—                 | 198 | Light-Weight JSON API                                     |
| F   | Can |     | hotspot/gc             | 134 | Intuitive Semantics for Nested Reference Objects          |
| F   | Can |     | core/—                 | 111 | Additional Unicode Constructs for Regular Expressions     |

## Submitted JEPs

|     |     |     |                     |         |                                      |
| --- | --- | --- | ------------------- | ------- | ------------------------------------ |
| F   | Sub |     | hotspot/gc          | 8359211 | Automatic Heap Sizing for G1         |
| F   | Sub |     | hotspot/gc          | 8329758 | Faster Startup and Warmup with ZGC   |
| F   | Sub |     | tools/javadoc(tool) | 8363700 | Rich Notes in Java API Documentation |
| F   | Sub |     | spec/—              | 401     | Value Classes and Objects (Preview)  |

## Draft JEPs

|     |     |     |                           |         |                                                                           |
| --- | --- | --- | ------------------------- | ------- | ------------------------------------------------------------------------- |
| F   | Dra |     | hotspot/runtime           | 8349069 | 4-byte Object Headers (Experimental)                                      |
| F   | Dra |     | hotspot/compiler          | 8335368 | Ahead-of-Time Code Compilation                                            |
| F   | Dra |     | hotspot/gc                | 8350152 | Automatic Heap Sizing for the Serial Garbage Collector                    |
| F   | Dra |     | hotspot/runtime           | 8201462 | Better hash codes                                                         |
| I   | Dra |     |                           | 8336232 | CDS Implementation Notes                                                  |
| F   | Dra |     | security/javax.net.ssl    | 8171275 | Certificate Transparency                                                  |
| F   | Dra |     | core/lang.invoke          | 8357674 | Classifier API to Map Finite Sets to Indexes                              |
| F   | Dra |     | hotspot/runtime           | 8361187 | Compact Object Headers by Default                                         |
| F   | Dra |     | spec/lang                 | 8209434 | Concise Method Bodies                                                     |
| F   | Dra |     | core/lang                 | 8371379 | Deprecate the UTF-16-only String Representation                           |
| F   | Dra |     | —/—                       | 137     | Diagnostic-Command Framework                                              |
| F   | Dra |     | hotspot/gc                | 163     | Enable NUMA Mode by Default When Appropriate                              |
| F   | Dra |     | —/—                       | 402     | Enhanced Primitive Boxing (Preview)                                       |
| F   | Dra |     | spec/lang                 | 8323658 | Exception handling in switch (Preview)                                    |
| S   | Dra |     |                           | 8283291 | Extended Opcodes                                                          |
| F   | Dra |     | —/—                       | 8261007 | Frozen Arrays (Preview)                                                   |
| I   | Dra |     |                           | 8214497 | Guidelines for documenting system properties                              |
| F   | Dra |     | hotspot/compiler          | 8328186 | Hot Code Heap                                                             |
| F   | Dra |     | core/net                  | 8179037 | Improve IPv6 support                                                      |
| F   | Dra |     | hotspot/compiler          | 8279184 | Instruction Issue Cache Hardware Accommodation                            |
| I   | Dra |     |                           | 8305968 | Integrity by Default                                                      |
| F   | Dra |     | hotspot/compiler          | 8261099 | Internal Frozen Arrays                                                    |
| F   | Dra |     | core/lang.invoke          | 8158765 | Isolated Methods                                                          |
| F   | Dra |     | client/javax.swing        | 8368874 | JEP: Add a JDatePicker UI Component to the Swing UI Toolkit (Preview)     |
| F   | Dra | tbd | core-svc/javax.management | 8044507 | JMX Specific Annotations for Registration of Managed Resources            |
| F   | Dra |     | hotspot/compiler          | 8223220 | JVMCI based JIT Compiler pre-compiled as shared library                   |
| I   | Dra |     |                           | 188     | Java Memory Model Update                                                  |
| F   | Dra | tbd | hotspot/runtime           | 8208520 | Java Thread Sanitizer                                                     |
| I   | Dra |     |                           | 8223002 | Keyword Management for the Java Language                                  |
| F   | Dra |     | hotspot/compiler          | 169     | Larval State for Value Objects                                            |
| F   | Dra |     | tools/—                   | 8209964 | Lazy Static Final Fields                                                  |
| F   | Dra |     | core/lang                 | 8249196 | Low-level Object layout introspection methods                             |
| F   | Dra |     | hotspot/runtime           | 8248259 | Low-level classfile reflection                                            |
| F   | Dra |     | hotspot/compiler          | 8221828 | New Invoke Bindings                                                       |
| F   | Dra |     | —/—                       | 8316779 | Null-Restricted Value Class Types (Preview)                               |
| F   | Dra |     | tools/javac               | 8303099 | Null-Restricted and Nullable Types (Preview)                              |
| F   | Dra |     | hotspot/compiler          | 8132243 | Optimize Final Field Loads In Generated Code                              |
| F   | Dra |     | hotspot/jfr               | 8284453 | Optionally Record Thread Context in JFR                                   |
| F   | Dra |     | tools/javac               | 190     | Pluggable Static Analyzers                                                |
| I   | Dra |     |                           | 182     | Policy for Retiring javac -source and -target Options                     |
| F   | Dra |     | core/util.regex           | 8260688 | Predictable regex performance                                             |
| F   | Dra |     | —/—                       | 8354416 | Prepare for Native Memory Tracking in the JDK                             |
| I   | Dra |     |                           | 8300604 | Preview Features: A Look Back, and A Look Ahead                           |
| F   | Dra |     | hotspot/runtime           | 8180647 | Race exclusion for confined data                                          |
| F   | Dra | tbd | core/lang                 | 8263697 | Safer Process Launch by ProcessBuilder and Runtime.exec                   |
| F   | Dra |     | security/javax.crypto     | 8325511 | Security Providers Filter                                                 |
| F   | Dra |     | hotspot/runtime           | 8248257 | Simplified bootstrap method invocation                                    |
| F   | Dra |     | hotspot/runtime           | 8350458 | Strict Field Initialization in the JVM (Preview)                          |
| F   | Dra |     | security/javax.net.ssl    | 8281710 | TLS Certificate Compression                                               |
| F   | Dra |     | hotspot/runtime           | 8204937 | Type operator expressions in the JVM                                      |
| S   | Dra | tbd |                           | 8288293 | Windows/gcc Port                                                          |
| F   | Dra |     | —/—                       | 8297236 | enhanced checkcast for Valhalla type unification                          |
| F   | Dra |     | spec/lang                 | 8191530 | fluent postfix notation for statically scoped interface methods           |
| I   | Dra |     |                           | 8068562 | javadoc tags to distinguish API, implementation, specification, and notes |
| F   | Dra |     | —/—                       | 8297156 | low-level control of field initialization                                 |
| F   | Dra |     | hotspot/compiler          | 8179657 | provide stable USDT probe points on JVM compiled methods                  |
| F   | Dra |     | hotspot/runtime           | 8258000 | refactor per-instance metadata to be separate from ClassInfo metadata     |
| F   | Dra |     | spec/lang                 | 8186473 | special notation for the receiver helper pattern                          |
| F   | Dra |     | core/lang.invoke          | 8182862 | unboxed argument lists for method handles                                 |

## Delivered Feature and Infrastructure JEPs

|     |     |      |                              |     |                                                                        |
| --- | --- | ---- | ---------------------------- | --- | ---------------------------------------------------------------------- |
| F   | Clo | 26   | spec/lang                    | 530 | Primitive Types in Patterns, instanceof, and switch (Fourth Preview)   |
| F   | Clo | 26   | core/—                       | 529 | Vector API (Eleventh Incubator)                                        |
| F   | Clo | 26   | core/lang                    | 526 | Lazy Constants (Second Preview)                                        |
| F   | Clo | 25   | hotspot/gc                   | 521 | Generational Shenandoah                                                |
| F   | Clo | 25   | hotspot/jfr                  | 520 | JFR Method Timing & Tracing                                            |
| F   | Clo | 25   | hotspot/runtime              | 519 | Compact Object Headers                                                 |
| F   | Clo | 25   | hotspot/jfr                  | 518 | JFR Cooperative Sampling                                               |
| F   | Clo | 26   | hotspot/gc                   | 516 | Ahead-of-Time Object Caching with Any GC                               |
| F   | Clo | 25   | hotspot/compiler             | 515 | Ahead-of-Time Method Profiling                                         |
| F   | Clo | 25   | hotspot/runtime              | 514 | Ahead-of-Time Command-Line Ergonomics                                  |
| F   | Clo | 25   | spec/lang                    | 513 | Flexible Constructor Bodies                                            |
| F   | Clo | 25   | spec/lang                    | 512 | Compact Source Files and Instance Main Methods                         |
| F   | Clo | 25   | spec/lang                    | 511 | Module Import Declarations                                             |
| F   | Clo | 25   | security/javax.crypto        | 510 | Key Derivation Function API                                            |
| F   | Clo | 25   | hotspot/jfr                  | 509 | JFR CPU-Time Profiling (Experimental)                                  |
| F   | Clo | 25   | core/—                       | 508 | Vector API (Tenth Incubator)                                           |
| F   | Clo | 25   | spec/lang                    | 507 | Primitive Types in Patterns, instanceof, and switch (Third Preview)    |
| F   | Clo | 25   | core/—                       | 506 | Scoped Values                                                          |
| F   | Clo | 25   | core/—                       | 505 | Structured Concurrency (Fifth Preview)                                 |
| F   | Clo | 25   | hotspot/other                | 503 | Remove the 32-bit x86 Port                                             |
| F   | Clo | 25   | core/lang                    | 502 | Stable Values (Preview)                                                |
| F   | Clo | 24   | hotspot/other                | 501 | Deprecate the 32-bit x86 Port for Removal                              |
| F   | Clo | 24   | core/—                       | 499 | Structured Concurrency (Fourth Preview)                                |
| F   | Clo | 24   | core/—                       | 498 | Warn upon Use of Memory-Access Methods in sun.misc.Unsafe              |
| F   | Clo | 24   | security/security            | 497 | Quantum-Resistant Module-Lattice-Based Digital Signature Algorithm     |
| F   | Clo | 24   | security/javax.crypto        | 496 | Quantum-Resistant Module-Lattice-Based Key Encapsulation Mechanism     |
| F   | Clo | 24   | spec/lang                    | 495 | Simple Source Files and Instance Main Methods (Fourth Preview)         |
| F   | Clo | 24   | spec/lang                    | 494 | Module Import Declarations (Second Preview)                            |
| F   | Clo | 24   | tools/jlink                  | 493 | Linking Run-Time Images without JMODs                                  |
| F   | Clo | 24   | spec/lang                    | 492 | Flexible Constructor Bodies (Third Preview)                            |
| F   | Clo | 24   | hotspot/runtime              | 491 | Synchronize Virtual Threads without Pinning                            |
| F   | Clo | 24   | hotspot/gc                   | 490 | ZGC: Remove the Non-Generational Mode                                  |
| F   | Clo | 24   | core/—                       | 489 | Vector API (Ninth Incubator)                                           |
| F   | Clo | 24   | spec/lang                    | 488 | Primitive Types in Patterns, instanceof, and switch (Second Preview)   |
| F   | Clo | 24   | core/—                       | 487 | Scoped Values (Fourth Preview)                                         |
| F   | Clo | 24   | security/security            | 486 | Permanently Disable the Security Manager                               |
| F   | Clo | 24   | core/util.stream             | 485 | Stream Gatherers                                                       |
| F   | Clo | 24   | core/lang.classfile          | 484 | Class-File API                                                         |
| F   | Clo | 24   | hotspot/runtime              | 483 | Ahead-of-Time Class Loading & Linking                                  |
| F   | Clo | 23   | spec/lang                    | 482 | Flexible Constructor Bodies (Second Preview)                           |
| F   | Clo | 23   | core/—                       | 481 | Scoped Values (Third Preview)                                          |
| F   | Clo | 23   | core/—                       | 480 | Structured Concurrency (Third Preview)                                 |
| F   | Clo | 24   | hotspot/other                | 479 | Remove the Windows 32-bit x86 Port                                     |
| F   | Clo | 24   | security/javax.crypto        | 478 | Key Derivation Function API (Preview)                                  |
| F   | Clo | 23   | spec/lang                    | 477 | Implicitly Declared Classes and Instance Main Methods (Third Preview)  |
| F   | Clo | 23   | spec/lang                    | 476 | Module Import Declarations (Preview)                                   |
| F   | Clo | 24   | hotspot/compiler             | 475 | Late Barrier Expansion for G1                                          |
| F   | Clo | 23   | hotspot/gc                   | 474 | ZGC: Generational Mode by Default                                      |
| F   | Clo | 23   | core/util.stream             | 473 | Stream Gatherers (Second Preview)                                      |
| F   | Clo | 24   | core/—                       | 472 | Prepare to Restrict the Use of JNI                                     |
| F   | Clo | 23   | core/—                       | 471 | Deprecate the Memory-Access Methods in sun.misc.Unsafe for Removal     |
| F   | Clo | 25   | security/security            | 470 | PEM Encodings of Cryptographic Objects (Preview)                       |
| F   | Clo | 23   | core/—                       | 469 | Vector API (Eighth Incubator)                                          |
| F   | Clo | 23   | tools/javadoc(tool)          | 467 | Markdown Documentation Comments                                        |
| F   | Clo | 23   | core/lang.classfile          | 466 | Class-File API (Second Preview)                                        |
| F   | Clo | 22   | core/—                       | 464 | Scoped Values (Second Preview)                                         |
| F   | Clo | 22   | spec/lang                    | 463 | Implicitly Declared Classes and Instance Main Methods (Second Preview) |
| F   | Clo | 22   | core/—                       | 462 | Structured Concurrency (Second Preview)                                |
| F   | Clo | 22   | core/util.stream             | 461 | Stream Gatherers (Preview)                                             |
| F   | Clo | 22   | core/—                       | 460 | Vector API (Seventh Incubator)                                         |
| F   | Clo | 22   | spec/lang                    | 459 | String Templates (Second Preview)                                      |
| F   | Clo | 22   | tools/launcher               | 458 | Launch Multi-File Source-Code Programs                                 |
| F   | Clo | 22   | core/lang.classfile          | 457 | Class-File API (Preview)                                               |
| F   | Clo | 22   | spec/lang                    | 456 | Unnamed Variables & Patterns                                           |
| F   | Clo | 23   | spec/lang                    | 455 | Primitive Types in Patterns, instanceof, and switch (Preview)          |
| F   | Clo | 22   | core/lang.foreign            | 454 | Foreign Function & Memory API                                          |
| F   | Clo | 21   | core/—                       | 453 | Structured Concurrency (Preview)                                       |
| F   | Clo | 21   | security/javax.crypto        | 452 | Key Encapsulation Mechanism API                                        |
| F   | Clo | 21   | hotspot/svc                  | 451 | Prepare to Disallow the Dynamic Loading of Agents/关闭动态加载Agent    |
| F   | Clo | 24   | hotspot/runtime              | 450 | Compact Object Headers (Experimental)                                  |
| F   | Clo | 21   | hotspot/other                | 449 | Deprecate the Windows 32-bit x86 Port for Removal                      |
| F   | Clo | 21   | core/—                       | 448 | Vector API (Sixth Incubator)                                           |
| F   | Clo | 22   | spec/lang                    | 447 | Statements before super(...) (Preview)                                 |
| F   | Clo | 21   | core/—                       | 446 | Scoped Values (Preview)                                                |
| F   | Clo | 21   | spec/lang                    | 445 | Unnamed Classes and Instance Main Methods (Preview)                    |
| F   | Clo | 21   | core/—                       | 444 | Virtual Threads/虚拟线程                                               |
| F   | Clo | 21   | spec/lang                    | 443 | Unnamed Patterns and Variables (Preview)                               |
| F   | Clo | 21   | core/—                       | 442 | Foreign Function & Memory API (Third Preview)                          |
| F   | Clo | 21   | spec/lang                    | 441 | Pattern Matching for switch                                            |
| F   | Clo | 21   | spec/lang                    | 440 | Record Patterns                                                        |
| F   | Clo | 21   | hotspot/gc                   | 439 | Generational ZGC                                                       |
| F   | Clo | 20   | core/—                       | 438 | Vector API (Fifth Incubator)                                           |
| F   | Clo | 20   | core/—                       | 437 | Structured Concurrency (Second Incubator)                              |
| F   | Clo | 20   | core/—                       | 436 | Virtual Threads (Second Preview)                                       |
| F   | Clo | 20   | core/—                       | 434 | Foreign Function & Memory API (Second Preview)                         |
| F   | Clo | 20   | spec/lang                    | 433 | Pattern Matching for switch (Fourth Preview)                           |
| F   | Clo | 20   | spec/lang                    | 432 | Record Patterns (Second Preview)                                       |
| F   | Clo | 21   | core/util:collections        | 431 | Sequenced Collections                                                  |
| F   | Clo | 21   | spec/lang                    | 430 | String Templates (Preview)                                             |
| F   | Clo | 20   | core/—                       | 429 | Scoped Values (Incubator)                                              |
| F   | Clo | 19   | core/—                       | 428 | Structured Concurrency (Incubator)                                     |
| F   | Clo | 19   | spec/lang                    | 427 | Pattern Matching for switch (Third Preview)                            |
| F   | Clo | 19   | core/—                       | 426 | Vector API (Fourth Incubator)                                          |
| F   | Clo | 19   | core/—                       | 425 | Virtual Threads (Preview)                                              |
| F   | Clo | 19   | core/—                       | 424 | Foreign Function & Memory API (Preview)                                |
| F   | Clo | 22   | hotspot/gc                   | 423 | Region Pinning for G1                                                  |
| F   | Clo | 19   | hotspot/other                | 422 | Linux/RISC-V Port                                                      |
| F   | Clo | 18   | core/lang                    | 421 | Deprecate Finalization for Removal                                     |
| F   | Clo | 18   | spec/lang                    | 420 | Pattern Matching for switch (Second Preview)                           |
| F   | Clo | 18   | core/—                       | 419 | Foreign Function & Memory API (Second Incubator)                       |
| F   | Clo | 18   | core/net                     | 418 | Internet-Address Resolution SPI                                        |
| F   | Clo | 18   | core/—                       | 417 | Vector API (Third Incubator)                                           |
| F   | Clo | 18   | core/lang:reflect            | 416 | Reimplement Core Reflection with Method Handles                        |
| F   | Clo | 17   | core/io:serialization        | 415 | Context-Specific Deserialization Filters                               |
| F   | Clo | 17   | core/—                       | 414 | Vector API (Second Incubator)                                          |
| F   | Clo | 18   | tools/javadoc(tool)          | 413 | Code Snippets in Java API Documentation                                |
| F   | Clo | 17   | core/—                       | 412 | Foreign Function & Memory API (Incubator)                              |
| F   | Clo | 17   | security/security            | 411 | Deprecate the Security Manager for Removal                             |
| F   | Clo | 17   | hotspot/compiler             | 410 | Remove the Experimental AOT and JIT Compiler                           |
| F   | Clo | 17   | spec/lang                    | 409 | Sealed Classes                                                         |
| F   | Clo | 18   | core/net                     | 408 | Simple Web Server                                                      |
| F   | Clo | 17   | core/rmi                     | 407 | Remove RMI Activation                                                  |
| F   | Clo | 17   | spec/lang                    | 406 | Pattern Matching for switch (Preview)                                  |
| F   | Clo | 19   | spec/lang                    | 405 | Record Patterns (Preview)                                              |
| F   | Clo | 24   | hotspot/gc                   | 404 | Generational Shenandoah (Experimental)                                 |
| F   | Clo | 17   | —/—                          | 403 | Strongly Encapsulate JDK Internals                                     |
| F   | Clo | 18   | core/nio.charsets            | 400 | UTF-8 by Default                                                       |
| F   | Clo | 17   | client/awt                   | 398 | Deprecate the Applet API for Removal                                   |
| F   | Clo | 16   | spec/lang                    | 397 | Sealed Classes (Second Preview)                                        |
| F   | Clo | 16   | —/—                          | 396 | Strongly Encapsulate JDK Internals by Default                          |
| F   | Clo | 16   | spec/lang                    | 395 | Records                                                                |
| F   | Clo | 16   | spec/lang                    | 394 | Pattern Matching for instanceof                                        |
| F   | Clo | 16   | core/—                       | 393 | Foreign-Memory Access API (Third Incubator)                            |
| F   | Clo | 16   | tools/jpackage               | 392 | Packaging Tool                                                         |
| F   | Clo | 17   | hotspot/runtime              | 391 | macOS/AArch64 Port                                                     |
| F   | Clo | 16   | —/—                          | 390 | Warnings for Value-Based Classes                                       |
| F   | Clo | 16   | core/—                       | 389 | Foreign Linker API (Incubator)                                         |
| F   | Clo | 16   | hotspot/runtime              | 388 | Windows/AArch64 Port                                                   |
| F   | Clo | 16   | hotspot/runtime              | 387 | Elastic Metaspace                                                      |
| F   | Clo | 16   | hotspot/runtime              | 386 | Alpine Linux Port                                                      |
| F   | Clo | 15   | core/rmi                     | 385 | Deprecate RMI Activation for Removal                                   |
| F   | Clo | 15   | spec/lang                    | 384 | Records (Second Preview)                                               |
| F   | Clo | 15   | core/—                       | 383 | Foreign-Memory Access API (Second Incubator)                           |
| F   | Clo | 17   | client/2d                    | 382 | New macOS Rendering Pipeline                                           |
| F   | Clo | 15   | hotspot/other                | 381 | Remove the Solaris and SPARC Ports                                     |
| F   | Clo | 16   | core/nio                     | 380 | Unix-Domain Socket Channels                                            |
| F   | Clo | 15   | hotspot/gc                   | 379 | Shenandoah: A Low-Pause-Time Garbage Collector (Production)            |
| F   | Clo | 15   | spec/lang                    | 378 | Text Blocks                                                            |
| F   | Clo | 15   | hotspot/gc                   | 377 | ZGC: A Scalable Low-Latency Garbage Collector (Production)             |
| F   | Clo | 16   | hotspot/gc                   | 376 | ZGC: Concurrent Thread-Stack Processing                                |
| F   | Clo | 15   | spec/lang                    | 375 | Pattern Matching for instanceof (Second Preview)                       |
| F   | Clo | 15   | hotspot/runtime              | 374 | Deprecate and Disable Biased Locking                                   |
| F   | Clo | 15   | core/net                     | 373 | Reimplement the Legacy DatagramSocket API                              |
| F   | Clo | 15   | core/jdk.nashorn             | 372 | Remove the Nashorn JavaScript Engine                                   |
| F   | Clo | 15   | core/lang.invoke             | 371 | Hidden Classes                                                         |
| F   | Clo | 14   | core/—                       | 370 | Foreign-Memory Access API (Incubator)                                  |
| S   | Clo | 16   |                              | 369 | Migrate to GitHub                                                      |
| F   | Clo | 14   | spec/lang                    | 368 | Text Blocks (Second Preview)                                           |
| F   | Clo | 14   | tools/jar                    | 367 | Remove the Pack200 Tools and API                                       |
| F   | Clo | 14   | hotspot/gc                   | 366 | Deprecate the ParallelScavenge + SerialOld GC Combination              |
| F   | Clo | 14   | hotspot/gc                   | 365 | ZGC on Windows (Experimental)                                          |
| F   | Clo | 14   | hotspot/gc                   | 364 | ZGC on macOS (Experimental)                                            |
| F   | Clo | 14   | hotspot/gc                   | 363 | Remove the Concurrent Mark Sweep (CMS) Garbage Collector               |
| F   | Clo | 14   | —/—                          | 362 | Deprecate the Solaris and SPARC Ports                                  |
| F   | Clo | 14   | spec/lang                    | 361 | Switch Expressions                                                     |
| F   | Clo | 15   | spec/lang                    | 360 | Sealed Classes (Preview)                                               |
| F   | Clo | 14   | spec/lang                    | 359 | Records (Preview)                                                      |
| F   | Clo | 14   | hotspot/runtime              | 358 | Helpful NullPointerExceptions                                          |
| S   | Clo | 16   |                              | 357 | Migrate from Mercurial to Git                                          |
| F   | Clo | 17   | core/util                    | 356 | Enhanced Pseudo-Random Number Generators                               |
| F   | Clo | 13   | spec/lang                    | 355 | Text Blocks (Preview)                                                  |
| F   | Clo | 13   | spec/lang                    | 354 | Switch Expressions (Second Preview)                                    |
| F   | Clo | 13   | core/net                     | 353 | Reimplement the Legacy Socket API                                      |
| F   | Clo | 14   | core/nio                     | 352 | Non-Volatile Mapped Byte Buffers                                       |
| F   | Clo | 13   | hotspot/gc                   | 351 | ZGC: Uncommit Unused Memory (Experimental)                             |
| F   | Clo | 13   | hotspot/runtime              | 350 | Dynamic CDS Archives                                                   |
| F   | Clo | 14   | hotspot/jfr                  | 349 | JFR Event Streaming                                                    |
| S   | Clo | 16   |                              | 347 | Enable C++14 Language Features                                         |
| F   | Clo | 12   | hotspot/gc                   | 346 | Promptly Return Unused Committed Memory from G1                        |
| F   | Clo | 14   | hotspot/gc                   | 345 | NUMA-Aware Memory Allocation for G1                                    |
| F   | Clo | 12   | hotspot/gc                   | 344 | Abortable Mixed Collections for G1                                     |
| F   | Clo | 14   | tools/jpackage               | 343 | Packaging Tool (Incubator)                                             |
| F   | Clo | 12   | hotspot/runtime              | 341 | Default CDS Archives                                                   |
| F   | Clo | 12   | hotspot/runtime              | 340 | One AArch64 Port, Not Two                                              |
| F   | Clo | 15   | security/javax.crypto        | 339 | Edwards-Curve Digital Signature Algorithm (EdDSA)                      |
| F   | Clo | 16   | hotspot/compiler             | 338 | Vector API (Incubator)                                                 |
| F   | Clo | 11   | tools/—                      | 336 | Deprecate the Pack200 Tools and API                                    |
| F   | Clo | 11   | core/jdk.nashorn             | 335 | Deprecate the Nashorn JavaScript Engine for Removal                    |
| F   | Clo | 12   | core/lang.invoke             | 334 | JVM Constants API                                                      |
| F   | Clo | 11   | hotspot/gc                   | 333 | ZGC: A Scalable Low-Latency Garbage Collector (Experimental)           |
| F   | Clo | 11   | security/javax.net.ssl       | 332 | Transport Layer Security (TLS) 1.3                                     |
| F   | Clo | 11   | hotspot/jvmti                | 331 | Low-Overhead Heap Profiling                                            |
| F   | Clo | 11   | tools/javac                  | 330 | Launch Single-File Source-Code Programs                                |
| F   | Clo | 11   | security/javax.crypto        | 329 | ChaCha20 and Poly1305 Cryptographic Algorithms                         |
| F   | Clo | 11   | hotspot/jfr                  | 328 | Flight Recorder                                                        |
| F   | Clo | 11   | core/lang                    | 327 | Unicode 10                                                             |
| F   | Clo | 12   | spec/lang                    | 325 | Switch Expressions (Preview)                                           |
| F   | Clo | 11   | security/javax.crypto        | 324 | Key Agreement with Curve25519 and Curve448                             |
| F   | Clo | 11   | tools/—                      | 323 | Local-Variable Syntax for Lambda Parameters                            |
| F   | Clo | 10   | core/lang                    | 322 | Time-Based Release Versioning                                          |
| F   | Clo | 11   | core/net                     | 321 | HTTP Client API                                                        |
| F   | Clo | 11   | other/—                      | 320 | Remove the Java EE and CORBA Modules                                   |
| F   | Clo | 10   | security/security            | 319 | Root Certificates                                                      |
| F   | Clo | 11   | hotspot/gc                   | 318 | Epsilon: A No-Op Garbage Collector (Experimental)                      |
| F   | Clo | 10   | hotspot/compiler             | 317 | Experimental Java-Based JIT Compiler                                   |
| F   | Clo | 10   | hotspot/gc                   | 316 | Heap Allocation on Alternative Memory Devices                          |
| F   | Clo | 11   | hotspot/compiler             | 315 | Improve Aarch64 Intrinsics                                             |
| F   | Clo | 10   | core/util:i18n               | 314 | Additional Unicode Language-Tag Extensions                             |
| F   | Clo | 10   | tools/javah                  | 313 | Remove the Native-Header Generation Tool (javah)                       |
| F   | Clo | 10   | hotspot/runtime              | 312 | Thread-Local Handshakes                                                |
| F   | Clo | 10   | hotspot/runtime              | 310 | Application Class-Data Sharing                                         |
| F   | Clo | 11   | hotspot/runtime              | 309 | Dynamic Class-File Constants                                           |
| F   | Clo | 10   | hotspot/gc                   | 307 | Parallel Full GC for G1                                                |
| F   | Clo | 17   | spec/lang                    | 306 | Restore Always-Strict Floating-Point Semantics                         |
| F   | Clo | 14   | spec/lang                    | 305 | Pattern Matching for instanceof (Preview)                              |
| F   | Clo | 10   | hotspot/gc                   | 304 | Garbage Collector Interface                                            |
| S   | Clo | 9    |                              | 299 | Reorganize Documentation                                               |
| F   | Clo | 9    | infrastructure/—             | 298 | Remove Demos and Samples                                               |
| F   | Clo | 9    | hotspot/compiler             | 297 | Unified arm32/arm64 Port                                               |
| S   | Clo | 10   |                              | 296 | Consolidate the JDK Forest into a Single Repository                    |
| F   | Clo | 9    | hotspot/compiler             | 295 | Ahead-of-Time Compilation                                              |
| F   | Clo | 9    | hotspot/compiler             | 294 | Linux/s390x Port                                                       |
| F   | Clo | 9    | core/jdk.nashorn             | 292 | Implement Selected ECMAScript 6 Features in Nashorn                    |
| F   | Clo | 9    | hotspot/gc                   | 291 | Deprecate the Concurrent Mark Sweep (CMS) Garbage Collector            |
| F   | Clo | 9    | core/io:serialization        | 290 | Filter Incoming Serialization Data                                     |
| F   | Clo | 9    | client/—                     | 289 | Deprecate the Applet API                                               |
| F   | Clo | 9    | security/security            | 288 | Disable SHA-1 Certificates                                             |
| F   | Clo | 9    | security/security            | 287 | SHA-3 Hash Algorithms                                                  |
| F   | Clo | 10   | tools/—                      | 286 | Local-Variable Type Inference                                          |
| F   | Clo | 9    | core/lang                    | 285 | Spin-Wait Hints                                                        |
| S   | Clo | 9    |                              | 284 | New HotSpot Build System                                               |
| F   | Clo | 9    | javafx/window-toolkit        | 283 | Enable GTK 3 on Linux                                                  |
| F   | Clo | 9    | tools/jlink                  | 282 | jlink: The Java Linker                                                 |
| F   | Clo | 9    | hotspot/test                 | 281 | HotSpot C++ Unit-Test Framework                                        |
| F   | Clo | 9    | tools/javac                  | 280 | Indify String Concatenation                                            |
| F   | Clo | 9    | —/—                          | 279 | Improve Test-Failure Troubleshooting                                   |
| F   | Clo | 9    | hotspot/gc                   | 278 | Additional Tests for Humongous Objects in G1                           |
| F   | Clo | 9    | core/lang                    | 277 | Enhanced Deprecation                                                   |
| F   | Clo | 9    | core/lang.invoke             | 276 | Dynamic Linking of Language-Defined Object Models                      |
| F   | Clo | 9    | deploy/packager              | 275 | Modular Java Application Packaging                                     |
| F   | Clo | 9    | core/lang.invoke             | 274 | Enhanced Method Handles                                                |
| F   | Clo | 9    | security/security            | 273 | DRBG-Based SecureRandom Implementations                                |
| F   | Clo | 9    | client/awt                   | 272 | Platform-Specific Desktop Features                                     |
| F   | Clo | 9    | hotspot/gc                   | 271 | Unified GC Logging                                                     |
| F   | Clo | 9    | hotspot/runtime              | 270 | Reserved Stack Areas for Critical Sections                             |
| F   | Clo | 9    | core/util:collections        | 269 | Convenience Factory Methods for Collections                            |
| F   | Clo | 9    | xml/jaxp                     | 268 | XML Catalogs                                                           |
| F   | Clo | 9    | core/lang                    | 267 | Unicode 8.0                                                            |
| F   | Clo | 9    | core/util.concurrent         | 266 | More Concurrency Updates                                               |
| F   | Clo | 9    | client/2d                    | 265 | Marlin Graphics Renderer                                               |
| F   | Clo | 9    | core/util.logging            | 264 | Platform Logging API and Service                                       |
| F   | Clo | 9    | client/awt                   | 263 | HiDPI Graphics on Windows and Linux                                    |
| F   | Clo | 9    | client/javax.imageio         | 262 | TIFF Image I/O                                                         |
| F   | Clo | 9    | —/—                          | 261 | Module System                                                          |
| F   | Clo | 9    | —/—                          | 260 | Encapsulate Most Internal APIs                                         |
| F   | Clo | 9    | core/—                       | 259 | Stack-Walking API                                                      |
| F   | Clo | 9    | client/2d                    | 258 | HarfBuzz Font-Layout Engine                                            |
| F   | Clo | 9    | javafx/media                 | 257 | Update JavaFX/Media to Newer Version of GStreamer                      |
| F   | Clo | 9    | client/beans                 | 256 | BeanInfo Annotations                                                   |
| F   | Clo | 9    | xml/jaxp                     | 255 | Merge Selected Xerces 2.11.0 Updates into JAXP                         |
| F   | Clo | 9    | core/lang                    | 254 | Compact Strings                                                        |
| F   | Clo | 9    | javafx/controls              | 253 | Prepare JavaFX UI Controls & CSS APIs for Modularization               |
| F   | Clo | 9    | core/util:i18n               | 252 | Use CLDR Locale Data by Default                                        |
| F   | Clo | 9    | client/2d                    | 251 | Multi-Resolution Images                                                |
| F   | Clo | 9    | hotspot/runtime              | 250 | Store Interned Strings in CDS Archives                                 |
| F   | Clo | 9    | security/javax.net.ssl       | 249 | OCSP Stapling for TLS                                                  |
| F   | Clo | 9    | hotspot/gc                   | 248 | Make G1 the Default Garbage Collector                                  |
| F   | Clo | 9    | tools/javac                  | 247 | Compile for Older Platform Versions                                    |
| F   | Clo | 9    | security/javax.crypto        | 246 | Leverage CPU Instructions for GHASH and RSA                            |
| F   | Clo | 9    | hotspot/runtime              | 245 | Validate JVM Command-Line Flag Arguments                               |
| F   | Clo | 9    | security/javax.net.ssl       | 244 | TLS Application-Layer Protocol Negotiation Extension                   |
| F   | Clo | 9    | hotspot/compiler             | 243 | Java-Level JVM Compiler Interface/JVM编译器接口                        |
| F   | Clo | 8u60 | hotspot/gc                   | 242 | JVM Trace Events for Failed Allocations                                |
| F   | Clo | 9    | core-svc/tools               | 241 | Remove the jhat Tool                                                   |
| F   | Clo | 9    | core-svc/tools               | 240 | Remove the JVM TI hprof Agent                                          |
| F   | Clo | 8u60 | javafx/web                   | 239 | Update JavaFX/WebView to Newer Version of WebKit                       |
| F   | Clo | 9    | tools/jar                    | 238 | Multi-Release JAR Files                                                |
| F   | Clo | 9    | hotspot/compiler             | 237 | Linux/AArch64 Port                                                     |
| F   | Clo | 9    | core/jdk.nashorn             | 236 | Parser API for Nashorn                                                 |
| F   | Clo | 9    | tools/javac                  | 235 | Test Class-File Attributes Generated by javac                          |
| F   | Clo | 9    | hotspot/compiler             | 233 | Generate Run-Time Compiler Tests Automatically                         |
| F   | Clo | 9    | security/security            | 232 | Improve Secure Application Performance                                 |
| F   | Clo | 9    | tools/launcher               | 231 | Remove Launch-Time JRE Version Selection                               |
| F   | Clo | 12   | performance/—                | 230 | Microbenchmark Suite                                                   |
| F   | Clo | 9    | security/security            | 229 | Create PKCS12 Keystores by Default                                     |
| F   | Clo | 9    | hotspot/svc                  | 228 | Add More Diagnostic Commands                                           |
| F   | Clo | 9    | core/lang                    | 227 | Unicode 7.0                                                            |
| F   | Clo | 9    | core/util:i18n               | 226 | UTF-8 Property Resource Bundles                                        |
| F   | Clo | 9    | tools/javadoc(tool)          | 225 | Javadoc Search                                                         |
| F   | Clo | 9    | tools/javadoc(tool)          | 224 | HTML5 Javadoc                                                          |
| F   | Clo | 9    | —/—                          | 223 | New Version-String Scheme                                              |
| F   | Clo | 9    | tools/jshell                 | 222 | jshell: The Java Shell (Read-Eval-Print Loop)                          |
| F   | Clo | 9    | tools/javadoc(tool)          | 221 | New Doclet API                                                         |
| F   | Clo | 9    | —/—                          | 220 | Modular Run-Time Images                                                |
| F   | Clo | 9    | security/javax.net.ssl       | 219 | Datagram Transport Layer Security (DTLS)                               |
| F   | Clo | 9    | tools/javac                  | 217 | Annotations Pipeline 2.0                                               |
| F   | Clo | 9    | tools/javac                  | 216 | Process Import Statements Correctly                                    |
| F   | Clo | 9    | tools/javac                  | 215 | Tiered Attribution for javac                                           |
| F   | Clo | 9    | hotspot/gc                   | 214 | Remove GC Combinations Deprecated in JDK 8                             |
| F   | Clo | 9    | tools/javac                  | 213 | Milling Project Coin                                                   |
| F   | Clo | 9    | tools/—                      | 212 | Resolve Lint and Doclint Warnings                                      |
| F   | Clo | 9    | tools/javac                  | 211 | Elide Deprecation Warnings on Import Statements                        |
| F   | Clo | 8u40 | core/lang.invoke             | 210 | LambdaForm Reduction and Caching                                       |
| F   | Clo | 8u40 | client/—                     | 209 | JavaFX Scene Builder Update                                            |
| F   | Clo | 8u40 | deploy/—                     | 208 | Java Packager Improvements                                             |
| F   | Clo | 8u40 | hotspot/compiler             | 207 | Leverage CPU Instructions to Improve SHA Performance on SPARC          |
| F   | Clo | 8u40 | client/—                     | 206 | Modernize the JavaFX Media Stack on Mac OS X                           |
| F   | Clo | 8u40 | client/—                     | 205 | New Controls for JavaFX                                                |
| F   | Clo | 8u40 | client/—                     | 204 | JavaFX Accessibility                                                   |
| F   | Clo | 8u40 | core/jdk.nashorn             | 203 | Nashorn: Lexically-Scoped Variable & Constant Declarations             |
| F   | Clo | 8u40 | core/jdk.nashorn             | 202 | Nashorn Class Filter                                                   |
| F   | Clo | 9    | —/—                          | 201 | Modular Source Code                                                    |
| F   | Clo | 9    | —/—                          | 200 | The Modular JDK                                                        |
| F   | Clo | 9    | tools/javac                  | 199 | Smart Java Compilation, Phase Two                                      |
| F   | Clo | 9    | hotspot/compiler             | 197 | Segmented Code Cache                                                   |
| F   | Clo | 8u40 | core/jdk.nashorn             | 196 | Nashorn Optimistic Typing                                              |
| F   | Clo | 8u40 | hotspot/runtime              | 195 | Scalable Native Memory Tracking                                        |
| F   | Clo | 9    | core/lang                    | 193 | Variable Handles                                                       |
| F   | Clo | 8u20 | hotspot/gc                   | 192 | String Deduplication in G1                                             |
| F   | Clo | 12   | hotspot/gc                   | 189 | Shenandoah: A Low-Pause-Time Garbage Collector (Experimental)          |
| F   | Clo | 8    | xml/jaxp                     | 185 | Restrict Fetching of External XML Resources                            |
| F   | Clo | 8    | core/net                     | 184 | HTTP URL Permissions                                                   |
| F   | Clo | 11   | hotspot/runtime              | 181 | Nest-Based Access Control                                              |
| F   | Clo | 8    | core/—                       | 180 | Handle Frequent HashMap Collisions with Balanced Trees                 |
| F   | Clo | 8    | —/—                          | 179 | Document JDK API Support and Stability                                 |
| F   | Clo | 8    | core/—                       | 178 | Statically-Linked JNI Libraries                                        |
| F   | Clo | 8    | core/—                       | 177 | Optimize java.text.DecimalFormat.format                                |
| F   | Clo | 8    | —/—                          | 176 | Mechanical Checking of Caller-Sensitive Methods                        |
| F   | Clo | 8u20 | —/—                          | 175 | PowerPC/AIX Port                                                       |
| F   | Clo | 8    | core/jdk.nashorn             | 174 | Nashorn JavaScript Engine                                              |
| F   | Clo | 8    | hotspot/gc                   | 173 | Retire Some Rarely-Used GC Combinations                                |
| F   | Clo | 8    | tools/javadoc(tool)          | 172 | DocLint                                                                |
| F   | Clo | 8    | hotspot/runtime              | 171 | Fence Intrinsics                                                       |
| F   | Clo | 8    | core/—                       | 170 | JDBC 4.2                                                               |
| F   | Clo | 8    | core-svc/—                   | 168 | Network Discovery of Manageable Java Processes                         |
| F   | Clo | 7u40 | hotspot/svc                  | 167 | Event-Based JVM Tracing                                                |
| F   | Clo | 8    | security/—                   | 166 | Overhaul JKS-JCEKS-PKCS12 Keystores                                    |
| F   | Clo | 9    | hotspot/compiler             | 165 | Compiler Control                                                       |
| F   | Clo | 8    | —/—                          | 164 | Leverage CPU Instructions for AES Cryptography                         |
| F   | Clo | 8    | —/—                          | 162 | Prepare for Modularization                                             |
| F   | Clo | 8    | —/—                          | 161 | Compact Profiles                                                       |
| F   | Clo | 8    | —/—                          | 160 | Lambda-Form Representation for Method Handles                          |
| F   | Clo | 9    | hotspot/svc                  | 158 | Unified JVM Logging                                                    |
| F   | Clo | 8u40 | hotspot/gc                   | 156 | G1 GC: Reduce need for full GCs                                        |
| F   | Clo | 8    | core/—                       | 155 | Concurrency Updates                                                    |
| F   | Clo | 8    | client/—                     | 153 | Launch JavaFX Applications                                             |
| F   | Clo | 8    | core/—                       | 150 | Date & Time API                                                        |
| F   | Clo | 8    | core/—                       | 149 | Reduce Core-Library Memory Usage                                       |
| F   | Clo | 8    | hotspot/runtime              | 148 | Small VM                                                               |
| F   | Clo | 8    | hotspot/runtime              | 147 | Reduce Class Metadata Footprint                                        |
| F   | Clo | 9    | hotspot/runtime              | 143 | Improve Contended Locking                                              |
| F   | Clo | 8    | hotspot/gc                   | 142 | Reduce Cache Contention on Specified Fields                            |
| F   | Clo | 8    | security/—                   | 140 | Limited doPrivileged                                                   |
| F   | Clo | 8    | tools/javac                  | 139 | Enhance javac to Improve Build Speed                                   |
| F   | Clo | 8    | —/—                          | 138 | Autoconf-Based Build System                                            |
| F   | Clo | 8    | hotspot/runtime              | 136 | Enhanced Verification Errors                                           |
| F   | Clo | 8    | core/—                       | 135 | Base64 Encoding & Decoding                                             |
| F   | Clo | 8    | core/—                       | 133 | Unicode 6.2                                                            |
| F   | Clo | 8    | security/javax.crypto:pkcs11 | 131 | PKCS#11 Crypto Provider for 64-bit Windows                             |
| F   | Clo | 8    | security/security            | 130 | SHA-224 Message Digests                                                |
| F   | Clo | 8    | security/—                   | 129 | NSA Suite B Cryptographic Algorithms                                   |
| F   | Clo | 8    | core/util:i18n               | 128 | Unicode BCP 47 Locale Matching                                         |
| F   | Clo | 8    | core/util:i18n               | 127 | Improve Locale Data Packaging and Adopt Unicode CLDR Data              |
| F   | Clo | 8    | tools/javac                  | 126 | Lambda Expressions & Virtual Extension Methods                         |
| F   | Clo | 8    | security/security            | 124 | Enhance the Certificate Revocation-Checking API                        |
| F   | Clo | 8    | security/security            | 123 | Configurable Secure Random-Number Generation                           |
| F   | Clo | 8    | hotspot/gc                   | 122 | Remove the Permanent Generation                                        |
| F   | Clo | 8    | security/—                   | 121 | Stronger Algorithms for Password-Based Encryption                      |
| F   | Clo | 8    | spec/lang                    | 120 | Repeating Annotations                                                  |
| F   | Clo | 8    | core/—                       | 119 | javax.lang.model Implementation Backed by Core Reflection              |
| F   | Clo | 8    | spec/vm                      | 118 | Access to Parameter Names at Runtime                                   |
| F   | Clo | 8    | tools/javac                  | 117 | Remove the Annotation-Processing Tool (apt)                            |
| F   | Clo | 8    | security/—                   | 115 | AEAD CipherSuites                                                      |
| F   | Clo | 8    | security/javax.net.ssl       | 114 | TLS Server Name Indication (SNI) Extension                             |
| F   | Clo | 8    | security/—                   | 113 | MS-SFU Kerberos 5 Extensions                                           |
| F   | Clo | 8    | core/—                       | 112 | Charset Implementation Improvements                                    |
| F   | Clo | 9    | core/net                     | 110 | HTTP/2 Client (Incubator)                                              |
| F   | Clo | 8    | core/—                       | 109 | Enhance Core Libraries with Lambda                                     |
| F   | Clo | 8    | core/—                       | 107 | Bulk Data Operations for Collections                                   |
| F   | Clo | 8    | tools/javadoc(tool)          | 106 | Add Javadoc to javax.tools                                             |
| F   | Clo | 8    | tools/javac                  | 105 | DocTree API                                                            |
| F   | Clo | 8    | spec/lang                    | 104 | Type Annotations                                                       |
| F   | Clo | 8    | core/—                       | 103 | Parallel Array Sorting                                                 |
| F   | Clo | 9    | core/lang                    | 102 | Process API Updates                                                    |
| F   | Clo | 8    | spec/lang                    | 101 | Generalized Target-Type Inference                                      |

## Withdrawn JEPs

|     |     |     |                        |     |                                                     |
| --- | --- | --- | ---------------------- | --- | --------------------------------------------------- |
| F   | Clo |     | spec/lang              | 465 | String Templates (Third Preview)                    |
| F   | Clo |     | hotspot/svc            | 435 | Asynchronous Stack Trace VM API                     |
| F   | Clo |     | tools/—                | 348 | Compiler Intrinsics for Java SE APIs                |
| F   | Clo |     | hotspot/compiler       | 342 | Limit Speculative Execution                         |
| F   | Clo |     | core/nio               | 337 | RDMA Network Sockets                                |
| F   | Clo |     | spec/lang              | 326 | Raw String Literals (Preview)                       |
| F   | Clo |     | deploy/packager        | 311 | Java Packager API & CLI                             |
| F   | Clo |     | hotspot/gc             | 308 | Improve Dynamic Number of Thread Sizing for G1      |
| F   | Clo |     | tools/javac            | 301 | Enhanced Enums                                      |
| F   | Clo |     | core/—                 | 194 | Nashorn Code Persistence                            |
| F   | Clo |     | tools/—                | 191 | Foreign Function Interface                          |
| F   | Clo |     | spec/lang              | 186 | Collection Literals                                 |
| F   | Clo |     | core/net               | 183 | HTTP Cross-Origin Resource Sharing                  |
| F   | Clo |     | hotspot/jvmti          | 159 | Enhanced Class Redefinition                         |
| F   | Clo |     | hotspot/gc             | 157 | G1 GC: NUMA-Aware Allocation                        |
| F   | Clo |     | core/—                 | 154 | Remove Serialization                                |
| F   | Clo |     | security/javax.crypto  | 152 | Crypto Operations with Network HSMs                 |
| F   | Clo |     | core/—                 | 151 | Compress Time-Zone Data                             |
| F   | Clo |     | hotspot/runtime        | 146 | Improve Fatal Error Logs                            |
| F   | Clo |     | hotspot/gc             | 144 | Reduce GC Latency for Large Heaps                   |
| F   | Clo |     | hotspot/gc             | 141 | Increase the Client VM's Default Heap Size          |
| F   | Clo |     | —/—                    | 132 | More-prompt finalization                            |
| F   | Clo |     | core/net               | 125 | Network Interface Aliases, Events, and Defaults     |
| F   | Clo |     | security/javax.net.ssl | 116 | Extended Validation SSL Certificates                |
| F   | Clo |     | core/—                 | 108 | Collections Enhancements from Third-Party Libraries |
