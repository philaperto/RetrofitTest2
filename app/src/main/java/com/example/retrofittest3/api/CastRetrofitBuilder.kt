package com.example.retrofittest3.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CastRetrofitBuilder {

    private const val BASE_URL: String = "https://api.themoviedb.org/"

    private val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
    }

    val apiService: MovieDBCastService by lazy {
        retrofitBuilder
            .build()
            .create(MovieDBCastService::class.java)
    }

}