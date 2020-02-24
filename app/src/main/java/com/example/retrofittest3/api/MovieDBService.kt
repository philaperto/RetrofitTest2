package com.example.retrofittest3.api

import com.example.retrofittest3.database.Result
import retrofit2.Call
import retrofit2.http.GET

interface MovieDBService {
    @GET("3/movie/popular?api_key=9e4c58d77539b2933b4997321e5f6401")
    suspend fun getMovies() : Result
    //fun getMovies(): Call<Result>
}