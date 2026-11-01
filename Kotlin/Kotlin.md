# Kotlin
* https://kotlinlang.org/
* https://github.com/JetBrains/kotlin

> Kotlin is a modern but already mature programming language designed to make developers happier. It's concise, safe, interoperable with Java and other languages, and provides many ways to reuse code between multiple platforms for productive programming.

# Language
* [Kotlin Language Specification](https://github.com/Kotlin/kotlin-spec)

- Home
- Get started
- Take Kotlin tour
  - Kotlin tour overview
  - Beginner 0/7
  - Intermediate 0/9
- What's new in Kotlin
  - Kotlin 2.4.20
  - Kotlin 2.5.0-Beta1
  - Kotlin 2.4.0
  - Compatibility guide
- Language guide
  - Basic syntax
  - Coding conventions
  - Idioms
  - Types
  - Control flow
  - Exception handling
  - Functions
  - Classes and interfaces
  - Null safety
  - Equality
  - Generics
  - Concurrency
  - Reflection: `kotlin-reflect`, `kotlin.reflect.full`, `kotlin.reflect.jvm`
  - Destructuring declarations
  - Language reference
- Development
  - Backend development
  - Web development
  - Native development
  - Android development
  - AI development
  - Designing your library
  - Kotlin custom scripting
- Development tooling
  - IDEs for Kotlin development
  - AI tools
  - Kotlin AI skills
  - Run code snippets
  - Kotlin Language Server
  - **Dokka**: Dokka is an API documentation engine for Kotlin.
  - **KDoc**: Document Kotlin code
  - OSGi
  - TeamCity
- Interoperability
  - Java interop
  - JavaScript interop
  - Swift/Objective-C and C interop
- Library guides
  - Standard library
  - Coroutines (`kotlinx.coroutines`)
  - Serialization (`kotlinx.serialization`)
  - Kotlin Metadata JVM (`kotlin-metadata-jvm`)
  - Data analysis
  - Lincheck (`kotlinx.lincheck`)
  - Ktor
  - Exposed
- API reference
  - Overview
  - Standard library (`stdlib`)
  - Test library (`kotlin.test`)
  - Coroutines (`kotlinx.coroutines`)
  - Serialization (`kotlinx.serialization`)
  - Kotlin I/O library (`kotlinx-io`)
  - Date and time (`kotlinx-datetime`)
  - Immutable collections (`kotlinx.collections.immutable`)
  - JVM Metadata (`kotlinx-metadata-jvm`)
  - Ktor
  - Kotlin Gradle plugins (`kotlin-gradle-plugin`)
  - Compose Multiplatform Material3
- Build tools
  - Gradle
  - Maven
  - Build tools API
  - Kotlin daemon
- Compiler and plugins
  - Kotlin compiler
  - Kotlin compiler plugins
  - Compose compiler
  - Kotlin Symbol Processing API
- Kotlin evolution and roadmap
  - Kotlin roadmap
  - Language features and proposals
  - Language evolution principles
  - Stability of Kotlin components
  - Kotlin release process
  - Earlier Kotlin releases
- Early access preview (EAP)
  - Participate
  - Configure your build for EAP
- Learning materials
  - Overview
  - Kotlin Core track
  - Kotlin hands-on
  - Kotlin tips
  - Kotlin Koans
  - Kotlin books
  - Advent of Code puzzles
  - Learn in IDE (JetBrains Academy)
- Other resources
  - FAQ
  - Kotlin Foundation
  - Google Summer of Code
  - Community
  - Kotlin brand assets
  - Kotlin documentation as PDF
  - Security
  - Press kit

## KDoc

```kotlin
/**
 * A group of *members*.
 *
 * This class has no useful logic; it's just a documentation example.
 *
 * @param T the type of a member in this group.
 * @property name the name of this group.
 * @constructor Creates an empty group.
 */
class Group<T>(val name: String) {
    /**
     * Adds a [member] to this group.
     * @return the new size of the group.
     */
    fun add(member: T): Int { ... }
}
```

block tags
```kotlin
@param name description.
@param[name] description.

@return
@constructor // primary constructor of a class
@receiver // receiver of an extension function
@property name // property of a class
@throws class, @exception class
@sample identifier
@see identifier
@author
@since
@suppress

// no support @deprecated: use @Deprecated annotation
```

inline markup
```kotlin
// links to elements
Use the method [foo] for this purpose.
Use [this method][foo] for this purpose.
Use [kotlin.reflect.KClass.properties] to enumerate the properties of the class.

// external links
For more information about KDoc syntax, see [KDoc](https://kotlinlang.org/docs/kotlin-doc.html).
```


## Dokka


# KMP(Kotlin Multiplatform)
* https://kotlinlang.org/docs/multiplatform/kmp-overview.html

Kotlin Multiplatform (KMP) is an open-source technology from JetBrains that enables sharing code across Android, iOS, desktop, web, and server while retaining the advantages of native development.

With **Compose Multiplatform**, you can also share UI code across multiple platforms for maximum code reuse.

# Tools

* [Exposed](https://github.com/JetBrains/Exposed): Exposed is a lightweight SQL library on top of a database connectivity driver for the Kotlin programming language, with support for both JDBC and R2DBC (since version 1.0.0-*) drivers. It offers two approaches for database access: a typesafe SQL-wrapping Domain-Specific Language (DSL) and a lightweight Data Access Object (DAO) API.
* [http4k](https://github.com/http4k/http4k): http4k is a lightweight but fully-featured HTTP toolkit written in pure Kotlin that enables the serving and consuming of HTTP services in a functional and consistent way. http4k applications are just Kotlin functions.
* [Kotest](https://github.com/kotest/kotest): Kotest is a flexible and comprehensive testing tool for Kotlin with multiplatform support.
* [ktor](https://github.com/ktorio/ktor): Ktor is an asynchronous framework for creating microservices, web applications and more. Written in Kotlin from the ground up.
* [KGP(Kotlin Gradle Plugin)](https://github.com/JetBrains/kotlin/blob/master/libraries/tools/kotlin-gradle-plugin/ReadMe.md)
* [kotlinx.html](https://github.com/Kotlin/kotlinx.html)

# See Also
* [Awesome Kotlin](https://github.com/Heapy/awesome-kotlin): A curated list of awesome Kotlin related stuff Inspired by awesome-java.
* [KEEP - Kotlin Evolution and Enhancement Process](https://github.com/Kotlin/KEEP)
