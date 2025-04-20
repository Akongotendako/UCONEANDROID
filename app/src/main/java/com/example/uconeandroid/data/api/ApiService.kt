package com.example.uconeandroid.data.api

import com.example.uconeandroid.data.model.ProductModel
import com.example.uconeandroid.data.model.UserModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @GET("users")
    suspend fun getData(): List<UserModel>

    @POST("users")
    suspend fun signUp(@Body user: UserModel): Response<UserModel>

    @POST("users/sign-in")
    suspend fun signIn(@Body user: UserModel): Response<UserModel>
    @POST("products")
    suspend fun addProduct(
        @Part("productName") productName: RequestBody,
        @Part("description") description: RequestBody,
        @Part("price") price: RequestBody,
        @Part("stock") stock: RequestBody,
        @Part("discount") discount: RequestBody,
        @Part image: MultipartBody.Part,
        @Part("sizes") sizes: RequestBody,
        @Part("category") category: RequestBody
    ): Response<ProductModel>
}