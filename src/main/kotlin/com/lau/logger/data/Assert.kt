package com.lau.logger.data

import com.lau.logger.data.LogType.*

class Assert(
    val message: String,
    val line: String?,
    val severity: Int
): Loggable {
    override val type: LogType = ASSERTION
}
