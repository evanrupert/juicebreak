package io.juicebreak.slack

import io.juicebreak.HTTP
import io.juicebreak.JSON

class SlackException(message: String)
    : Exception("Slack method error: '$message'")

object SlackHTTP {
    data class SlackResponse(
        val ok: Boolean
    )

    data class ErrorResponse(
        val error: String
    )

    const val baseUrl = "https://slack.com/api"

    val apiKey = System.getenv("JUICEBREAK_SLACK_API_KEY") ?:
        throw Exception("Could not find 'JUICEBREAK_SLACK_API_KEY'")

    inline fun <reified T : Any > call(method: String, body: Any): T {
        val resp = HTTP.post("${baseUrl}/${method}", apiKey, body)

        if (isOk(resp)) {
            return JSON.read(resp)
        } else {
            val (error) = JSON.read<ErrorResponse>(resp)
            throw SlackException(error)
        }
    }

    fun isOk(resp: String): Boolean {
        return JSON.read<SlackResponse>(resp).ok
    }
}
