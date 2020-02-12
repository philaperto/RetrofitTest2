package com.example.retrofittest3.api

import com.example.retrofittest3.database.A_CastInfo
import com.example.retrofittest3.database.MovieDetailResult
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDBMoviebyActorService {
    @GET("3/person/{id}?api_key=9e4c58d77539b2933b4997321e5f6401&append_to_response=movie_credits")

    suspend fun getMovies(@Path("id")id:Int): A_CastInfo
}