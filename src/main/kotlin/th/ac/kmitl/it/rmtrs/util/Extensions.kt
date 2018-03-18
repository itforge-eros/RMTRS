package th.ac.kmitl.it.rmtrs.util

import th.ac.kmitl.it.rmtrs.model.*
import th.ac.kmitl.it.rmtrs.payload.*
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

fun Production.toResponse()
        = ProductionResponse(id, name)

fun Actor.toResponse()
        = ActorResponse(id, fname, mname, lname)

fun Director.toResponse()
        = DirectorResponse(id, fname, mname, lname)

fun Genre.toResponse()
        = GenreResponse(id, name)

fun ActorRequest.toModel()
        = Actor(fname = fname, mname = mname, lname = lname)

fun DirectorRequest.toModel()
        = Director(fname = fname, mname = mname, lname = lname)

fun GenreRequest.toModel()
        = Genre(name = name)

fun ProductionRequest.toModel()
        = Production(name = name)

fun MovieRequest.toModel()
        = Movie(
        th_title = th_title,
        en_title = en_title,
        synopsis = synopsis,
        duration = duration,
        posterUrl = posterUrl,
        trailerUrl = trailerUrl,
        releaseDate = releaseDate,
        endDate = endDate,
        rate = rate
)
