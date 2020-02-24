package com.example.retrofittest3.views

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.retrofittest3.Repository.MovieRepository
import com.example.retrofittest3.database.Movie
import com.example.retrofittest3.database.MovieDatabase
import kotlinx.coroutines.launch


class MovieDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val repository : MovieRepository
    lateinit var _liveMovie : LiveData<Movie>

    val liveMovie: LiveData<Movie>
        get() = _liveMovie

    private val _currentMovie = MutableLiveData<Movie>()

    val currentMovie : LiveData<Movie>
        get() = _currentMovie

    init{
        val movieDao = MovieDatabase.getDatabase(application).movieDao()
        repository = MovieRepository(movieDao)
    }

    fun getMovieById(id:Int){
        _liveMovie = repository.getMovieById(id)
    }

    fun deleteMovieById(id: Int){
        viewModelScope.launch{ repository.deleteMoviebyId(id)}
    }

   fun findNextMovieUp(roomId : Int) {
       Log.i("tag","we got this far")
       viewModelScope.launch {_currentMovie.value = repository.getNextMovieUp(roomId)  }
   }

    fun findNextMovieDown(roomId: Int){
        viewModelScope.launch { _currentMovie.value = repository.getNextMovieDown(roomId) }
    }
}