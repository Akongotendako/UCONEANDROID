package com.example.uconeandroid.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.uconeandroid.R
import com.example.uconeandroid.data.model.UserModel
import com.example.uconeandroid.data.repository.UserRepository
import com.example.uconeandroid.utils.isValidGmailAddress
import com.example.uconeandroid.utils.passwordVisibility
import com.example.uconeandroid.viewModel.UserViewModel
import com.example.uconeandroid.viewModel.UserViewModelFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        val userRepository = UserRepository()
        val userFactory = UserViewModelFactory(userRepository)
        userViewModel = ViewModelProvider(this, userFactory)[UserViewModel::class.java]

        validateEmail()
        passwordVisibility(findViewById(R.id.loginPassword))
        toggleCheckBox()
        navigateToSignUpScreen()
        signIn()
    }

    private fun validateEmail() {
        val email = findViewById<EditText>(R.id.loginEmail)

        email.addTextChangedListener {
            val emailText = it.toString()

            if (emailText.isNotEmpty() && !isValidGmailAddress(emailText)) {
                email.error = "Enter valid email address"
                return@addTextChangedListener
            }
            email.error = null
        }
    }


    private fun toggleCheckBox() {

        var isCheck = false

        val loginCheckBox = findViewById<View>(R.id.loginCheckbox)

        loginCheckBox.setOnClickListener {

            isCheck = !isCheck

            val drawableId = if (isCheck) {
                R.drawable.check
            } else {
                R.drawable.uncheck
            }

            loginCheckBox.setBackgroundResource(drawableId)
        }
    }

    private fun navigateToSignUpScreen() {
        findViewById<TextView>(R.id.loginSignUpDesctination).setOnClickListener {
            startActivity(
                Intent(this, SignUpActivity::class.java)
            )
        }
    }

    private fun signIn() {
        findViewById<Button>(R.id.login).setOnClickListener {

            val email = findViewById<EditText>(R.id.loginEmail).text.toString()
            val password = findViewById<EditText>(R.id.loginPassword).text.toString()

            val user = UserModel(email = email, password = password)
            userViewModel.signIn(user)
        }
        userViewModel.user.observe(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, it.message(), Toast.LENGTH_LONG).show()
            } else {
                val error = it.errorBody()?.string()

                Log.e(
                    "RetrofitError", """
            ❌ Failed to create user
            HTTP Code: ${it.code()}
            Error Body: $error
        """.trimIndent()
                )

                Toast.makeText(this, error, Toast.LENGTH_LONG).show()
            }
        }
    }
}