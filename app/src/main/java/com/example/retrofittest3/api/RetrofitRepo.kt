package com.example.retrofittest3.api


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitRepo {
    val baseUrl = "https://api.themoviedb.org/"
    val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(baseUrl).build()
    val retrofitService = retrofit.create(MovieDBService::class.java)
    val retroFitCall = retrofitService.getMovies()





   }
