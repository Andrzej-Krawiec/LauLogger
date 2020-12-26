package com.lau.logger.data

typealias LogType = Int

interface Loggable {
    val type: LogType

    companion object {
        const val SEPARATOR = 0
        const val NETWORK_FULL = 1
        const val NETWORK_REQUEST = 2
        const val NETWORK_RESPONSE = 3
        const val SELENIUM_CLICK = 4
        const val SELENIUM_NAVIGATE = 5
        const val SELENIUM_INPUT = 6
        const val ASSERTION = 7
    }
}
