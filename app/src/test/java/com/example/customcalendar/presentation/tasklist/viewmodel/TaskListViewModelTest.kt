package com.example.customcalendar.presentation.tasklist.viewmodel

import com.example.customcalendar.data.model.ResTask
import com.example.customcalendar.data.model.Task
import com.example.customcalendar.data.repository.TaskRepository
import com.example.customcalendar.presentation.tasklist.event.TaskListEvent
import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TaskListViewModelTest {

    private lateinit var viewModel: TaskListViewModel

    @MockK
    private lateinit var repository: TaskRepository

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = TaskListViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetch tasks updates ui state`() = runTest {
        val fakeTasks = ResTask(
            tasks = listOf(
                Task(
                    taskId = 1,
                    taskDetail = mockk()
                ),
                Task(
                    taskId = 2,
                    taskDetail = mockk()
                )
            )
        )

        coEvery {
            repository.getTasks(any())
        } returns fakeTasks

        coEvery { repository.deleteTask(any()) } just Runs
        viewModel.onEvent(TaskListEvent.DeleteTask(1))
        advanceUntilIdle()

        // Instead of awaitItem() and test {}, do this:
        val state = viewModel.uiState.value
        assertTrue(state.tasks.isNotEmpty())
    }
}
