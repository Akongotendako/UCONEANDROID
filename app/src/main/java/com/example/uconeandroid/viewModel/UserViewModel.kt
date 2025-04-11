package com.example.uconeandroid.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uconeandroid.data.model.UserModel
import com.example.uconeandroid.data.repository.UserRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class UserViewModel(private val userRepository: UserRepository): ViewModel() {

    private val _user = MutableLiveData<Response<UserModel>>()
    val user:LiveData<Response<UserModel>> = _user


    fun signUp(userModel: UserModel) {
        viewModelScope.launch {
            try {
                val result = userRepository.signUp(userModel)
                _user.value = result
            } catch (e: Exception) {
                Log.e("User", "Error ${e.message}")
            }
        }
    }

    fun signIn(userModel: UserModel) {
        viewModelScope.launch {
            try {
                val result = userRepository.signIn(userModel)
                _user.value = result
            } catch (e: Exception) {
                Log.e("User", "Error ${e.message}")
            }
        }
    }
}