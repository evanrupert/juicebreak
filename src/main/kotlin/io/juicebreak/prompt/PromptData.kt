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

            > *Who would win: [fighter1] [power1] or [fighter2] [power2]?*
        """.trimIndent()
    )
)
