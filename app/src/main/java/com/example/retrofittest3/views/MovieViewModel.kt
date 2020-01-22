package com.example.retrofittest3.views

import android.util.Log
import androidx.lifecycle.*
import com.example.retrofittest3.api.*
import com.example.retrofittest3.models.Movie
import com.example.retrofittest3.models.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel(){



   private val retrofitRepo = RetrofitRepo()
    private val amitsRepo = AmitsRepo()

    private var _liveMovieList = MutableLiveData<ArrayList<Movie>>()
    val liveMovieList: LiveData<ArrayList<Movie>>
        get() = _liveMovieList

    init{
        // getMovieList()
        getMovieListFromAmit()
    }

    fun getMovieListFromAmit(){
        _liveMovieList = amitsRepo.getMovies()
    }

    fun getMovieList() {


        val retrofitCall = RetrofitBuilder.apiService.getMovies()
       // val retrofitCall = retrofitRepo.retroFitCall
        retrofitCall.enqueue(object : Callback<Result> {
            override fun onFailure(call: Call<Result>, t: Throwable) {
                Log.i("Tag","Schlecht.....")
            }

            override fun onResponse(call: Call<Result>, response: Response<Result>) {

               _liveMovieList.value = response.body()?.movieList
            }

        })

    }

}
