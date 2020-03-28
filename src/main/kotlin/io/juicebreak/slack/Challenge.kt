package io.juicebreak.slack

import io.juicebreak.JSON

fun isChallenge(rawBody: String): Boolean =
    JSON.read<TypedResponse>(rawBody).type == "challenge"

data class Challenge(
    val token: String,
    val challenge: String,
    val type: String
)
