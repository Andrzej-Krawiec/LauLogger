package com.lau.logger.data

import java.io.Serializable

data class HttpMessage(
    private val networkType: LogType.Network,
    val request: HttpRequestMessage,
    val response: HttpResponseMessage
) : Loggable {
    override val type = networkType
}

data class HttpRequestMessage(
    private val networkType: LogType.Network,
    val method: HttpMethod,
    val url: String,
    val headers: Map<String, String>? = null,
    val body: Serializable? = null
) : Loggable {
    override val type = networkType
}

data class HttpResponseMessage(
    private val networkType: LogType.Network,
    val code: Int,
    val message: String,
    val url: String,
    val duration: Long,
    val headers: Map<String, String>? = null,
    val body: Serializable? = null
) : Loggable {
    override val type = networkType
}

enum class HttpMethod {
    GET,
    POST,
    PUT,
    DELETE
}