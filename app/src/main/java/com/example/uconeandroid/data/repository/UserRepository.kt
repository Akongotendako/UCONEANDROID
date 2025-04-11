package com.example.uconeandroid.data.repository

import com.example.uconeandroid.data.api.RetrofitInstance
import com.example.uconeandroid.data.model.UserModel

class UserRepository {
    suspend fun getData() = RetrofitInstance.api.getData()
    suspend fun signUp(user: UserModel) = RetrofitInstance.api.signUp(user)
    suspend fun signIn(user: UserModel) = RetrofitInstance.api.signIn(user)
}