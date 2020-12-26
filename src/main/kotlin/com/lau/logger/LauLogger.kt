package com.lau.logger

import com.lau.logger.context.Context
import com.lau.logger.data.Loggable
import java.util.*

class LauLogger {

    val context = Context()

    val relay = LogRelay(
        context.randomGeneratorApi,
        context.calendarApi
    )

    fun update(uuid: UUID, action: (Loggable) -> Loggable) =
        relay.update(uuid, action)

    fun addLog(loggable: Loggable): UUID =
        relay.addLog(loggable)
}
