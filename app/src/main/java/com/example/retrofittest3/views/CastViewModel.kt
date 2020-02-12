package com.example.retrofittest3.views

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.retrofittest3.api.MovieRepository
import com.example.retrofittest3.database.Cast
import com.example.retrofittest3.database.MovieDatabase
import com.example.retrofittest3.database.MovieDetailResult
import kotlinx.coroutines.launch
import java.lang.Exception

class CastViewModel (application: Application) : AndroidViewModel(application){

    private val repository : MovieRepository

    init{
        val movieDao = MovieDatabase.getDatabase(application).movieDao()
        repository = MovieRepository(movieDao)
    }

    private var _actorList = MutableLiveData<List<Cast>>()
    val actorList : LiveData<List<Cast>>
        get()=_actorList


   /* fun getCast(movieId:Int){

        viewModelScope.launch {
            try{
                Log.i("log","movieId_supplied: $movieId")
                var rawCast = repository.getCast(movieId).credits.cast
                filterCast(rawCast)
                // _actorList.value = repository.getCast(movieId).credits.cast
            }catch (ex: Exception){
                Log.i("Log","$ex.message")
            }
        }
    } */

    fun getCast(movieId:Int){
        viewModelScope.launch {
            try{
                var rawCast : MovieDetailResult = repository.getCast(movieId)

                var rawCastList = rawCast.credits.cast
                filterCast(rawCastList)
            }catch (ex:Exception){
            }
        }
    }


    private fun filterCast(rawCast:List<Cast>){
        val rawSize : Int = rawCast.size
        Log.i("tag", "size_before: $rawSize ")
        val filteredCast = ArrayList<Cast>()
        for (cast in rawCast){
            if(cast.profile_path != null ){
                filteredCast.add(cast)
            }
        }
        val filteredSize : Int = filteredCast.size
        Log.i("tag", "size_after: $filteredSize ")
        _actorList.value = filteredCast
    }
}