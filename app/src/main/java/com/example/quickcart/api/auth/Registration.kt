package com.example.quickcart.api.auth

data class Registration(
    val email: String?,
    val name: String?,
    val password: String?,
    val phone: String?
)