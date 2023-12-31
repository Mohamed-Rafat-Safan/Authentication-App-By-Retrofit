package com.example.authenticationbyretrofitapp.di

import android.content.Context
import com.example.authenticationbyretrofitapp.api.AuthApi
import com.example.authenticationbyretrofitapp.api.RetrofitInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAuthApi(
        @ApplicationContext context: Context
    ): AuthApi {
        return RetrofitInstance.api
    }

//    @Singleton
//    @Provides
//    fun provideUserApi(
//        remoteDataSource: RemoteDataSource,
//        @ApplicationContext context: Context
//    ): UserApi {
//        return remoteDataSource.buildApi(UserApi::class.java, context)
//    }
}