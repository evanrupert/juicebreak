package io.juicebreak.prompt

import io.juicebreak.superlatives
import kotlin.random.Random;

class Prompt {
    private val title: String
    private val description: String

    init {
        val data = prompts[Random.nextInt(0, prompts.size)]
        title = data.title
        description = replaceTemplate(data.description)
    }

    private fun replaceTemplate(str: String): String =
        str
            .replace(Regex("\\[noun\\]")) { randomNoun() }
            .replace(Regex("\\[adjective\\]")) { randomAdjective() }
            .replace(Regex("\\[fighter\\]")) { randomFighter() }
            .replace(Regex("\\[power\\]")) { randomPower() }
            .replace(Regex("\\[buzzword\\]")) { randomBuzzword() }

    override fun toString(): String =
"""
Hey there! JuiceBreak Here! Today’s fun challenge is: :drum_with_drumsticks: :drum_with_drumsticks: :drum_with_drumsticks:

> *$title*
> $description

To enter the challenge, please reply to this thread with *@Juice Break* and your submission!

To vote for superlatives, just react with any of the following emojis:

${ superlatives.map { "\t\t:${it.key}: for ${it.value}" }.joinToString("\n") }

Run */winners* to see who won!

Good luck! :four_leaf_clover:
"""

}
