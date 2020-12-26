package com.lau.logger.context

interface CalendarApi {
    fun currentTimeInMillis(): Long
}

internal class DefaultCalendar : CalendarApi {
    override fun currentTimeInMillis() = System.currentTimeMillis()
}