package com.lau.logger.data

import com.lau.logger.data.Loggable.Companion.NETWORK_FULL
import com.lau.logger.data.Loggable.Companion.NETWORK_REQUEST
import com.lau.logger.data.Loggable.Companion.NETWORK_RESPONSE

data class HttpMessage(
    var request: HttpRequestMessage,
    var response: HttpResponseMessage?
) : Loggable {
    override val type = NETWORK_FULL
}

data class HttpRequestMessage(
    val method: HttpMethod,
    val url: String,
    val headers: Map<String, String>? = null,
    val body: Any? = null
) : Loggable {
    override val type = NETWORK_REQUEST
}

data class HttpResponseMessage(
    val code: Int,
    val message: String,
    val url: String,
    val duration: Long,
    val headers: Map<String, String>? = null,
    val body: Any? = null
) : Loggable {
    override val type = NETWORK_RESPONSE
}

enum class HttpMethod {
    GET,
    POST,
    PUT,
    DELETE
}
