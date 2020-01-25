package com.example.retrofittest3.Database

import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.retrofittest3.models.Movie

@Dao
interface MovieDao {

    @Query ("Select * from movie_table")
    fun getMovieList(): MutableLiveData<ArrayList<Movie>>

    @Insert
    fun insertAll(vararg movies :Movie)
}