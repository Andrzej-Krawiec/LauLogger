package com.lau.logger.adapter

interface TypeAdapter<T, K> {
    fun map(t: T): K
}