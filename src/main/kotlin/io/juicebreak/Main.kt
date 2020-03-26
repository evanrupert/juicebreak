package io.juicebreak

import io.juicebreak.slack.SlackApp

fun main() {
    val app = SlackApp()
    val resp = app.postMessage("test", "Heck ye")
    println(resp)
}
