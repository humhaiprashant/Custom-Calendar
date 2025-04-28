package com.example.customcalendar.presentation.calender.viewmodel

import com.example.customcalendar.data.repository.TaskRepository
import com.example.customcalendar.presentation.calender.event.CalendarEvent
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import java.time.LocalDate

@ExperimentalCoroutinesApi
class CalendarViewModelTest {

    private lateinit var viewModel: CalendarViewModel

    @MockK
    private lateinit var repository: TaskRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = CalendarViewModel(repository)
    }

    @Test
    fun `test initial state year and month are current`() {
        val state = viewModel.uiState.value
        assertEquals(LocalDate.now().year, state.selectedYear)
        assertEquals(LocalDate.now().month, state.selectedMonth)
    }

    @Test
    fun `test switching to previous month`() {
        val initialMonth = viewModel.uiState.value.selectedMonth
        viewModel.onEvent(CalendarEvent.PreviousMonth)
        val newMonth = viewModel.uiState.value.selectedMonth
        assertNotEquals(initialMonth, newMonth)
    }

    @Test
    fun `test switching to next month`() {
        val initialMonth = viewModel.uiState.value.selectedMonth
        viewModel.onEvent(CalendarEvent.NextMonth)
        val newMonth = viewModel.uiState.value.selectedMonth
        assertNotEquals(initialMonth, newMonth)
    }
}
