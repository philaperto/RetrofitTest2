package com.example.retrofittest3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofittest3.api.MovieService
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.retrofittest3.models.Result
import com.example.retrofittest3.models.Movie
import com.example.retrofittest3.views.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var movieData : TextView
    private lateinit var movieAdapter: MovieRecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // movieData = findViewById(R.id.text_view)
        val movieViewModel = MovieViewModel()
        // val movieList = movieViewModel.getMovieList()

       // movieData.text = movieList[4].title


        val baseUrl = "https://api.themoviedb.org/"
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(MovieService::class.java)
        val call = service.getMovies()

        call.enqueue(object : Callback<Result>{
            override fun onFailure(call: Call<Result>, t: Throwable) {
                movieData.text = t.message
            }

            override fun onResponse(call: Call<Result>, response: Response<Result>) {
             val result : Result? = response.body()
             val movieList = result!!.movieList
            //movieData.text = movieList[4].title
                initRecyclerView()
                movieAdapter.submitList(movieList)
            }
       })
    }

            private fun initRecyclerView(){


                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                movieAdapter = MovieRecyclerAdapter()
                recyclerView.adapter = movieAdapter
            }

}
