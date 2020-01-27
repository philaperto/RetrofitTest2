package com.example.retrofittest3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofittest3.database.Movie
import com.example.retrofittest3.views.MovieRecyclerAdapter
import com.example.retrofittest3.views.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var movieAdapter: MovieRecyclerAdapter
    private lateinit var movieViewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
    }

    private fun setupView() {
        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        movieViewModel.liveMovieList.observe(this, Observer{
                newMovieList -> startRecyclerView(newMovieList)
                movieViewModel.insertToDB(newMovieList)
        })
    }

    private fun startRecyclerView(newMovieList : ArrayList<Movie>){
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        movieAdapter = MovieRecyclerAdapter()
        recyclerView.adapter = movieAdapter
        movieAdapter.submitList(newMovieList)
    }

}
