package com.example.retrofittest3.views


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofittest3.MovieDetailActivity

import com.example.retrofittest3.R
import com.example.retrofittest3.database.Movie
import kotlinx.android.synthetic.main.fragment_movies_by_actor.*


class MoviesByActorFragment : Fragment() {

    private lateinit var moviesByActorViewModel: MoviesByActorViewModel
    private var actorId = 0
    private lateinit var movieAdapter: MovieRecyclerAdapter

    companion object {

        fun newInstance(castId: Int): MoviesByActorFragment {
            val fragment = MoviesByActorFragment()
            val bundle = Bundle()
            bundle.putInt("castId", castId)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_by_actor, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        moviesByActorViewModel = ViewModelProvider(this).get(MoviesByActorViewModel::class.java)

        if (arguments != null){
            actorId = arguments!!.getInt("castId")
            Log.i("tag-MovieByActorFrgment", "cast im neuen Fragment : $actorId")
        }

        moviesByActorViewModel.getMovieList(actorId)
        moviesByActorViewModel.movieList.observe(this, Observer{
            movieList -> startRecyclerView(movieList)
        })
    }
    private fun startRecyclerView(movieList : List<Movie>){
        recycler_view_by_actor.layoutManager = LinearLayoutManager(activity)
        movieAdapter = MovieRecyclerAdapter(movieList, {movie : Movie -> movieClicked(movie)})
        recycler_view_by_actor.adapter = movieAdapter

    }

    private fun movieClicked(movie : Movie){
       val fragment = MovieDetailFragment.newInstance(movie.id)
        replaceFragment(fragment)


    }
    fun replaceFragment(fragment: Fragment){
        val fragmentManager = activity!!.supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.root_layout, fragment)
        transaction.commit()
    }
}
