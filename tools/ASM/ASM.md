# ASM
* https://asm.ow2.io
* https://gitlab.ow2.org/asm/asm

> ASM is an all purpose Java bytecode manipulation and analysis framework. It can be used to modify existing classes or to dynamically generate classes, directly in binary form. ASM provides some common bytecode transformations and analysis algorithms from which custom complex transformations and code analysis tools can be built. ASM offers similar functionality as other Java bytecode frameworks, but is focused on performance. Because it was designed and implemented to be as small and as fast as possible, it is well suited for use in dynamic systems (but can of course be used in a static way too, e.g. in compilers).

ASM is used in many projects, including:
- the **OpenJDK**, to generate the lambda call sites, and also in the Nashorn compiler,
- the **Groovy** compiler and the **Kotlin** compiler,
- **Cobertura** and **Jacoco**, to instrument classes in order to measure code coverage,
- **Byte Buddy**, to dynamically generate classes, itself used in other projects such as **Mockito** (to generate mock classes),
- **Gradle**, to generate some classes at runtime.

JJava
```java
%maven org.ow2.asm:asm:9.9.1

import org.objectweb.asm.*;
import static org.objectweb.asm.Opcodes.*;
```

# Use Guide

The goal of the ASM library is to generate, transform and analyze compiled Java classes, represented as byte arrays.

2 APIs
- the core API provides an event based representation of classes/基于事件的表示
  - event producer/class parser, event consumer/class writer, event filters
- the tree API provides an object based representation/基于对象的表示 - built on top of core API
  - class generator, class transformer

UML: [ASM.uxf](./ASM.uxf)

## Core API: asm, asm-util, asm-commons

classes
- structure: JVM specification
  - internal names
  - type descriptors
  - method descriptors
- interfaces and components
  - presentation: `ClassVisitor`, `AnnotationVisitor`, `FieldVisitor`, `MethodVisitor`, `ClassReader`, `ClassWriter`
  - parsing classes
  - generating classes
  - transforming classes
  - removing class members
  - adding class members
  - transformation chain 
- tools
  - `Type`
  - `TraceClassVisitor`
  - `CheckClassAdapter`
  - `ASMifier`, `Textifier`

methods
- structure
  - execution model
  - bytecode instructions
    - 2 categories
      - 1. transfer values from local variables to the operand stack and vice versa 
      - 2. act on the operand stack: pop values from stack, compute result based on the values and push on stack
  - examples
  - exception handlers
  - frames
- interfaces and components
  - presentation: `MethodVisitor`
  - generating methods
  - transforming methods
  - stateless transformations
  - stateful transformations
- tools
  - basic tools: `Type`, `CheckClassAdapter`, `ASMifier`
  - `AnalyzerAdapter`
  - `LocalVariablesSorter`
  - `AdviceAdapter`

metadata
- generics/泛型
  - structure
    - signatures/签名: 类型, 方法, 类
  - interfaces and components
    - `SignatureVisitor`, `SignatureReader`, `SignatureWriter`
  - tools
    - `TraceClassVisitor`, `ASMifier`
- annotations/注解
  - structure
  - interfaces and components
    - `AnnotationVisitor`
  - tools
    - `TraceClassVisitor`, `CheckClassAdapter`, `ASMifier`
    - `TraceAnnotationVisitor`, `CheckAnnotationAdapter`
- debug/调试: `javac -g`
  - structure
    - source file name of a class
    - mapping between source line numbers and bytecode instructions
    - mapping between local variable names in source code and local variable slots in bytecode
  - interfaces and components
    - `ClassVisitor.visitSource`
    - `MethodVisitor.visitLineNumber`
    - `MethodVisitor.visitLocalVariable`
    - `SKIP_DEBUG` option
  - tools
    - `TraceClassVisitor`, `CheckClassAdapter`, `ASMifier`

backward compatibility
- contract
- guidelines

## Tree API: asm-tree, asm-analysis

classes
- interfaces and components
  - `ClassNode`, `FieldNode`, `MethodNode`
  - generating classes
  - adding and removing class members
- components composition
  - presentation
    - construct `ClassNode` from byte array or vice versa
    - `class ClassNode extends ClassVisitor {}`
  - patterns 

methods
- interfaces and components
  - `MethodNode`
  - `InsnList`
  - `AbstractInsnNode`: `XxxInsnNode`
  - generating methods
  - transforming methods
  - stateless and satefull trnsformations
  - global transformations
- components composition
  - presentation
    - `class MethodNode extends MethodVisitor {}`
  - patterns

method analysis
- presentation
  - data flow analysis: compute the state of the execution frames of a method for each instruction of the method
    - forward, backward
  - control flow analysis: compute the control flow graph of a method and perform analyses on the graph
- interfaces and components
  - `org.objectweb.asm.tree.analysis`
    - data flow analysis
      - `Analyzer`, `Frame`: overall data flow algorithms, the task of pop from stack and push back to stack, the appropriate number of values
      - `Interpreter`, `Value` user defined subclasses: task of combine values and compute unions of value sets
    - control flow analysis
      - `Analyzer` `newControlFlowEdge`, `newControlFlowExceptionEdge`
  - basic data flow analysis
    - `BasicInterpreter`: `BasicValue` 7 sets of values
  - basic data flow verifier
    - `BasicVerifier`
  - simple data flow vrifier
    - `SimpleVerifier`
    - `AnalyzerAdapter`
  - user defined data flow analysis
    - ex: detect field accesses and method calls on potentially `null` objects
  - control flow analysis
    - `Analyzer` `newControlFlowEdge`

metadata
- generics
  - `SignatureNode`
- annotation
  - `AnnotationNode`
- debug
  - `ClassNode` `sourceFile`, `LineNumberNode`, `LocalVariableNode`

backward compatibility

# Developer Guide
* https://asm.ow2.io/developer-guide.html

projects
- asm
  - org.objectweb.asm, org.objectweb.asm.signature
- asm-analysis
  - org.objectweb.asm.tree.analysis
  - requires: asm-tree
- asm-commons
  - org.objectweb.asm.commons
  - requires: asm, asm-tree
- asm-test
  - org.objectweb.asm.test
- asm-tree
  - org.objectweb.asm.tree
  - requires: asm
- asm-util
  - org.objectweb.asm.util
  - requires: asm, asm-tree, asm-analysis
- benchmarks
- tools: Tools used to build ASM
- tools:retrofitter: JDK 1.5 class retrofitter/改造者 based on ASM

packages
- `org.objectweb.asm` is the core package. It defines **the ASM visitor API** and provides the `ClassReader` and `ClassWriter` classes to read and write compiled Java classes. This package does not depend on the other ones and can be used alone.
- `org.objectweb.asm.signature` provides an API to **read and write generics signatures**. It is independent of the core package but complements it.
- `org.objectweb.asm.tree` provides **a DOM-like API** on top of the SAX-like API provided by the core package. It can be used to implement complex class transformations, for which the core package would be too complicated to use.
- `org.objectweb.asm.tree.analysis` provides **a static bytecode analysis framework** on top of the tree package. It can be used in addition to the tree package to implement really complex class transformations that need to know the state of the stack map frames for each instruction.
- `org.objectweb.asm.commons` provides **some useful class adapters that are based on the core and tree packages**. These adapters can be used as is or can be extended to implement more specific class transformations.
- `org.objectweb.asm.util` provides **some useful class visitors and adapters that can be used for debugging purposes**. It is generally not needed at runtime.
- `org.objectweb.asm.xml` is **deprecated**. It provided the ability to convert classes to and from XML.

# See Also
* [ASM 6 Developer Guide](https://asm.ow2.io/developer-guide.html)
* [IDEA Plugin - ASM Bytecode Viewer](https://plugins.jetbrains.com/plugin/10302-asm-bytecode-viewer): Displays bytecode for Java classes and ASMified code which will help you in your class generation.