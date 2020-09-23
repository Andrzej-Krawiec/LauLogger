package com.lau.logger.observers

import com.lau.logger.LogObserver
import com.lau.logger.PreparedLog
import com.lau.logger.context.Context
import com.lau.logger.data.HttpMessage
import com.lau.logger.data.HttpRequestMessage
import com.lau.logger.data.HttpResponseMessage
import java.util.*

class SystemLoggerObserver(
    private val context: Context
) : LogObserver {

    override fun update(uuid: UUID, data: PreparedLog) {
        when(val loggable = data.loggable) {
            is HttpRequestMessage -> update(loggable)
            is HttpMessage -> update(loggable.response)
        }
    }

    override fun update(data: Map<UUID, PreparedLog>) {
        // Not Supported
    }

    private fun update(data: HttpRequestMessage) {
        log("---> ${data.method} ${data.url}")
        data.headers
            ?.forEach { key, value -> log("$INDENT$key : $value") }
        data.body
            ?.let { context.serializer.serialize(it) }
    }

    private fun update(data: HttpResponseMessage) {
        log("<--- ${data.code} ${data.message} ${data.url}")
        log("Duration: ${data.duration}")
        data.headers
            ?.forEach { key, value -> log("$INDENT$key : $value") }
        data.body
            ?.let { context.serializer.serialize(it) }
    }

    private fun log(message: String) {
        println(message)
    }

    companion object {
        private const val INDENT = "    "
    }
}
