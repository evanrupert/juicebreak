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
    )
)
