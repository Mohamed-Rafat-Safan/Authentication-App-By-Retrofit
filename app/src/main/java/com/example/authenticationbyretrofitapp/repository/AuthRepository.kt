package com.example.authenticationbyretrofitapp.repository

import com.example.authenticationbyretrofitapp.api.AuthApi
import com.example.authenticationbyretrofitapp.model.LoginRequest
import com.example.authenticationbyretrofitapp.model.RegisterRequest
import javax.inject.Inject


class AuthRepository @Inject constructor(
    private val api: AuthApi
) {

    fun register(
        registerRequest: RegisterRequest
    ) = api.register(registerRequest)


    fun login(
        request: LoginRequest
    ) = api.login(request)

    fun getUserInfo(token: String) = api.getUserInfo(token)

    fun logout() = api.logout()

}
