package com.example.authenticationbyretrofitapp.model

import com.google.gson.annotations.SerializedName

data class StatusResponse(
    @SerializedName("message") val message: String,
    @SerializedName("success") val success: Boolean
)