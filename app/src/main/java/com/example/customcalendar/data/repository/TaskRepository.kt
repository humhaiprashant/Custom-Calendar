package com.example.customcalendar.data.repository

import com.example.customcalendar.data.request.DeleteTaskRequest
import com.example.customcalendar.data.request.GetTaskListRequest
import com.example.customcalendar.data.model.ResTask
import com.example.customcalendar.data.request.StoreTaskRequest

interface TaskRepository {
    suspend fun storeTask(request: StoreTaskRequest)
    suspend fun getTasks(request: GetTaskListRequest): ResTask
    suspend fun deleteTask(request: DeleteTaskRequest)
}
