package com.example.retrofittest3.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "result_table")
data class Result (
    @SerializedName ("results") @PrimaryKey @ColumnInfo (name = "results")
    val movieList : ArrayList<Movie>
)
