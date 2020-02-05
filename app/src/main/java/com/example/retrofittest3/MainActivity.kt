package com.example.retrofittest3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofittest3.database.Movie
import com.example.retrofittest3.views.MovieRecyclerAdapter
import com.example.retrofittest3.views.MovieViewModel
import com.example.retrofittest3.views.ShowDatabaseActivity
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
            Log.i("Tag", "Observer observed change in observable")
            movieViewModel.insertToDB(newMovieList)
        })
    }

    private fun startRecyclerView(newMovieList : List<Movie>){
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        movieAdapter = MovieRecyclerAdapter(newMovieList,{ movie : Movie -> movieClicked(movie) })
        recyclerView.adapter = movieAdapter
       // movieAdapter.submitList(newMovieList)
    }

    private fun movieClicked(movie : Movie){
    //   Toast.makeText(this,"${movie.room_id} ${movie.title}", Toast.LENGTH_SHORT).show()
   //  refreshList()

        Log.d("movieClicked", "We are here")
        // val intent = Intent(this, ShowDatabaseActivity::class.java)

        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra("movieId",movie.id)
        startActivity(intent)

    }
    private fun refreshList(){
        movieViewModel.getMovieList()
    }
}
