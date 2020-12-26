package com.lau.logger

import com.lau.logger.data.HttpMessage
import com.lau.logger.data.HttpMethod
import com.lau.logger.data.HttpRequestMessage
import com.lau.logger.data.HttpResponseMessage
import com.lau.logger.observers.JsonFileLoggerObserver
import com.lau.logger.observers.SystemLoggerObserver
import java.io.File

fun main() {

    val logger = LauLogger {
        observers += SystemLoggerObserver()
        observers += JsonFileLoggerObserver(File("testaaa.json"))
    }

    val requestUUid = logger.addLog(
        HttpRequestMessage(
            method = HttpMethod.POST,
            url = "https://google.com"
        )
    )

    Thread.sleep(5_000)

    logger.update(requestUUid) {
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
