package io.juicebreak.slack.receiving

import io.juicebreak.slack.JSON
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receiveText
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

class SlackApp {
    private val eventListeners: MutableMap<Event, suspend (args: InvocationArgs) -> Unit> = mutableMapOf()

    fun on(event: Event, callback: suspend (args: InvocationArgs) -> Unit) {
        println("Registering event listener for: ${event.code}")
        eventListeners[event] = callback
    }

    fun start() {
        embeddedServer(Netty, 8080) {
            routing {
                post("/") { handleEvent(call) }
            }
        }.start(wait = true)
    }

    private suspend fun handleEvent(call: ApplicationCall) {
        val rawBody = call.receiveText()

        if (isChallenge(rawBody)) {
            challengeAccepted(call, rawBody)
            return
        }

        val eventType = parseEventType(rawBody)

        println("Received event of type: '$eventType'")
        eventListeners[Event.from(eventType)]?.invoke(buildInvocationData(rawBody))

        call.respond(HttpStatusCode.OK)
    }

    private fun buildInvocationData(rawBody: String): InvocationArgs =
        InvocationArgs(JSON.read(rawBody), rawBody)

    private fun isChallenge(rawBody: String): Boolean =
        JSON.readDynamic(rawBody)["type"].asText() == "challenge"

    private suspend fun challengeAccepted(call: ApplicationCall, body: String) {
        val challenge = JSON.readDynamic(body)["challenge"].asText()
        call.respondText(challenge)
    }

    private fun parseEventType(body: String): String {
        val json = JSON.readDynamic(body)
        return json["event"]["type"].asText()
    }
}