package com.example.retrofittest3.database

import com.google.gson.annotations.SerializedName

data class Credits(

    @SerializedName("cast") val cast : List<Cast>
)