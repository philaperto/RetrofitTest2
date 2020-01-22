package com.example.retrofittest3.api

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.retrofittest3.models.Movie
import com.example.retrofittest3.models.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AmitsRepo {
    fun getMovies():MutableLiveData<ArrayList<Movie>>{
        val retrofitCall = RetrofitBuilder.apiService.getMovies()
        Log.i("Tag", "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@           " + RetrofitBuilder.hashCode().toString())
        var movielist =  MutableLiveData<ArrayList<Movie>>()
        retrofitCall.enqueue(object : Callback<Result>{
            override fun onFailure(call: Call<Result>, t: Throwable) {
                movielist.value = null
            }

            override fun onResponse(call: Call<Result>, response: Response<Result>) {
               if (response.code() == 200) {
                   Log.i("Tag", "Actually worked.............................")
                   movielist.value = response.body()?.movieList
               }
            }

        })
        return movielist
    }
}