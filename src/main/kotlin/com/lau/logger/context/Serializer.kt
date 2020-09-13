package com.lau.logger.context

import com.google.gson.Gson
import java.io.Serializable

interface Serializer {
    fun serialize(data: Serializable): String
}

class DefaultSerializer : Serializer {

    override fun serialize(data: Serializable): String =
        data.toString()
}

class GsonSerializer(private val gson: Gson): Serializer {

    override fun serialize(data: Serializable): String =
        gson.toJson(data)

}