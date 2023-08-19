package com.example.authenticationbyretrofitapp.model

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("token") val token: String,
    @SerializedName("user") val user: User
)