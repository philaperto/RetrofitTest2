package com.example.retrofittest3.views

import android.app.Application
import androidx.lifecycle.*
import com.example.retrofittest3.database.MovieDatabase

import com.example.retrofittest3.api.*
import com.example.retrofittest3.database.Movie
import com.example.retrofittest3.util.InternetUtils
import kotlinx.coroutines.launch

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    private val repository : MovieRepository

    lateinit var _liveWebMovieList : LiveData<List<Movie>>
    val liveWebMovieList: LiveData<List<Movie>>
        get() = _liveWebMovieList

    lateinit var _liveDBMovieList : LiveData<List<Movie>>
    val liveDBMovieList: LiveData<List<Movie>>
        get() = _liveDBMovieList

   init{
      val movieDao = MovieDatabase.getDatabase(application).movieDao()
          repository = MovieRepository(movieDao)
        getMovieList()
        getMovieListFromDatabase()

    }
    fun getMovieListFromDatabase(){
        _liveDBMovieList = repository.getMoviesFromDatabase()
    }

    fun getMovieList(){
      _liveWebMovieList = repository.getMovies(getApplication())
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
