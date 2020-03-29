package io.juicebreak.slack.receiving

enum class Event(val type: String) {
    REACTION_ADDED("reaction_added"),
    REACTION_REMOVED("reaction_removed"),
    APP_MENTION("app_mention"),
    MESSAGE("message");

    companion object {
        fun from(typeString: String): Event {
            return when (typeString) {
                REACTION_ADDED.type -> REACTION_ADDED
                REACTION_REMOVED.type -> REACTION_REMOVED
                APP_MENTION.type -> APP_MENTION
                MESSAGE.type -> MESSAGE
                else -> throw Exception("Does not support event type: '$typeString'")
            }
        }
    }
}
