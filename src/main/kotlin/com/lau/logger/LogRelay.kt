package com.lau.logger

import com.lau.logger.context.CalendarApi
import com.lau.logger.context.RandomGeneratorApi
import com.lau.logger.data.Loggable
import java.util.*

class LogRelay(
    private val randomGeneratorApi: RandomGeneratorApi,
    private val calendarApi: CalendarApi
) {

    private val relay = mutableMapOf<UUID, PreparedLog>()
    private val observers = mutableListOf<LogObserver>()

    @Synchronized fun update(uuid: UUID, action: (Loggable) -> Loggable) {
        relay[uuid]
            ?.let { storedLog ->
                val updatedLog = storedLog.copy(loggable = action.invoke(storedLog.loggable))
                relay[uuid] = updatedLog
                observers.forEach { it.update(uuid, updatedLog) }
            }
    }

    @Synchronized fun addLog(loggable: Loggable): UUID {
        val uuid = randomGeneratorApi.generateUUID()
        val preparedLog = PreparedLog(
            time = calendarApi.currentTimeInMillis(),
            loggable = loggable
        )
        relay[uuid] = preparedLog
        observers.forEach { it.update(uuid, preparedLog) }
        return uuid
    }

    fun addObserver(observer: LogObserver, populate: Boolean = true) {
        observers.add(observer)
        if (populate) {
            observer.update(relay)
        }
    }

    @Synchronized fun clear() {
        relay.clear()
        observers.clear()
    }
}

data class PreparedLog(
    val time: Long,
    val loggable: Loggable
)

interface LogObserver {
    fun update(uuid: UUID, data: PreparedLog)
    fun update(data: Map<UUID, PreparedLog>)
}
