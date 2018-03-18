package th.ac.kmitl.it.rmtrs.util

import java.io.InputStream
import java.security.DigestInputStream
import java.security.MessageDigest
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun InputStream.sha1()
        = MessageDigest.getInstance("SHA-1")
        .let { DigestInputStream(this, it).messageDigest.digest() }
        .fold("") {str, byte -> str + "%02x".format(byte)}

fun String.toLocalDate() = LocalDate.parse(this, DateTimeFormatter.ISO_LOCAL_DATE)
