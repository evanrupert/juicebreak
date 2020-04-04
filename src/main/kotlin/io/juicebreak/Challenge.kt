package io.juicebreak

typealias ThreadId = String
typealias SubmissionId = String
typealias UserId = String
typealias Reaction = String
typealias ReactionCount = MutableMap<String, Int>

class Challenge(val threadId: ThreadId) {
    private val submissions: MutableMap<SubmissionId, ReactionCount> = mutableMapOf()
    private val submissionUsers: MutableMap<SubmissionId, UserId> = mutableMapOf()

    fun newSubmission(id: SubmissionId, userId: UserId) {
        submissions[id] = mutableMapOf()
        submissionUsers[id] = userId
    }

    fun addVote(id: SubmissionId, reaction: Reaction) {
        val reactions = submissions[id] ?: return
        reactions[reaction] = reactions.getOrDefault(reaction, 0) + 1
    }

    fun removeVote(id: SubmissionId, reaction: Reaction) {
        val reactions = submissions[id] ?: return

        reactions[reaction] = reactions[reaction]!! - 1
    }

    fun isApplicableFor(threadId: ThreadId): Boolean =
        this.threadId == threadId

    fun calculateResults(): Map<Reaction, List<UserId>> {
        val results: MutableMap<Reaction, List<UserId>> = mutableMapOf()

        for (reaction in superlatives.keys) {
            val userReactionValues = submissions.map { (ts, reactionMap) -> ts to (reactionMap[reaction] ?: 0) }
            val maxValue =
                userReactionValues
                    .maxBy { it.second }
                    ?.takeIf { it.second > 0 }
                    ?.second ?: continue

            val winningSubmissions = userReactionValues.filter { it.second == maxValue }.map { it.first }
            val winners = winningSubmissions.map { submissionUsers.getValue(it) }

            results[reaction] = winners
        }

        return results
    }

    fun printDebug() {
        println("\n====Challenge Debug====\n")
        println("Thread: $threadId\n")
        println(submissions)
        println()
        println(submissionUsers)
        println()
    }
}
