package com.example.customcalendar.data.service

import com.example.customcalendar.data.model.ResTask
import com.example.customcalendar.data.request.DeleteTaskRequest
import com.example.customcalendar.data.request.GetTaskListRequest
import com.example.customcalendar.data.request.StoreTaskRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface TaskApiService {

    @POST("api/storeCalendarTask")
    suspend fun storeCalendarTask(@Body body: StoreTaskRequest): Response<Unit>

    @POST("api/getCalendarTaskList")
    suspend fun getTaskList(@Body body: GetTaskListRequest): Response<ResTask>

    @POST("api/deleteCalendarTask")
    suspend fun deleteTask(@Body body: DeleteTaskRequest): Response<Unit>
}
