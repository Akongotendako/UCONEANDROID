package com.example.uconeandroid.utils


fun isValidGmailAddress(email: String): Boolean {
    val gmailPattern = "^[A-Za-z0-9+_.-]+@gmail\\.com$"
    return email.matches(gmailPattern.toRegex())
}