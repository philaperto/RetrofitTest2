package com.example.retrofittest3.database

import com.google.gson.annotations.SerializedName

data class A_MovieCredits(

    @SerializedName("cast") val movieList : List<Movie>

)