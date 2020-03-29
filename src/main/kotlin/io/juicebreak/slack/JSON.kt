package io.juicebreak.slack

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

object JSON {
    val mapper: ObjectMapper = ObjectMapper().registerKotlinModule()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    inline fun <reified T : Any> read(str: String): T =
        mapper.readValue(str)

    fun readDynamic(str: String): JsonNode =
        read(str)

    fun write(value: Any): String = mapper.writeValueAsString(value)
}
