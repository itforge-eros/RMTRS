package th.ac.kmitl.it.rmtrs.util

import th.ac.kmitl.it.rmtrs.model.*
import th.ac.kmitl.it.rmtrs.payload.*
import java.io.InputStream
import java.security.DigestInputStream
import java.security.MessageDigest
import java.time.LocalDate
import java.time.LocalDateTime
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

fun ScreeningRequest.toModel()
        = Screening(showDate = showDate, showTime = showTime)

@Suppress("UNCHECKED_CAST")
fun Movie.toMovieWithDetail(): Map<String, Any>
        = JSON.toResponseMap(this)
        .plus(mapOf(
                "actors" to actors,
                "directors" to directors,
                "genres" to genres,
                "productions" to productions
        )) as Map<String, Any>

@Suppress("UNCHECKED_CAST")
fun Screening.toScreeningWithDetail(): Map<String, Any>
        = JSON.toResponseMap(this)
        .plus(mapOf("theatre_id" to theatre.id, "movie" to movie)) as Map<String, Any>

fun isOverlap(start1: LocalDateTime, start2: LocalDateTime, end1: LocalDateTime, end2: LocalDateTime): Boolean
        = maxOf(start1, start2).isBefore(minOf(end1, end2))
