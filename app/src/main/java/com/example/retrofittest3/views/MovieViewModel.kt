package com.example.retrofittest3.views

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.example.retrofittest3.database.MovieDatabase

import com.example.retrofittest3.api.*
import com.example.retrofittest3.database.Movie
import com.example.retrofittest3.util.InternetUtils
import kotlinx.coroutines.launch

class MovieViewModel(application: Application) : AndroidViewModel(application) {

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
        _liveMovieList = repository.getMovies(getApplication())
    }

    fun insertToDB( movies : List<Movie>){
        if(InternetUtils.isInternetAvailable(getApplication())){
        viewModelScope.launch {
            repository.deleteAll()
            repository.insertMovies(movies)
        }
        }
    }

}
