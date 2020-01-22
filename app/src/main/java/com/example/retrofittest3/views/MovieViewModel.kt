package com.example.retrofittest3.views

import androidx.lifecycle.*
import com.example.retrofittest3.api.*
import com.example.retrofittest3.models.Movie

class MovieViewModel : ViewModel(){

    private val amitsRepo = MovieRepository()

    private var _liveMovieList = MutableLiveData<ArrayList<Movie>>()

    val liveMovieList: LiveData<ArrayList<Movie>>
        get() = _liveMovieList

    init{
        getMovieListFromAmit()
    }

    fun getMovieListFromAmit(){
        _liveMovieList = amitsRepo.getMoviesFromWebservice()
    }


}
