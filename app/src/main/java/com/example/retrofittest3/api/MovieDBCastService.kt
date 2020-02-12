package com.example.retrofittest3.api

import com.example.retrofittest3.database.Credits
import com.example.retrofittest3.database.MovieDetailResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDBCastService {
    @GET("3/movie/{id}?api_key=9e4c58d77539b2933b4997321e5f6401&append_to_response=credits")
   // fun getActors(@Path("id")id:Int): Call<MovieDetailResult>
   suspend fun getActors(@Path("id")id:Int): MovieDetailResult
}

//