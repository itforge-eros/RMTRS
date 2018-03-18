package th.ac.kmitl.it.rmtrs.util

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

object JSON {
    val mapper = jacksonObjectMapper()

    inline fun <reified T: Any> parse(string: String) = mapper.readValue<T>(string)
    inline fun <reified T: Any> stringify(obj: T) = mapper.writeValueAsString(obj)

    fun <T> toResponseMap(obj: T) =JSON.mapper.convertValue(obj, Map::class.java)
}