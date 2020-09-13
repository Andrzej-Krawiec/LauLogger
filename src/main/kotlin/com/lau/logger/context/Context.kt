package com.lau.logger.context

class Context {
    var serializer: Serializer = DefaultSerializer()
    var randomGeneratorApi: RandomGeneratorApi = DefaultRandomGenerator()
    var calendarApi: CalendarApi = DefaultCalendar()
}