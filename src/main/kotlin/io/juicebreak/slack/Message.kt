package io.juicebreak.slack

import com.fasterxml.jackson.annotation.JsonProperty

data class PostMessageResp(
    val channel: String,
    val ts: String,
    val message: Message
)

data class Message(
    @JsonProperty("bot_id")
    val botId: String,
    val type: String,
    val text: String,
    val user: String,
    val ts: String,
    val team: String,
    @JsonProperty("bot_profile")
    val botProfile: BotProfile
)

data class BotProfile(
    val id: String,
    val deleted: Boolean,
    val name: String,
    val updated: Long,
    @JsonProperty("app_id")
    val appId: String,
    val icons: Map<String, String>,
    @JsonProperty("team_id")
    val teamId: String
)
