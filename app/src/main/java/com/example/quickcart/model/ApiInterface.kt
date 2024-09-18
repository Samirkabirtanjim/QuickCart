package com.example.quickcart.model

import com.example.quickcart.api.Login_Response
import com.example.quickcart.api.Registration_Response
import com.example.quickcart.api.auth.LoginRequest
import com.example.quickcart.api.auth.Registration
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {

    @POST("user/register")
    fun registerUser(@Body registration: Registration): Call<Registration_Response>

    @POST("user/login") // Ensure this endpoint matches your server
    fun loginUser(@Body loginRequest: LoginRequest): Call<Login_Response>
}
