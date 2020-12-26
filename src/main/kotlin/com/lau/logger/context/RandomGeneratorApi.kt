package com.lau.logger.context

import java.util.*

interface RandomGeneratorApi {
    fun generateUUID(): UUID
}

internal class DefaultRandomGenerator : RandomGeneratorApi {
    override fun generateUUID(): UUID = UUID.randomUUID()
}
