package th.ac.kmitl.it.rmtrs.util

import com.fasterxml.jackson.core.JsonProcessingException
import java.io.IOException
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.core.JsonGenerator
import java.text.SimpleDateFormat
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class LocalDateToString @JvmOverloads constructor(t: Class<LocalDate>? = null) : StdSerializer<LocalDate>(t) {

    @Throws(IOException::class, JsonProcessingException::class)
    override fun serialize(
            value: LocalDate, gen: JsonGenerator, arg2: SerializerProvider) {
            gen.writeString(value.format(formatter))
    }

    companion object {
        private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    }
}