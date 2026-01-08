/**
 * Demonstration of Kotlin visibility modifier.
 */
package com.spike.language.kotlin.core.packages


class C1 {} // final class, public
open class C2 {} // non final class, public
private class C3 {} // private in file
internal open class C4 {} // non final class, internal
//protected class C5 {} // protected is not applicable in file