package com.lau.logger

import com.lau.logger.data.*
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
            networkType = LogType.Network.Web,
            method = HttpMethod.POST,
            url = "https://google.com"
        )
    )

    Thread.sleep(5_000)

    logger.relay.update(requestUUid) {
        HttpMessage(
            networkType = LogType.Network.Web,
            request = it as HttpRequestMessage,
            response = HttpResponseMessage(
                networkType = LogType.Network.Web,
                code = 200,
                message = "OK",
                url = it.url,
                duration = 6000
            )
        )
    }
}
