package io.juicebreak

import io.juicebreak.slack.receiving.Event
import io.juicebreak.slack.receiving.SlackApp
import io.juicebreak.slack.sending.postMessage

fun main() {
    val app = SlackApp()

    app.on(Event.APP_MENTION) { (data, _) ->
        val channel = data.event["channel"].asText()
        val user = data.event["user"].asText()
        postMessage(
            channel,
            "<@$user> Why did you call me out bro... FIGHT ME!!!!!"
        )
    }

    app.on(Event.REACTION_ADDED) { (data, _) ->
        val user = data.event["user"].asText()
        val channel = data.event["item"]["channel"].asText()
        postMessage(
            channel,
            "<@$user> don't just add a reaction, MESSAGE ME LIKE A MAN!!!!"
        )
    }

    app.on(Event.REACTION_REMOVED) { (data, _) ->
        val user = data.event["user"].asText()
        val channel = data.event["item"]["channel"].asText()
        postMessage(
            channel,
            "<@$user> OH NO YOU DIDN'T JUST REMOVE THAT REACTION!!!!!!!"
        )
    }

    app.start()
}
