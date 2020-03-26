package io.juicebreak.slack

class SlackApp {

    fun postMessage(channel: String, text: String): PostMessageResp {
        return SlackHTTP.call("chat.postMessage", mapOf(
            "channel" to channel,
            "text" to text
        ))
    }
}
