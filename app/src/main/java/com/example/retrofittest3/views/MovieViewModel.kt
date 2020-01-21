package com.example.retrofittest3.views

import android.app.Application
import androidx.annotation.MainThread
import androidx.lifecycle.*
import com.example.retrofittest3.MainActivity
import com.example.retrofittest3.api.ApiController
import com.example.retrofittest3.models.Movie
import kotlin.coroutines.coroutineContext

class MovieViewModel(application1: Application) : AndroidViewModel(application1), LifecycleOwner {


    val apiController = ApiController()

    var liveMovieList = MutableLiveData<ArrayList<Movie>>()


    override fun getLifecycle(): Lifecycle {
        return 
    }





   /* fun getMovieList() : ArrayList<Movie>{
        val result = apiController.getResultList()
        return result
    } */
    fun observeMovies(){
        apiController.liveMovieList.observe(this,  Observer { observedMovieList ->
            liveMovieList.value = observedMovieList
        })
    }



}
