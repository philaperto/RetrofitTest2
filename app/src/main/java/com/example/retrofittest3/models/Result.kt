package com.example.retrofittest3.models

import com.google.gson.annotations.SerializedName

data class Result (
    @SerializedName("results")
    val movieList : ArrayList<Movie>
)
