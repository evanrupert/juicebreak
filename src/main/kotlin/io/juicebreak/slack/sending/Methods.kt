package io.juicebreak.slack.sending

import com.fasterxml.jackson.databind.JsonNode

fun postMessage(channel: String, text: String): JsonNode {
    return SlackAPI.callMethod(
        "chat.postMessage",
        mapOf(
            "channel" to channel,
            "text" to text
        )
    )
}
