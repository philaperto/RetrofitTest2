package com.example.retrofittest3.views

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.retrofittest3.Repository.MovieRepository
import com.example.retrofittest3.database.MovieDatabase

import com.example.retrofittest3.database.Movie
import com.example.retrofittest3.util.InternetUtils
import kotlinx.coroutines.launch

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    private val repository : MovieRepository

    private var _liveWebMovieList = MutableLiveData<List<Movie>>()
    val liveWebMovieList: LiveData<List<Movie>>
        get() = _liveWebMovieList

   init{
      val movieDao = MovieDatabase.getDatabase(application).movieDao()
          repository = MovieRepository(movieDao)
        getMovieList()
    }

    fun getMovieList(){
      viewModelScope.launch {  try {
          _liveWebMovieList.value = repository.getMoviesFromWebKotlinStyle().movieList
      }catch (ex:Exception){
          Log.i("tag", "$ex.message")
      }
      }
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
