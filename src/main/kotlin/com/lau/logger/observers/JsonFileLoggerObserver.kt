package com.lau.logger.observers

import com.lau.logger.LogObserver
import com.lau.logger.PreparedLog
import com.lau.logger.context.Context
import com.lau.logger.observers.JsonFilePolicy.*
import java.io.File
import java.io.FileNotFoundException
import java.util.*

class JsonFileLoggerObserver(
    private val file: File,
    private val filePolicy: JsonFilePolicy = ALWAYS_CREATE
) : LogObserver {

    private lateinit var context: Context
    private val result = mutableMapOf<UUID, PreparedLog>()

    init {
        if (filePolicy == ALWAYS_CREATE) {
            file.delete()
            file.parentFile?.mkdirs()
            file.createNewFile()
        }
    }

    override fun update(uuid: UUID, data: PreparedLog) {
        result[uuid] = data
        update()
    }

    override fun update(data: Map<UUID, PreparedLog>) {
        result.clear()
        result.putAll(data)
        update()
    }

    override fun provideContext(context: Context) {
        this.context = context
    }

    private fun update() {
        checkFile()
        file.writeText(context.serializer.serialize(result))
    }

    private fun checkFile() {
        if (file.exists())
            return

        when (filePolicy) {
            THROW_IF_NOT_EXIST -> throw FileNotFoundException("File: ${file.absolutePath} not found")
            CREATE_IF_NEEDED, ALWAYS_CREATE -> {
                file.parentFile?.mkdirs()
                file.createNewFile()
            }
        }
    }
}

enum class JsonFilePolicy {
    THROW_IF_NOT_EXIST,
    CREATE_IF_NEEDED,
    ALWAYS_CREATE
}

