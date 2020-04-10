package io.juicebreak

import io.juicebreak.prompt.Prompt
import io.juicebreak.slack.receiving.Event
import io.juicebreak.slack.receiving.SlackApp
import io.juicebreak.slack.sending.postMessage

fun main() {
    start()
}

fun start() {
    var currentChallenge: Challenge? = null
    var currentPrompt: Prompt

    SlackApp().apply {

        // Start new challenge
        on("/challenge") { _, channel ->
            currentPrompt = Prompt()

            val resp = postMessage(channel, currentPrompt.toString())
            val threadId = resp["message"]["ts"].asText()

            currentChallenge = Challenge(threadId)
        }

        // Make submission with mention
        on(Event.APP_MENTION) { data ->
            currentChallenge ?: return@on

            val threadId = data["thread_ts"].asText()
            if (!currentChallenge!!.isApplicableFor(threadId))
                return@on

            println("New Submission by: '${data["user"]}', with ts: '${data["ts"]}")
            currentChallenge?.newSubmission(data["ts"].asText(), data["user"].asText())
        }

        // Vote on submission with reaction
        on(Event.REACTION_ADDED) { data ->
            currentChallenge ?: return@on

            val submissionId = data["item"]["ts"].asText()
            val reaction = data["reaction"].asText()

            println("New vote '${reaction}' for: '${submissionId}'")
            currentChallenge?.addVote(submissionId, reaction)
        }

        // Rescind vote with reaction removal
        on(Event.REACTION_REMOVED) { data ->
            currentChallenge ?: return@on

            val submissionId = data["item"]["ts"].asText()
            val reaction = data["reaction"].asText()

            println("Vote '${reaction}' removed for '$submissionId'")
            currentChallenge?.removeVote(submissionId, reaction)
        }

        // Post results message to thread
        on("/winners") { _, channel ->
            currentChallenge?.let { postChallengeResults(channel, it) }
        }

        // Print data and force result message
        on("/debug") { _, channel ->
            currentChallenge?.let {
                it.printDebug()

                postChallengeResults(channel, it)
            }
        }

    }.start()

}

fun postChallengeResults(channel: String, challenge: Challenge) {
    val results = challenge.calculateResults()

    val resultsText =
        if (results.isEmpty())
            "No submissions :cry:"
        else
            results.map(::resultToDisplayString).joinToString("\n")

    postMessage(
        channel,
        resultsText,
        challenge.threadId
    )
}

fun resultToDisplayString(result: Map.Entry<String, List<String>>): String {
    val winners = result.value.joinToString(", ") { "<@$it>" }

    return ":${result.key}: ${superlatives[result.key]} = $winners"
}
