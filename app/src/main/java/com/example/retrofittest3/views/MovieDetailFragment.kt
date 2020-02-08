package com.example.retrofittest3.views


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.retrofittest3.R
import com.example.retrofittest3.database.Movie
import kotlinx.android.synthetic.main.details_fragment_layout.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

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
        Log.i("Ta@@@@@@@@@@@@@@@@@g", movieId.toString())
        setUpFragment()

    }

    fun setUpFragment(){
       movieDetailViewModel = ViewModelProvider(this).get(MovieDetailViewModel::class.java)
       movieDetailViewModel.getMovieById(movieId)
       movieDetailViewModel.liveMovie.observe(this, Observer{

           newMovie -> if(newMovie!=null)setValues(newMovie)

           Log.i("tag","Fragment_observer observed change in observable")
       })
    }

    fun setValues(movie : Movie){
        deleteButton.setOnClickListener {
            movieDetailViewModel.deleteMovieById(movie.id)
            activity?.finish()
        }
        nextButton.setOnClickListener {
          /*  (context as CoroutineScope).launch {
                val nextMovie = movieDetailViewModel.findNextMovieUp(movie.room_id)*/
             //   Log.i("tag",nextMovie?.title)
            }

        }
        release_date.text = getString(R.string.release_date_text) + movie.release_date
        movie_title.text = movie.title
        overview.text = movie.overview

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        Glide.with(this)
            .applyDefaultRequestOptions(requestOptions)
            .load("https://image.tmdb.org/t/p/w500/" +movie.poster_path)
            .into(movie_Poster)
    }



}
