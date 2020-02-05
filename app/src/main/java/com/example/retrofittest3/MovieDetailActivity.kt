package com.example.retrofittest3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentFactory
import com.example.retrofittest3.views.MovieDetailFragment

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val selectedMovie = intent.getIntExtra("movieId", 0)
        val fragment = MovieDetailFragment.newInstance(selectedMovie)
        replaceFragment(fragment)
       // FragmentFactory.loadFragmentClass(MovieDetailFragment())


    }
    fun AppCompatActivity.replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.root_layout,fragment)
        //transaction.addToBackStack(null)
        transaction.commit()
    }
}
