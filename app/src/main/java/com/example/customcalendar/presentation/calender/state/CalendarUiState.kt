package com.example.customcalendar.presentation.calender.state

import java.time.LocalDate
import java.time.Month

data class CalendarUiState(
    val selectedYear: Int = LocalDate.now().year,
    val selectedMonth: Month = LocalDate.now().month,
    val daysInMonth: List<LocalDate?> = emptyList()
)
