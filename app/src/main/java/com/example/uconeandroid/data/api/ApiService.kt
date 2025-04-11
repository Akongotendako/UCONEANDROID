package com.example.uconeandroid.data.api

import com.example.uconeandroid.data.model.UserModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("users")
    suspend fun getData(): List<UserModel>

    @POST("users")
    suspend fun signUp(@Body user: UserModel): Response<UserModel>

    @POST("users/sign-in")
    suspend fun signIn(@Body user: UserModel): Response<UserModel>
}