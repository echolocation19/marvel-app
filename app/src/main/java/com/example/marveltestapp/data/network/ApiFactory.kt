package com.example.marveltestapp.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.RuntimeException

object ApiFactory {

    private const val BASE_URL = "https://gateway.marvel.com"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java) ?: throw RuntimeException("ApiService == null")
}