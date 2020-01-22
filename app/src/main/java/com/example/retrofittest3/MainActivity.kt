package com.example.retrofittest3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofittest3.models.Movie
import com.example.retrofittest3.views.MovieRecyclerAdapter
import com.example.retrofittest3.views.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var movieAdapter: MovieRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movieViewModel = MovieViewModel()

        movieViewModel.liveMovieList.observe(this, Observer{
            newMovieList -> startRecyclerView(newMovieList)
        })
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

}
