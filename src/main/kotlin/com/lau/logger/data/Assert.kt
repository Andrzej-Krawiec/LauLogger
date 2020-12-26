package com.lau.logger.data

import com.lau.logger.data.Loggable.Companion.ASSERTION

class Assert(
    val message: String,
    val line: String?,
    val severity: Int
) : Loggable {
    override val type = ASSERTION
}
