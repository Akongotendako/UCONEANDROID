package com.example.uconeandroid.utils

import android.annotation.SuppressLint
import android.text.InputType
import android.view.MotionEvent
import android.widget.EditText
import com.example.uconeandroid.R


fun isValidGmailAddress(email: String): Boolean {
    val gmailPattern = "^[A-Za-z0-9+_.-]+@gmail\\.com$"
    return email.matches(gmailPattern.toRegex())
}


@SuppressLint("ClickableViewAccessibility")
fun passwordVisibility(editText: EditText) {
    var isVisible = false

    editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

    editText.setOnTouchListener { _, event ->
        if (event.action == MotionEvent.ACTION_UP) {
            // Get the drawable's bounds
            val drawableRight = editText.compoundDrawables[2]

            if (drawableRight != null && event.rawX >= (editText.right - drawableRight.bounds.width() - editText.paddingRight)) {
                isVisible = !isVisible

                if (isVisible) {
                    // Show password
                    editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                    editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.visibility_off, 0)
                } else {
                    // Hide password
                    editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                    editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.visibility, 0)
                }


                editText.setSelection(editText.text.length)
                return@setOnTouchListener true
            }
        }
        false
    }
}