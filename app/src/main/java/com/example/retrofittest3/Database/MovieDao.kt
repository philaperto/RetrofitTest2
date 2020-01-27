package com.example.retrofittest3.Database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.retrofittest3.models.Movie

@Dao
interface MovieDao {

    @Query ("Select * from movie_table")
    fun getMovieList(): LiveData<List<Movie>>

    @Insert(onConflict = 5)
    suspend fun insertAll( movies :List<Movie>)
}