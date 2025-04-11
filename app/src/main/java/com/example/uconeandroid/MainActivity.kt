package com.example.uconeandroid

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.uconeandroid.data.repository.UserRepository
import com.example.uconeandroid.viewModel.UserViewModel
import com.example.uconeandroid.viewModel.UserViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: UserViewModel
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userRepository = UserRepository()
        val userFactory = UserViewModelFactory(userRepository)
        viewModel = ViewModelProvider(this, userFactory)[UserViewModel::class.java]
    }
}