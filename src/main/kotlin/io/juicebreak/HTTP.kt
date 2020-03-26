package io.juicebreak

import com.github.kittinunf.fuel.core.Headers
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost

object HTTP {
    fun get(url: String): String {
        val (_, _, result) = url.httpGet().responseString()

        return result.get()
    }

    fun post(url: String, bearerToken: String, body: Any): String {
        val (_, _, result) = url.httpPost()
            .header(Headers.AUTHORIZATION, "Bearer $bearerToken")
            .header(Headers.CONTENT_TYPE, "application/json; charset=utf-8")
            .body(JSON.write(body))
            .responseString()
        return result.get()
    }
}
