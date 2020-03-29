package io.juicebreak.slack.sending

class SlackMethodException(message: String)
    : Exception("Slack method error: '$message'")
