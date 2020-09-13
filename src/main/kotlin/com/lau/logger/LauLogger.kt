package com.lau.logger

import com.lau.logger.context.Context

class LauLogger {

    val context = Context()

    val relay = LogRelay(
        context.randomGeneratorApi,
        context.calendarApi
    )

}