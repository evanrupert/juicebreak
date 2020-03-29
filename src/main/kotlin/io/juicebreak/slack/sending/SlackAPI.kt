package io.juicebreak.slack.sending

import com.fasterxml.jackson.databind.JsonNode
import io.juicebreak.slack.HTTP
import io.juicebreak.slack.JSON

object SlackAPI {
    private const val baseUrl = "https://slack.com/api"

    private val apiKey =
        System.getenv("JUICEBREAK_SLACK_API_KEY") ?: throw Exception("Could not find 'JUICEBREAK_SLACK_API_KEY'")

    fun callMethod(method: String, body: Any): JsonNode {
        val resp = HTTP.post("$baseUrl/${method}", apiKey, body)

        if (isOk(resp)) {
            return JSON.read(resp)
        } else {
            val error = JSON.readDynamic(resp)["error"].asText()
            throw SlackMethodException(error)
        }
    }

    private fun isOk(resp: String): Boolean {
        return JSON.readDynamic(resp)["ok"].asBoolean()
    }
}
