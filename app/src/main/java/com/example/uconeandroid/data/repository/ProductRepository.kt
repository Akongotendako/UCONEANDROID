package com.example.uconeandroid.data.repository

import com.example.uconeandroid.data.api.RetrofitInstance
import com.example.uconeandroid.data.model.ProductModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

import okhttp3.MultipartBody
import retrofit2.Response

class ProductRepository {

    suspend fun addProduct(
        productName: String,
        description: String,
        price: String,
        stock: String,
        discount: String,
        image: MultipartBody.Part,
        sizes: List<String>,
        category: String
    ): Response<ProductModel> {

        val productNameBody = productName.toRequestBody("text/plain".toMediaTypeOrNull())
        val descriptionBody = description.toRequestBody("text/plain".toMediaTypeOrNull())
        val priceBody = price.toRequestBody("text/plain".toMediaTypeOrNull())
        val stockBody = stock.toRequestBody("text/plain".toMediaTypeOrNull())
        val discountBody = discount.toRequestBody("text/plain".toMediaTypeOrNull())
        val sizesBody = sizes.joinToString(",").toRequestBody("text/plain".toMediaTypeOrNull())
        val categoryBody = category.toRequestBody("text/plain".toMediaTypeOrNull())

        return RetrofitInstance.api.addProduct(
            productNameBody,
            descriptionBody,
            priceBody,
            stockBody,
            discountBody,
            image,
            sizesBody,
            categoryBody
        )
    }
}