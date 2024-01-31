package dev.aaa1115910.bv.util

import io.github.oshai.kotlinlogging.KotlinLogging
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

object NetworkUtil {
    private lateinit var client: HttpClient
    private const val LOC_CHECK_URL = "https://www.cloudflare.com/cdn-cgi/trace"
    private val logger = KotlinLogging.logger { }
    var networkCheckResult: Map<String, String> = emptyMap()

    init {
        createClient()
    }

    private fun createClient() {
        client = HttpClient(OkHttp) {
            install(HttpRequestRetry) {
                retryOnException(maxRetries = 3)
            }
        }
    }

    suspend fun isMainlandChina(): Boolean {
        return true
    }
}
