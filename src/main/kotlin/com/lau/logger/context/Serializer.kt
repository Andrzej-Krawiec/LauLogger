package com.lau.logger.context

interface Serializer {
    fun serialize(data: Any): String
}

class DefaultSerializer : Serializer {

    override fun serialize(data: Any): String =
        data.toString()
}
