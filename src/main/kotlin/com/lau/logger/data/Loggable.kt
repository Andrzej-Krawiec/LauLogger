package com.lau.logger.data

interface Loggable {
    val type: LogType
}

enum class LogType {
    NETWORK_FULL,
    NETWORK_REQUEST,
    NETWORK_RESPONSE,
    SELENIUM_CLICK,
    SELENIUM_NAVIGATE,
    SELENIUM_INPUT,
    ASSERTION,
    SEPARATOR
}
