package com.lau.logger.data

interface Loggable {
    val type: LogType
}

sealed class LogType {

    object Default : LogType()
    object Selenium : LogType()
    data class Error(val throwable: Throwable) : LogType()

    sealed class Test : LogType() {
        object Suite : Test()
        object TestCase : Test()
        object Assertion : Test()
    }

    sealed class Network : LogType() {
        object Web : Network()
        object Device : Network()
    }
}