package com.example.customcalendar.data.repository

import com.example.customcalendar.data.request.DeleteTaskRequest
import com.example.customcalendar.data.request.GetTaskListRequest
import com.example.customcalendar.data.model.ResTask
import com.example.customcalendar.data.request.StoreTaskRequest
import com.example.customcalendar.data.service.TaskApiService
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val apiService: TaskApiService
) : TaskRepository {

    override suspend fun storeTask(request: StoreTaskRequest) {
        val response = apiService.storeCalendarTask(request)
        if (!response.isSuccessful) {
            throw Exception("Failed to store task")
        }
    }

    override suspend fun getTasks(request: GetTaskListRequest): ResTask {
        val response = apiService.getTaskList(request)
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Empty task list")
        } else {
            throw Exception("Failed to fetch tasks")
        }
    }

    override suspend fun deleteTask(request: DeleteTaskRequest) {
        val response = apiService.deleteTask(request)
        if (!response.isSuccessful) {
            throw Exception("Failed to delete task")
        }
    }
}
