package com.example.retrofittest3.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofittest3.R
import com.example.retrofittest3.database.Movie
import kotlinx.android.synthetic.main.activity_show_database.*

class ShowDatabaseActivity : AppCompatActivity() {

    private lateinit var databaseAdapter: DatabaseRecycleAdapter
    private lateinit var databaseActivityViewModel: DatabaseActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_database)
        setupRecyclerView()
    }

    fun setupRecyclerView(){
        databaseActivityViewModel = ViewModelProvider(this).get(DatabaseActivityViewModel::class.java)
        databaseActivityViewModel.liveMovieList.observe(this, Observer{
            startrecyclerView(it)
        })
    }

    fun startrecyclerView(newMovieList : List<Movie>){
        recyclerView.layoutManager = LinearLayoutManager(this@ShowDatabaseActivity)
        databaseAdapter = DatabaseRecycleAdapter()
        recyclerView.adapter = databaseAdapter
        databaseAdapter.submitList(newMovieList)
    }
}
