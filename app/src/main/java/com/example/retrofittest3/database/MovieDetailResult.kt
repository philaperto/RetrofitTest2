package com.example.retrofittest3.database

import com.google.gson.annotations.SerializedName

data class MovieDetailResult(
    @SerializedName("credits") val credits : Credits
)