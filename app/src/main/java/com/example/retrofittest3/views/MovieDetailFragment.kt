package com.example.retrofittest3.views


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.retrofittest3.R
import com.example.retrofittest3.database.Movie
import kotlinx.android.synthetic.main.details_fragment_layout.*

/**
 * A simple [Fragment] subclass.
 */
class MovieDetailFragment : Fragment() {
    private lateinit var movieDetailViewModel : MovieDetailViewModel
    private var movieId = 0
    companion object {

        fun newInstance(movieId : Int) : MovieDetailFragment {
            val fragment = MovieDetailFragment()

            val bundle = Bundle()
            bundle.putInt("movieId", movieId)
            fragment.arguments = bundle
            return fragment
            }
        }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.details_fragment_layout, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null){
            movieId = arguments!!.getInt("movieId")
        }
        setUpFragment()
    }

    private fun setUpFragment(){

       movieDetailViewModel = ViewModelProvider(this).get(MovieDetailViewModel::class.java)
       movieDetailViewModel.getMovieById(movieId)

       movieDetailViewModel.liveMovie.observe(this, Observer{

           newMovie -> if(newMovie!=null)setValues(newMovie)

           Log.i("tag","Fragment_observer observed change in observable")
       })
       movieDetailViewModel.currentMovie.observe(this, Observer {
           currentMovie -> Log.i("tag","we got a new current movie : ${currentMovie.title}")
           setValues(currentMovie)
       })
    }

    private fun setValues(movie : Movie){
        castButton.setOnClickListener {
            val fragment = CastFragment.newInstance(movie.id)
            replaceFragment(fragment)
        }

        release_date.text = getString(R.string.release_date_text) + movie.release_date
        movie_title.text = movie.title
        overview.text = movie.overview
        Log.i("tag", "movie in setValues : ${movie.title}")

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        Glide.with(this)
            .applyDefaultRequestOptions(requestOptions)
            .load("https://image.tmdb.org/t/p/w500/" +movie.poster_path)
            .into(movie_Poster)
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = activity!!.supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.root_layout, fragment)
        transaction.commit()
    }
}
