@file:JvmName("KotlinAnnotations")

package com.spike.kotlin.c12_annotation_reflection

import com.spike.kotlin.c12_annotation_reflection.jkid.CustomSerializer
import com.spike.kotlin.c12_annotation_reflection.jkid.DateSerializer
import com.spike.kotlin.c12_annotation_reflection.jkid.DeserializeInterface
import java.util.*

@JvmName("performCalculate")
fun calculate(): Int {
    return (2 + 2) - 1
}

class CertificateManager {
    // get, set: use-site target declaration
    @get:JvmName("obtainCertificate")
    @set:JvmName("putCertificate")
    var certificate: String = "-----BEGIN PRIVATE KEY-----"
}

fun test(list: List<*>) {
    @Suppress("UNCHECKED_CAST") // annotation on expressions
    val strings = list as List<String>
    println(strings)
}

@Target(AnnotationTarget.ANNOTATION_CLASS)
annotation class BindingAnnotation // a meta-annotation

@BindingAnnotation
annotation class MyBinding

// class as annotation parameters

interface Company {
    val name: String
}

data class CompanyImpl(override val name: String) : Company

data class Person(
    val name: String,
    @DeserializeInterface(CompanyImpl::class) val company: Company
)

// generic classes as annotation parameters
data class Person2(
    val name: String,
    @CustomSerializer(DateSerializer::class) val birthDate: Date
)