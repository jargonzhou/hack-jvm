# Byte Buddy
* https://bytebuddy.net
* https://github.com/raphw/byte-buddy

> Byte Buddy is a code generation and manipulation library for creating and modifying Java classes during the runtime of a Java application and without the help of a compiler. Other than the code generation utilities that ship with the Java Class Library, Byte Buddy allows the creation of arbitrary classes and is not limited to implementing interfaces for the creation of runtime proxies. Furthermore, Byte Buddy offers a convenient API for changing classes either manually, using a Java agent or during a build.

# Tutorial
* https://bytebuddy.net/#/tutorial

- Preliminary
  - Writing a security library
  - General information
- Creating a class/创建类
  - Domain specific language and immutability: `ByteBuddy`, `DynamicType.Unloaded`, `NamingStrategy`
  - Redefining and rebasing existing classes
    - type redefinition/类型重定义
    - type rebasing/类型变基
  - Loading a class/加载类: `ClassLoadingStrategy`, `TypeDescription`, `ClassFileLocator`
  - Reloading a class/重新加载类: `ClassReloadingStrategy`
  - Working with unloaded classes/使用未加载的类: `DynamicType.Unloaded`, `TypePool`
  - Creating Java agents/创建Java代理: `ByteBuddyAgent`, `AgentBuilder`
  - Loading classes in Android applications
  - Working with generic types/使用泛型: `TypeDescription.Generic.Builder`, `MethodGraph.Compiler`
- Fields and methods/字段和方法: `ElementMatchers`, `ElementMatcher`, `Implementation`
  - A closer look at fixed values: `FixedValue`, `TypeInitializer`
  - Delegating a method call/委托方法调用: `MethodDelegation`, annotations...
    - `@Argument`, `@AllArguments`, `@This`, `@Super`, `@Origin`
    - Accessing super implementations/访问父实现: `@SuperCall`, `@Super`
    - Checked exceptions
    - Dynamic types for interceptor parameters/拦截器参数的动态类型: `@RuntimeType`
    - Delegation to an interface's default method/委托给接口的默认方法: `@DefaultCall`, `@Default`
    - Forwarding method invocations/转发方法调用: `@Pipe`, `Pipe.Binder`
    - Ambiguity resolver for selection of target methods/选择目标方法的歧义解析器: `AmbiguityResolver`, `@BindingPriority`, `@IgnoreForBinding`, `BindingMechanic`
    - Delegating calls to instance methods/委托调用到实例方法
    - Independence from Byte Buddy
    - Other MethodDelegation annotations: `@Empty`, `@StubValue`, `@FieldValue`, `@FieldProxy`, `@Morph`, `@SuperMethod`, `@DefaultMethod`
  - Calling a super method/调用父方法: `SuperMethodCall`
  - Calling a default method/调用默认方法: `DefaultMethodCall`
  - Calling a specific method/调用指定方法: `MethodCall`
  - Accessing fields/访问字段: `FieldAccessor`
  - Miscellaneous: `StubMethod`, `ExceptionMethod`, `InvocationHandlerAdapter`, `InvokeDynamic`
- Annotations/注解
  - Annotating methods and fields/注解方法和字段: `AnnotationDescription`
  - Type annotations/类型注解: `TypeDescription.Generic`
  - Attribute appenders/属性追加器: `TypeAttributeAppender`, `MethodAttributeAppender`, `FieldAttributeAppender`
- Custom instrumentation/自定义插桩: `StackManipulation`, `ByteCodeAppender`
  - Jump instructions
    - Creating a custom assigner: `Assigner`
    - Creating a custom parameter binder: `ParameterBinder`


# API
* https://javadoc.io/doc/net.bytebuddy/byte-buddy

- `net.bytebuddy`: Byte Buddy is a library for creating Java classes at runtime of a Java program.
  - `net.bytebuddy.agent.builder`: An agent builder is used to easily implement load-time class-transformations using a Java agent.
  - `net.bytebuddy.asm`: The ASM package contains classes that are meant for direct interaction with the ASM API.
  - `net.bytebuddy.build`: A package for types that allow for applying Byte Buddy transformation during a build process.
  - `net.bytebuddy.description`: Classes of this package allow the representation of Java classes, their member and their meta data.
    - `net.bytebuddy.description.annotation`: Contains descriptions of annotations and annotation values.
    - `net.bytebuddy.description.enumeration`: A package that contains classes for describing enumeration values.
    - `net.bytebuddy.description.field`: Contains descriptions of Java fields.
    - `net.bytebuddy.description.method`: Contains descriptions of Java methods and constructors as well as their parameters.
    - `net.bytebuddy.description.modifier`: The modifier package contains high-level and type-safe descriptions of Java modifiers.
    - `net.bytebuddy.description.module`: Contains descriptions of Java modules.
    - `net.bytebuddy.description.type`: Contains descriptions of Java types and packages.
  - `net.bytebuddy.dynamic`: This package contains classes and interfaces that are connected to writing the byte stream that represents a Java type that is dynamically created and for loading this type into a running JVM process.
    - `net.bytebuddy.dynamic.loading`: This package contains classes that are responsible for class loading of classes that are represented by byte arrays.
    - `net.bytebuddy.dynamic.scaffold`: This package contains helper types and implementations that are responsible for the actual writing of a byte array representing a Java class.
      - `net.bytebuddy.dynamic.scaffold.inline`: All classes and types in this package are related to creating a `DynamicType` by enhancing a given type.
      - `net.bytebuddy.dynamic.scaffold.subclass`: All classes and types in this package are related to creating a `DynamicType` by creating a subclass of a given type.
  - `net.bytebuddy.implementation`: The implementation package contains any logic for intercepting method calls.
    - `net.bytebuddy.implementation.attribute`: All types and classes in this package are responsible for writing attributes for a given Java byte code element, i.e.
    - `net.bytebuddy.implementation.auxiliary`: Auxiliary types describe helper types that aid as a supplementary to a given `InstrumentedType`.
    - `net.bytebuddy.implementation.bind`: The types and classes of this package are responsible for binding a method call to calling another method.
    - `net.bytebuddy.implementation.bind.annotation`: This package contains annotations, types and classes that are responsible for binding a method to calling another method by interpreting annotations that indicate how a method should be bound to another method.
    - `net.bytebuddy.implementation.bytecode`: Types and classes in this package are responsible for creating Java byte code for a given byte code target which is represented by a `MethodDescription`.
      - `net.bytebuddy.implementation.bytecode.assign`: An `Assigner` is responsible for transforming a given `TypeDescription` into another one.
      - `net.bytebuddy.implementation.bytecode.assign.primitive`: `Assigner` implementations of this package are capable of handling primitive types or the void type.
      - `net.bytebuddy.implementation.bytecode.assign.reference`: `Assigner` implementations of this package are capable of assigning non-primitive types to each other.
      - `net.bytebuddy.implementation.bytecode.collection`: This package is dedicated to creating `StackManipulation`s that create collections or arrays from a given number of values.
      - `net.bytebuddy.implementation.bytecode.constant`: `StackManipulation`s in this package are responsible for creating compile-time constants and pushing them onto the operand stack.
      - `net.bytebuddy.implementation.bytecode.member`: `StackManipulation`s of this package are responsible for accessing type or method members, i.e.
  - `net.bytebuddy.matcher`: Contains an API for matching Java byte code entities.
  - `net.bytebuddy.pool`: Classes of this package allow for the creating `TypeDescription`s without loading any classes.
  - `net.bytebuddy.utility`: This package contains utility classes for common use within any Byte Buddy logic.
    - `net.bytebuddy.utility.dispatcher`: A package to handle dispatching of classes.
    - `net.bytebuddy.utility.nullability`: A package to contain nullability annotations to be used within the Byte Buddy agent project.
    - `net.bytebuddy.utility.privilege`: A package containing `PrivilegedActions` that are used for invoking sensitive methods.
    - `net.bytebuddy.utility.visitor`: A package containing visitor classes for ASM.
