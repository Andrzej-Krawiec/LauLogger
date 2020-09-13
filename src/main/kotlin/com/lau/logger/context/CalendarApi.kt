package com.lau.logger.context

interface CalendarApi {
    fun currentTimeInMillis(): Long
}

class DefaultCalendar: CalendarApi {
    override fun currentTimeInMillis(): Long = System.currentTimeMillis()
}