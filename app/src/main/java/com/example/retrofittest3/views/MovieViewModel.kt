package com.example.retrofittest3.views

import android.app.Application
import androidx.annotation.MainThread
import androidx.lifecycle.*
import com.example.retrofittest3.MainActivity
import com.example.retrofittest3.api.ApiController
import com.example.retrofittest3.models.Movie

class MovieViewModel(application: Application) :AndroidViewModel(application), LifecycleOwner {
    override fun getLifecycle(): Lifecycle {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    val apiController = ApiController()



   /* fun getMovieList() : ArrayList<Movie>{
        val result = apiController.getResultList()
        return result
    } */
    fun observeMovies(){

        apiController.liveMovieList.observe(this,  Observer{

        })
    }



}
