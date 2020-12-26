package com.lau.logger.data

import com.lau.logger.data.LogType.*

sealed class SeleniumEvent(override val type: LogType) : Loggable {

    data class Click(
        val name: String? = null,
        val url: String,
        val innerHtml: String,
        val lineNumber: Int? = null,
        val xPath: String? = null,
        val screenshot: String? = null,
        val error: Throwable? = null
    ) : SeleniumEvent(SELENIUM_CLICK)

    data class Navigate(
        val name: String? = null,
        val fromUrl: String?,
        val toUrl: String,
        val lineNumber: Int? = null,
        val error: Throwable? = null
    ): SeleniumEvent(SELENIUM_NAVIGATE)

    data class Input(
        val name: String? = null,
        val url: String,
        val innerHtml: String,
        val data: String,
        val xPath: String? = null,
        val lineNumber: Int? = null,
        val screenshot: String? = null,
        val error: Throwable? = null
    ): SeleniumEvent(SELENIUM_INPUT)
}
