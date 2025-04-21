package com.example.uconeandroid.data.repository

import com.example.uconeandroid.data.api.RetrofitInstance
import com.example.uconeandroid.data.model.ProductModel
import com.example.uconeandroid.data.response.ApiResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

import okhttp3.MultipartBody
import retrofit2.Response

class ProductRepository {

    private val apiService = RetrofitInstance.api

    suspend fun uploadProductImage(image: MultipartBody.Part): Response<ApiResponse<String>> {
        return apiService.uploadProductImage(image)
    }

    suspend fun addProduct(productModel: ProductModel): Response<ApiResponse<ProductModel>> {
        return apiService.addProduct(productModel)
    }

    suspend fun fetchProducts(): Response<ApiResponse<List<ProductModel>>> {
        return apiService.fetchProducts()
    }
}