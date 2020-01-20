package com.example.retrofittest3.views

import com.example.retrofittest3.api.ApiController
import com.example.retrofittest3.models.Movie

class MovieViewModel {
    private val apiController = ApiController()

    fun getMovieList() : ArrayList<Movie>{
        val result = apiController.getResultList()
        return result
    }

}
