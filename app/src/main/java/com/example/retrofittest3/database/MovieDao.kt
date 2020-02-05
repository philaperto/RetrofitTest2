package com.example.retrofittest3.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDao {

    @Query ("Select * from movie_table")
    fun getMovieList(): LiveData<List<Movie>>

    @Insert(onConflict = 1)
    suspend fun insertAll( movies :List<Movie>)

    @Query ("Delete from movie_table" )
    suspend fun deleteAll()

    @Query ("Select * from movie_table where id = :id")
    fun getMoviebyId(id : Int) : LiveData<Movie>

    @Query ("Delete from movie_table where id = :id")
    fun deleteMovieById(id: Int)
}