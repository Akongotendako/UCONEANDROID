package com.example.uconeandroid.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.uconeandroid.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        toggleCheckBox()
        navigateToSignUpScreen()
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
}