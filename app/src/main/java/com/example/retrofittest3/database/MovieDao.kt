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
    suspend fun deleteMovieById(id: Int)

    @Query ("Select * from movie_table where room_id = :roomId")
    fun getMovieByRoomId(roomId : Int) : LiveData<Movie>

    @Query ("Select * from movie_table where room_id = :roomId")
    suspend fun getRawMovieByRoomId(roomId : Int) : Movie

    @Query ("Select * from movie_table where id = :id")
    suspend fun getRawMoviebyId(id : Int) : Movie

    @Query ("Select * from movie_table where id > :roomId Limit 1 ")
    suspend fun getNextMovieUp (roomId : Int) :Movie
}