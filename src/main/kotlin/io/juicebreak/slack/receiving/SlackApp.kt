package io.juicebreak.slack.receiving

import com.fasterxml.jackson.databind.JsonNode
import io.juicebreak.slack.JSON
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.http.parseUrlEncodedParameters
import io.ktor.request.receiveText
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

class SlackApp {
    private val eventListeners: MutableMap<Event, suspend (data: JsonNode) -> Unit> = mutableMapOf()
    private val commandListeners: MutableMap<String, suspend (text: String, channel: String) -> Unit> = mutableMapOf()

    fun on(event: Event, callback: suspend (data: JsonNode) -> Unit) {
        println("Registering event listener for: ${event.type}")
        eventListeners[event] = callback
    }

    fun on(command: String, callback: suspend (text: String, channel: String) -> Unit) {
        println("Registering command listener for: $command")
        commandListeners[command] = callback
    }

    fun start() {
        embeddedServer(Netty, 8080) {
            routing {
                post("/") { handleEvent(call) }
                post("/commands") { handleCommand(call) }
            }
        }.start(wait = true)
    }

    private suspend fun handleEvent(call: ApplicationCall) {
        val json = JSON.readDynamic(call.receiveText())

        if (isChallenge(json)) {
            challengeAccepted(call, json)
            return
        }

        val event = parseEventType(json)

        if (!isBot(json)) {
            println("Received event of type: '${event.type}'")
            eventListeners[event]?.invoke(json["event"])
        }

        call.respond(HttpStatusCode.OK)
    }

    private suspend fun handleCommand(call: ApplicationCall) {
        val params = call.receiveText().parseUrlEncodedParameters()

        val commandType = params["command"]!!
        val text = params["text"]!!
        val channel = params["channel_id"]!!

        println("Received command of type: '$commandType'")
        commandListeners[commandType]?.invoke(text, channel)

        call.respond(HttpStatusCode.OK)
    }

    private fun isChallenge(json: JsonNode): Boolean =
        json["type"].asText() == "url_verification"

    private suspend fun challengeAccepted(call: ApplicationCall, json: JsonNode) {
        val challenge = json["challenge"].asText()
        call.respondText(challenge)
    }

    private fun parseEventType(json: JsonNode): Event =
        Event.from(json["event"]["type"].asText())

    private fun isBot(json: JsonNode): Boolean = json["event"].has("bot_id")
}
