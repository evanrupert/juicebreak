package io.juicebreak

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import kotlin.reflect.KClass

object JSON {
    val mapper: ObjectMapper = ObjectMapper().registerKotlinModule()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    inline fun <reified T : Any> read(str: String): T =
        mapper.readValue(str)

    fun write(value: Any): String = mapper.writeValueAsString(value)
}
