package com.example.retrofittest3.api

import androidx.lifecycle.MutableLiveData
import com.example.retrofittest3.models.Movie
import com.example.retrofittest3.models.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository {
    
    fun getMoviesFromWebservice():MutableLiveData<ArrayList<Movie>>{

        val retrofitCall = MovieRetrofitBuilder.apiService.getMovies()
        val movielist =  MutableLiveData<ArrayList<Movie>>()

        retrofitCall.enqueue(object : Callback<Result>{

            override fun onFailure(call: Call<Result>, t: Throwable) {
                movielist.value = null
            }

            override fun onResponse(call: Call<Result>, response: Response<Result>) {
               if (response.code() == 200) {
                   movielist.value = response.body()?.movieList
               }
            }
        })
        return movielist
    }

}