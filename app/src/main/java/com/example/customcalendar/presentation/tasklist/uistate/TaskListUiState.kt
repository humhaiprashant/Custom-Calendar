package com.example.customcalendar.presentation.tasklist.uistate

import com.example.customcalendar.data.model.Task

data class TaskListUiState(
    val tasks: List<Task> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)
