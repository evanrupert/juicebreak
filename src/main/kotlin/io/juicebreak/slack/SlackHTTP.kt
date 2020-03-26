package io.juicebreak.slack

import com.fasterxml.jackson.databind.JsonMappingException
import io.juicebreak.HTTP
import io.juicebreak.JSON

object SlackHTTP {
    data class ErrorResponse (
        val ok: Boolean,
        val error: String
    )

    const val baseUrl = "https://slack.com/api"

    val apiKey = System.getenv("JUICEBREAK_SLACK_API_KEY") ?:
        throw Exception("Could not find 'JUICEBREAK_SLACK_API_KEY'")

    inline fun <reified T : Any > call(method: String, body: Any): T {
        val resp = HTTP.post("${baseUrl}/${method}", apiKey, body)

        try {
            return JSON.read(resp)
        } catch (e: JsonMappingException) {
            try {
                val errResp = JSON.read<ErrorResponse>(resp)
                if (errResp.ok)
                    throw e
                else
                    throw Exception("Slack method error: '${errResp.error}'")
            } catch (e2: JsonMappingException) {
                throw e2
            }
        }
    }
}
