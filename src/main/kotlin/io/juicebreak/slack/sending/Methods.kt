package io.juicebreak.slack.sending

import com.fasterxml.jackson.databind.JsonNode
import io.juicebreak.ThreadId

fun postMessage(channel: String, text: String, replyTo: ThreadId? = null): JsonNode {
    val args = listOfNotNull(
        "channel" to channel,
        "text" to text,
        replyTo?.let { "thread_ts" to it }
    ).toMap()

    return SlackAPI.callMethod("chat.postMessage", args)
}
