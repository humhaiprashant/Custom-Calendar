package com.example.customcalendar.data.request

import com.google.gson.annotations.SerializedName

data class GetTaskListRequest(
    @SerializedName("user_id")
    val userId: Int
)