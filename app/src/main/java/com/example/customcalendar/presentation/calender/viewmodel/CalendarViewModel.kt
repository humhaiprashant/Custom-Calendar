package com.example.customcalendar.presentation.calender.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.customcalendar.constants.Constants
import com.example.customcalendar.data.model.TaskDetail
import com.example.customcalendar.data.repository.TaskRepository
import com.example.customcalendar.data.request.StoreTaskRequest
import com.example.customcalendar.presentation.calender.event.CalendarEvent
import com.example.customcalendar.presentation.calender.state.CalendarUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.Month
import java.time.Year
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CalendarUiState())
    val uiState: StateFlow<CalendarUiState> = _uiState

    init {
        generateMonth()
    }

    fun onEvent(event: CalendarEvent) {
        when (event) {
            is CalendarEvent.PreviousMonth -> {
                val prevMonth = _uiState.value.selectedMonth.minus(1)
                val newYear = if (prevMonth.value == 12) {
                    _uiState.value.selectedYear - 1
                } else {
                    _uiState.value.selectedYear
                }
                generateMonth(
                    selectedYear = newYear,
                    selectedMonth = prevMonth
                )
            }

            is CalendarEvent.NextMonth -> {
                val nextMonth = _uiState.value.selectedMonth.plus(1)
                val newYear = if (nextMonth.value == 1) {
                    _uiState.value.selectedYear + 1
                } else {
                    _uiState.value.selectedYear
                }
                generateMonth(
                    selectedMonth = nextMonth,
                    selectedYear = newYear
                )
            }

            is CalendarEvent.AddTask -> {
                viewModelScope.launch {
                    repository.storeTask(
                        StoreTaskRequest(
                            userId = Constants.USER_ID,
                            task = TaskDetail(title = event.title, description = event.description)
                        )
                    )
                }
            }
        }
    }

    private fun generateMonth(
        selectedYear: Int? = null,
        selectedMonth: Month? = null
    ) {
        val year = selectedYear ?: _uiState.value.selectedYear
        val month = selectedMonth ?: _uiState.value.selectedMonth

        val firstDay = LocalDate.of(year, month, 1)
        val daysInMonth = month.length(Year.isLeap(year.toLong()))

        val days = mutableListOf<LocalDate?>()

        // Add empty days for padding
        repeat(firstDay.dayOfWeek.value % 7) {
            days.add(null)
        }

        for (day in 1..daysInMonth) {
            days.add(LocalDate.of(year, month, day))
        }

        _uiState.update {
            it.copy(
                selectedYear = year,
                daysInMonth = days,
                selectedMonth = month
            )
        }
    }
}
