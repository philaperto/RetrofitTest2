package com.example.retrofittest3.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDao {

    @Query ("Select * from movie_table")
    fun getMovieList(): LiveData<List<Movie>>

    @Insert(onConflict = 5)
    suspend fun insertAll( movies :List<Movie>)
}