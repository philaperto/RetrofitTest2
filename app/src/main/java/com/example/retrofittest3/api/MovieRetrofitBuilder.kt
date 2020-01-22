package com.example.retrofittest3.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieRetrofitBuilder {

   private const val BASE_URL: String = "https://api.themoviedb.org/"

   private val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    val apiService: MovieDBService by lazy {
        retrofitBuilder
            .build()
            .create(MovieDBService::class.java)
    }

}