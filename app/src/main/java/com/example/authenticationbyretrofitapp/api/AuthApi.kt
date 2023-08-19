package com.example.authenticationbyretrofitapp.api

import com.example.authenticationbyretrofitapp.model.*
import retrofit2.Call
import retrofit2.http.*


interface AuthApi {

    @POST("api/v1/register")
    fun register(
        @Body request: RegisterRequest
    ): Call<AuthResponse>


    @POST("api/v1/login")
    fun login(
        @Body request: LoginRequest,
    ): Call<AuthResponse>


    @GET("api/v1/me")
    fun getUserInfo(@Header("Cookie") token: String?): Call<UserInfoResponse>

    @GET("api/v1/logout")
    fun logout(): Call<StatusResponse>

}