package io.juicebreak.prompt

import kotlin.random.Random

fun randomNoun(): String =
    nouns[Random.nextInt(0, nouns.size)]

fun randomAdjective(): String =
    adjectives[Random.nextInt(0, adjectives.size)]

val nouns: List<String> = listOf(
    "squirrel",
    "bunny",
    "farmer",
    "umbrella",
    "train",
    "mayor",
    "alpaca",
    "lumberjack",
    "engineer",
    "bathtub",
    "bear",
    "anteater",
    "turtle",
    "lizard",
    "geco",
    "taco",
    "mummy",
    "werewolf",
    "cloud",
    "puppy",
    "dog",
    "cat",
    "kitten",
    "snake",
    "chicken",
    "turkey",
    "flower",
    "dinosaur",
    "alligator",
    "spider",
    "goose",
    "duck",
    "antelope",
    "kangaroo",
    "cookie",
    "cheese",
    "toaster",
    "sandwich",
    "coffee pot",
    "lamp",
    "deer",
    "dragon",
    "pangolin",
    "ninja",
    "pirate",
    "police officer",
    "tiger",
    "lion",
    "car",
    "bicycle",
    "iguana",
    "laptop",
    "book",
    "lightbulb",
    "refrigerator",
    "microwave",
    "chameleon",
    "toothbrush",
    "floss",
    "elf",
    "dwarf",
    "knight",
    "wizard",
    "broccoli",
    "orange",
    "muffin",
    "bee",
    "donut",
    "cow",
    "dolphin",
    "pig",
    "owl",
    "dog",
    "elephant",
    "tree",
    "crab",
    "apple",
    "Walrus",
    "Meerkat",
    "Waffle",
    "Car",
    "Bicycle",
    "Clown",
    "Shark"
)

val adjectives: List<String> = listOf(
    " bearded",
    " beautiful",
    " bossy",
    " brave",
    " burly",
    " charming",
    " chill",
    " clever",
    " confident",
    " consoling",
    " courageous",
    " cowardly",
    " crabby",
    " crazy",
    " creative",
    " creepy",
    " curly",
    " cute",
    " devious",
    " diplomatic",
    " disreputable",
    " dreamy",
    " fearful",
    " friendly",
    " frozen",
    " funny",
    " greedy",
    " grumpy",
    " hard-working",
    " lazy",
    " magical",
    " mischevious",
    " misunderstood",
    " mysterious",
    " nervous",
    " nosey",
    " philosophical",
    " rebellious",
    " relaxed",
    " respectful",
    " self-disciplined",
    " shiny",
    " sinister",
    " sleepy",
    " smart",
    " sneaky",
    " social",
    " sophisticated",
    " spooky",
    " studious",
    " supersticious",
    " surprised",
    " sweet",
    " tough",
    " vain",
    " wise",
    "n absent-minded",
    "n ambitious",
    "n angry",
    "n angry",
    "n anxious",
    "n approachable",
    "n embarassed",
    "n enthusiastic",
    "n exceptional",
    "n impractical",
    "n indiscrete",
    "n itchy",
    "n objectionable",
    "n ugly"
)