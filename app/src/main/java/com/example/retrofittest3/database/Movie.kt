package com.example.retrofittest3.database

import android.os.SystemClock
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.sql.Date
import java.sql.Time
import java.util.*

@Entity(tableName = "movie_table")

data class Movie(

    @SerializedName("popularity") @ColumnInfo (name = "popularity")
    val popularity : Double,

    @SerializedName("title")  @ColumnInfo (name = "title")
    val title : String,

    @SerializedName("id") @ColumnInfo (name="id")
    val id : Int,

    @SerializedName("release_date") @ColumnInfo (name = "release_date")
    val release_date : String,

    @SerializedName("poster_path") @ColumnInfo (name = "poster_path")
    val poster_path : String,

    @SerializedName("overview") @ColumnInfo (name = "overview")
    val overview : String,

    @PrimaryKey (autoGenerate = true) @ColumnInfo
    val room_id : Int

)
