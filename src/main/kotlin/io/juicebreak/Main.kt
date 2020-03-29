package io.juicebreak

import io.juicebreak.slack.receiving.Event
import io.juicebreak.slack.receiving.SlackApp
import io.juicebreak.slack.sending.postMessage

fun main() {
    val app = SlackApp()

    app.on(Event.REACTION_ADDED) { data ->
        val channel = data["event"]["item"]["channel"].asText()
        postMessage(channel, "Thanks for reaction")
    }

    app.on(Event.REACTION_REMOVED) { data ->
        val channel = data["event"]["item"]["channel"].asText()
        postMessage(channel, "react4ion removed")
    }

    app.on(Event.APP_MENTION) { data ->
        val channel = data["event"]["channel"].asText()
        postMessage(channel, "app menti n")
    }

    app.on(Event.MESSAGE) { data ->
        val channel = data["event"]["channel"].asText()
        postMessage(channel, "nice message")
    }

    app.start()
}
