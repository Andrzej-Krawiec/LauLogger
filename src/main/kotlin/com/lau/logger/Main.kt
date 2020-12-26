package com.lau.logger

import com.lau.logger.data.HttpMessage
import com.lau.logger.data.HttpMethod
import com.lau.logger.data.HttpRequestMessage
import com.lau.logger.data.HttpResponseMessage
import com.lau.logger.observers.JsonFileLoggerObserver
import com.lau.logger.observers.SystemLoggerObserver
import java.io.File

fun main() {

    val logger = LauLogger()

    logger.relay.addObserver(SystemLoggerObserver(logger.context))
    val file = File("testaaa.json")
    file.createNewFile()
    logger.relay.addObserver(JsonFileLoggerObserver(logger.context, file))

    val requestUUid = logger.relay.addLog(
        HttpRequestMessage(
            method = HttpMethod.POST,
            url = "https://google.com"
        )
    )

    Thread.sleep(5_000)

    logger.relay.update(requestUUid) {
        HttpMessage(
            request = it as HttpRequestMessage,
            response = HttpResponseMessage(
                code = 200,
                message = "OK",
                url = it.url,
                duration = 6000
            )
        )
    }
}
