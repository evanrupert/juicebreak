package io.juicebreak.prompt

import io.juicebreak.superlatives

class Prompt {
    private val title: String
    private val description: String

    init {
        val (title, description) = prompts.random()
        this.title = title
        this.description = replaceWithRandomWords(description)
    }

    private fun replaceWithRandomWords(str: String): String =
        str.replace(Regex("\\[([^\\]]*)\\]")) { words[it.groupValues[1]]?.random() ?: "" }

    override fun toString(): String =
"""
Hey there! Juice Break here! Today’s fun challenge is: :drum_with_drumsticks: :drum_with_drumsticks: :drum_with_drumsticks:

> *$title*
> $description

To enter the challenge, please reply to this thread with *'@Juice Break'* and your submission!

To vote for superlatives, just react with any of the following emojis:

${ superlatives.map { "\t\t:${it.key}: for ${it.value}" }.joinToString("\n") }

Run */winners* to see who won!

Good luck! :four_leaf_clover:
"""

}
