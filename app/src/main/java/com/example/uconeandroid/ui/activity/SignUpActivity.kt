package com.example.uconeandroid.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.uconeandroid.R
import com.example.uconeandroid.data.model.UserModel
import com.example.uconeandroid.data.repository.UserRepository
import com.example.uconeandroid.utils.isValidGmailAddress
import com.example.uconeandroid.viewModel.UserViewModel
import com.example.uconeandroid.viewModel.UserViewModelFactory

class SignUpActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        val userRepository = UserRepository()
        val userFactory = UserViewModelFactory(userRepository)
        userViewModel = ViewModelProvider(this, userFactory)[UserViewModel::class.java]

        validateEmail()
        signUp()
    }

    private fun validateEmail() {

        val email = findViewById<EditText>(R.id.signUpEmail)

        email.addTextChangedListener {
            val emailText = it.toString()
            if (emailText.isNotEmpty() && !isValidGmailAddress(emailText)) {
                email.error = "Please enter a valid Gmail address"
            } else {
                email.error = null
            }
        }
    }

    private fun signUp() {


        findViewById<Button>(R.id.signUp).setOnClickListener {

            val email = findViewById<EditText>(R.id.signUpEmail).text.toString()
            val password = findViewById<EditText>(R.id.signUpPassword).text.toString()
            val confirmPassword = findViewById<EditText>(R.id.signUpConfirmPassword).text.toString()

            Log.e("EMAIL", email)
            val user = UserModel(
                email = email,
                password = password,
                confirmPassword = confirmPassword,
                role = "admin"
            )

            userViewModel.signUp(user)
        }

        userViewModel.user.observe(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "User Created", Toast.LENGTH_LONG).show()
            } else {
                val error = it.errorBody()?.string()
                Log.e(
                    "RetrofitError", """
            ❌ Failed to create user
            HTTP Code: ${it.code()}
            Error Body: $error
        """.trimIndent()
                )
                Toast.makeText(this, "Failed ${error}\n$error", Toast.LENGTH_LONG).show()
            }
        }
    }
}
















