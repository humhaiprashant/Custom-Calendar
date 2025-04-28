package com.example.customcalendar.data.request

import com.example.customcalendar.data.model.TaskDetail
import com.google.gson.annotations.SerializedName

data class StoreTaskRequest(
    @SerializedName("user_id")
    val userId: Int,
    val task: TaskDetail
)