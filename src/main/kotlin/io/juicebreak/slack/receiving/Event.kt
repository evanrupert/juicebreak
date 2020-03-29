package io.juicebreak.slack.receiving

enum class Event(val code: String) {
    REACTION_ADDED("reaction_added"),
    REACTION_REMOVED("reaction_removed"),
    APP_MENTION("app_mention");

    companion object {
        fun from(str: String): Event {
            return when (str) {
                REACTION_ADDED.code -> REACTION_ADDED
                REACTION_REMOVED.code -> REACTION_REMOVED
                APP_MENTION.code -> APP_MENTION
                else -> throw Exception("Does not support event: '$str'")
            }
        }
    }
}

