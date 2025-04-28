package com.example.customcalendar.presentation.tasklist.event

sealed class TaskListEvent {
    data class DeleteTask(val taskId: Int) : TaskListEvent()
}