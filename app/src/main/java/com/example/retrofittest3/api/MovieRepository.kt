package com.example.retrofittest3.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.retrofittest3.Database.MovieDao
import com.example.retrofittest3.models.Movie
import com.example.retrofittest3.models.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository (private val movieDao: MovieDao){

    fun getMovies():MutableLiveData<ArrayList<Movie>>{


        return getMoviesFromWebservice()
    }
    
   private fun getMoviesFromWebservice():LiveData<ArrayList<Movie>>{

        val retrofitCall = MovieRetrofitBuilder.apiService.getMovies()
        val movieList =  MutableLiveData<ArrayList<Movie>>()

        retrofitCall.enqueue(object : Callback<Result>{

            override fun onFailure(call: Call<Result>, t: Throwable) {
                movieList.value = null
            }

            override fun onResponse(call: Call<Result>, response: Response<Result>) {
               if (response.code() == 200) {
                   movieList.value = response.body()?.movieList
               }
            }
        })

        return movieList
    }

    private fun getMoviesFromDatabase(): LiveData<List<Movie>> {
    return movieDao.getMovieList()

    }

     suspend fun insertMovies(movies : List<Movie>){
        movieDao.insertAll(movies)
         Log.i("tag","ruuuuuuuuunnnnniiiiiing")
    }

}