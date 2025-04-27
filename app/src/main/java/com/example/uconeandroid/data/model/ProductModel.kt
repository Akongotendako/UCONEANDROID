package com.example.uconeandroid.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductModel(
    val id: String? = null,
    val productName: String,
    val description: String,
    val price: String,
    val stock: String,
    val discount: String,
    val imageUrl: String? = null,
    val sizes: List<String> = listOf(),
    val category: String
) : Parcelable
