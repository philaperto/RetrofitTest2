package com.example.retrofittest3.models

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("popularity")
    val popularity : Double,
    @SerializedName("title")
    val title : String,
    @SerializedName("release_date")
    val release_date : String,
    @SerializedName("poster_path")
    val poster_path : String
)