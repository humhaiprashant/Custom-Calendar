package com.example.customcalendar.presentation.tasklist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.customcalendar.constants.Constants
import com.example.customcalendar.data.request.DeleteTaskRequest
import com.example.customcalendar.data.request.GetTaskListRequest
import com.example.customcalendar.data.repository.TaskRepository
import com.example.customcalendar.presentation.tasklist.event.TaskListEvent
import com.example.customcalendar.presentation.tasklist.uistate.TaskListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(TaskListUiState())
    val uiState: StateFlow<TaskListUiState> = _uiState

    init {
        getTasks()
    }

    fun onEvent(event: TaskListEvent) {
        when (event) {
            is TaskListEvent.DeleteTask -> {
                viewModelScope.launch {
                    repository.deleteTask(
                        DeleteTaskRequest(userId = Constants.USER_ID, taskId = event.taskId)
                    )
                    getTasks() // Refresh list
                }
            }
        }
    }

    private fun getTasks() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val response = repository.getTasks(GetTaskListRequest(userId = Constants.USER_ID))
                _uiState.update {
                    it.copy(
                        tasks = response.tasks,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        error = e.localizedMessage ?: "Unknown Error",
                        isLoading = false
                    )
                }
            }
        }
    }
}
