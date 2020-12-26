package com.lau.logger

import com.lau.logger.context.*
import com.lau.logger.data.Loggable
import java.util.*

class LauLoggerConfig {
    var serializer: Serializer = DefaultSerializer()
    var randomGeneratorApi: RandomGeneratorApi = DefaultRandomGenerator()
    var calendarApi: CalendarApi = DefaultCalendar()

    val observers = mutableListOf<LogObserver>()
}

class LauLogger(configBuilder: LauLoggerConfig.() -> Unit = { }) {

    private val context: Context
    private val relay: LogRelay

    init {
        val config = LauLoggerConfig().apply(configBuilder)

        context = Context(
            serializer = config.serializer,
            randomGeneratorApi = config.randomGeneratorApi,
            calendarApi = config.calendarApi
        )
        relay = LogRelay(
            context.randomGeneratorApi,
            context.calendarApi
        )
        config.observers.forEach { addObserver(it, false) }
    }

    fun update(uuid: UUID, action: (Loggable) -> Loggable) =
        relay.update(uuid, action)

    fun addLog(loggable: Loggable): UUID =
        relay.addLog(loggable)

    fun addObserver(observer: LogObserver, populate: Boolean = true) =
        relay.addObserver(observer.apply { provideContext(context) }, populate)
}
