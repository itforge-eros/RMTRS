package th.ac.kmitl.it.rmtrs.util

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import java.io.IOException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LocalDateTimeToString @JvmOverloads constructor(t: Class<LocalDateTime>? = null) : StdSerializer<LocalDateTime>(t) {

    @Throws(IOException::class, JsonProcessingException::class)
    override fun serialize(
            value: LocalDateTime, gen: JsonGenerator, arg2: SerializerProvider) {
        gen.writeString(value.format(formatter))
    }

    companion object {
        private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")
    }
}