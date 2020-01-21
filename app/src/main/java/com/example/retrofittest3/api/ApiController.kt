package com.example.retrofittest3.api

import androidx.lifecycle.MutableLiveData
import com.example.retrofittest3.models.Movie
import com.example.retrofittest3.models.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiController {

     var liveMovieList = MutableLiveData<ArrayList<Movie>>()

    fun getResultList(){
        lateinit var movieList : ArrayList<Movie>
        val baseUrl = "https://api.themoviedb.org/"
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(MovieService::class.java)
        val call = service.getMovies()

        call.enqueue(object : Callback<Result> {
            override fun onFailure(call: Call<Result>, t: Throwable) {

            }

            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                val result : Result? = response.body()
                movieList = result!!.movieList
               liveMovieList.value = movieList

            }

        })

    }
    object RetroFitAPI {

    }

}