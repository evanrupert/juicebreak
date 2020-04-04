package io.juicebreak.prompt

data class PromptData(
    val title: String,
    val description: String
)

val prompts: List<PromptData> = listOf(
    PromptData(
        title = "Mundane Memery!",
        description = "Take a picture of a random item in your immediate surroundings and give it a funny caption. The best photo/caption combos will be deemed the winner!"
    ),
    PromptData(
        title = "The Super Cool Drawing Contest!",
        description = """
            Given the following two random words, post your best five-minute drawing! Feel free to get creative with the details!

            > *Draw a[adjective] [noun]*
        """.trimIndent()
    ),
    PromptData(
        title = "Face Off!",
        description = """
            Given the following matchup, explain your best two-sentence pitch of why you think your character would win! The more creative, the better!

            > *Who would win: [fighter] [power] or [fighter] [power]?*
        """.trimIndent()
    ),
    PromptData(
        title = "Three-Word Startup!",
        description = """
            Using the following three random words, write a two-sentence pitch about a made-up company! The crazier the idea, the better!

            > *Your three words are: '[buzzword]', '[buzzword]', and '[buzzword]'*
        """.trimIndent()
    ),
    PromptData(
        title = "Dumb Debates!",
        description = """
            Given the following debate topic, explain your reasoning for the side you choose!

            > *[debate]*
        """.trimIndent()
    ),
    PromptData(
        title = "Creative Capitalism!",
        description = """
            Given the following crazy situation, write a two-sentence response about how you would make super profits! The crazier the idea, the better!

            > *[event]*
        """.trimIndent()
    )
)
