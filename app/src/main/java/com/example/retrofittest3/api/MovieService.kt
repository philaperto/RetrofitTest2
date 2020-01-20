package com.example.retrofittest3.api

import com.example.retrofittest3.models.Result
import retrofit2.Call
import retrofit2.http.GET

interface MovieService {
    @GET("3/movie/popular?api_key=9e4c58d77539b2933b4997321e5f6401")
    fun getMovies(): Call<Result>
}