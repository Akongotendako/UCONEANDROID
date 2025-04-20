package com.example.uconeandroid.utils

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import androidx.core.content.ContentProviderCompat.requireContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream

fun imageConversion(uri: Uri, context: Context): MultipartBody.Part {
    val contentResolver: ContentResolver = context.contentResolver
    val inputStream = contentResolver.openInputStream(uri)
    val byteArrayOutputStream = ByteArrayOutputStream()
    inputStream?.copyTo(byteArrayOutputStream)
    val byteArray = byteArrayOutputStream.toByteArray()
    inputStream?.close()

    val requestBody = byteArray.toRequestBody("image/*".toMediaTypeOrNull(), 0, byteArray.size)
    return MultipartBody.Part.createFormData("image", "product_image.jpg", requestBody)
}