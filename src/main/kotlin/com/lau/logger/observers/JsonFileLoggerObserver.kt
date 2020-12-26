package com.lau.logger.observers

import com.lau.logger.LogObserver
import com.lau.logger.PreparedLog
import com.lau.logger.context.Context
import java.io.File
import java.util.*

    class JsonFileLoggerObserver(
    private val context: Context,
    private val file: File
) : LogObserver {

    private val result = mutableMapOf<UUID, PreparedLog>()

    override fun update(uuid: UUID, data: PreparedLog) {
        result[uuid] = data
        update()
    }

    override fun update(data: Map<UUID, PreparedLog>) {
        result.clear()
        result.putAll(data)
        update()
    }

    private fun update() {
        file.writeText(context.serializer.serialize(result))
    }
}
