package com.spike.kotlin.c12_annotation_reflection;

public class Foo {
    public static void main(String[] args) {

        var certManager = new CertificateManager();
        var cert = certManager.obtainCertificate();
        System.out.println(cert); // -----BEGIN PRIVATE KEY-----
        certManager.putCertificate("-----BEGIN CERTIFICATE-----");
        cert = certManager.obtainCertificate();
        System.out.println(cert); // -----BEGIN CERTIFICATE-----
    }
}
