package com.lau.logger.data

interface Loggable {
    val type: LogType?
}

sealed class LogType(private val name: String) {

    object Default : LogType("Default")
    object Selenium : LogType("Selenium")
    data class Error(val throwable: Throwable) : LogType("Error")

    sealed class Test(innerName: String) : LogType("Test - $innerName") {
        object Suite : Test("Suite")
        object TestCase : Test("TestCase")
        object Assertion : Test("Assertion")
    }

    sealed class Network(innerName: String) : LogType("Network - $innerName") {
        object Web : Network("Web")
        object Device : Network("Device")
    }

    override fun toString() = name
}
