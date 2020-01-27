package com.example.retrofittest3.views

import android.app.Application
import androidx.lifecycle.*
import com.example.retrofittest3.database.MovieDatabase

import com.example.retrofittest3.api.*
import com.example.retrofittest3.database.Movie
import kotlinx.coroutines.launch

class MovieViewModel(application :Application) : AndroidViewModel(application){

    private val repository : MovieRepository
    private lateinit var _liveMovieList : LiveData<ArrayList<Movie>>

    val liveMovieList: LiveData<ArrayList<Movie>>
        get() = _liveMovieList

   init{
      val movieDao = MovieDatabase.getDatabase(application).movieDao()
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
