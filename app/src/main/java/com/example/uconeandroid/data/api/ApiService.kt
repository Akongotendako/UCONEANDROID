package com.example.uconeandroid.data.api

import com.example.uconeandroid.data.model.ProductModel
import com.example.uconeandroid.data.model.UserModel
import com.example.uconeandroid.data.response.ApiResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun getData(): List<UserModel>

    @POST("users")
    suspend fun signUp(@Body user: UserModel): Response<UserModel>

    @POST("users/sign-in")
    suspend fun signIn(@Body user: UserModel): Response<UserModel>
    @POST("products")
    suspend fun addProduct(
        @Body productModel: ProductModel
    ): Response<ApiResponse<ProductModel>>

    @Multipart
    @POST("products/upload")
    suspend fun uploadProductImage(
        @Part image: MultipartBody.Part
    ): Response<ApiResponse<String>>

    @GET("products")
    suspend fun fetchProducts(): Response<ApiResponse<List<ProductModel>>>

    @GET("products/category")
    suspend fun fetchProductByCategory(
        @Query("category") category: String
    ): Response<ApiResponse<List<ProductModel>>>
}