package com.example.quickcart.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val api: ApiInterface by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://sample-ecom.parallaxlogic.dev/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiInterface::class.java)
    }
}