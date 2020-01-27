package com.example.retrofittest3.views

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.retrofittest3.Database.MovieDatabase

import com.example.retrofittest3.api.*
import com.example.retrofittest3.models.Movie
import kotlinx.coroutines.launch

class MovieViewModel(application :Application) : AndroidViewModel(application){

   val repository : MovieRepository
    private var _liveMovieList = MutableLiveData<ArrayList<Movie>>()

    val liveMovieList: LiveData<ArrayList<Movie>>
        get() = _liveMovieList

   init{
      val movieDao = MovieDatabase.getDatabase(application, viewModelScope).movieDao()
          repository = MovieRepository(movieDao)
        getMovieList()

    }

    private fun getMovieList(){
        _liveMovieList = repository.getMovies()
    }
    fun insertToDB(movies : List<Movie>){
        viewModelScope.launch {
            repository.insertMovies(movies)
        }
    }

}
