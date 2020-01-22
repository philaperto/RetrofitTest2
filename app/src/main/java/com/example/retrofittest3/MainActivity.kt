package com.example.retrofittest3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofittest3.models.Movie
import com.example.retrofittest3.views.MovieRecyclerAdapter
import com.example.retrofittest3.views.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var movieData : TextView

    private lateinit var movieAdapter: MovieRecyclerAdapter

    var liveMovieList = MutableLiveData<ArrayList<Movie>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // movieData = findViewById(R.id.text_view)
        val movieViewModel = MovieViewModel()
        // val movieList = movieViewModel.getMovieList()

       // movieData.text = movieList[4].title


      /*  val baseUrl = "https://api.themoviedb.org/"
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(MovieDBService::class.java)
        val call = service.getMovies() */

    /*    call.enqueue(object : Callback<Result>{
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
       }) */
       // initRecyclerView()

        movieViewModel.liveMovieList.observe(this, Observer{
          //  newMovieList -> movieAdapter.submitList(newMovieList)
            newMovieList -> startRecyclerView(newMovieList)
        })
      //  movieViewModel.apiController.getResultList()
       // Log.i("Tag", "BBBBBBBBBBBBBBB")
    }
            private fun startRecyclerView(newMovieList : ArrayList<Movie>){
                initRecyclerView()
                movieAdapter.submitList(newMovieList)
            }


            private fun initRecyclerView(){


                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                movieAdapter = MovieRecyclerAdapter()
                recyclerView.adapter = movieAdapter
            }
            private fun checkMovieList(testmovieList : ArrayList<Movie>){
             //   Log.i("Tag", "AAAAAAAAAAAAAAAAA" + testmovieList[0].title)
            }
}
