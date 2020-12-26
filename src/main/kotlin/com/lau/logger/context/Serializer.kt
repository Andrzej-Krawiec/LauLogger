package com.lau.logger.context

interface Serializer {
    fun serialize(data: Any): String
}

internal class DefaultSerializer : Serializer {
    override fun serialize(data: Any) = data.toString()
}
