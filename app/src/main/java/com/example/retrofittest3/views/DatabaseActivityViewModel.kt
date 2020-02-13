package com.example.retrofittest3.views

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.retrofittest3.api.MovieRepository
import com.example.retrofittest3.database.Movie
import com.example.retrofittest3.database.MovieDatabase


//TODO Review

class DatabaseActivityViewModel (application: Application) : AndroidViewModel(application){
    private val repository : MovieRepository
    lateinit var _liveMovieList : LiveData<List<Movie>>

    val liveMovieList: LiveData<List<Movie>>
        get() = _liveMovieList

    init{
        val movieDao = MovieDatabase.getDatabase(application).movieDao()
        repository = MovieRepository(movieDao)
        getMovieList()
    }
    fun getMovieList(){
        _liveMovieList = repository.getMoviesFromDatabase()
    }
}