package io.juicebreak.slack.receiving

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonNode

data class InvocationArgs(
    val dispatch: EventDispatch,
    val rawBody: String
)

data class EventDispatch(
    val token: String,
    @JsonProperty("team_id")
    val teamId: String,
    @JsonProperty("api_app_id")
    val apiAppId: String,
    val event: JsonNode,
    val type: String,
    @JsonProperty("event_id")
    val eventId: String,
    @JsonProperty("event_time")
    val eventTime: Long,
    @JsonProperty("authed_users")
    val authedUsers: List<String>
)
