package com.example.customcalendar.presentation.calender.event

sealed class CalendarEvent {
    data object PreviousMonth : CalendarEvent()
    data object NextMonth : CalendarEvent()
    data class AddTask(val title: String, val description: String) : CalendarEvent()
}
