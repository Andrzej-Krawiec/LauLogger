package com.lau.logger

import com.lau.logger.data.*
import com.lau.logger.observers.SystemLoggerObserver

fun main() {

    val logger = LauLogger()

    logger.relay.addObserver(SystemLoggerObserver())

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
            it as HttpRequestMessage,
            HttpResponseMessage(
                networkType = LogType.Network.Web,
                code = 200,
                message = "OK",
                url = it.url,
                duration = 6000
            )
        )
    }
}