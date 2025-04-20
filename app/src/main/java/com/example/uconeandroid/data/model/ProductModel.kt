package com.example.uconeandroid.data.model

import android.net.Uri
import com.google.gson.annotations.SerializedName

data class ProductModel(
    @SerializedName("_id")
    val id: String,
    @SerializedName("productName")
    val productName: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("stock")
    val stock: String,
    @SerializedName("discount")
    val discount: String,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("sizes")
    val sizes: List<String> = listOf(),
    @SerializedName("category")
    val category: String
)