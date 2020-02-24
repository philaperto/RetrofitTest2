package com.example.retrofittest3.views

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.retrofittest3.Repository.MovieRepository
import com.example.retrofittest3.database.Movie
import com.example.retrofittest3.database.MovieDatabase
import kotlinx.coroutines.launch

class MoviesByActorViewModel(application: Application) : AndroidViewModel(application){

    private val repository : MovieRepository

    init{
         val movieDao = MovieDatabase.getDatabase(application).movieDao()
        repository = MovieRepository(movieDao)
    }

   private val _movieList = MutableLiveData<List<Movie>>()
    val movieList : LiveData<List<Movie>>
            get() = _movieList

    fun getMovieList(actorId : Int){
        viewModelScope.launch{ _movieList.value = repository.getMoviesByActor(actorId).movie_credits.movieListByActor}
    }
}