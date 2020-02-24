package com.example.retrofittest3.views

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.retrofittest3.R
import com.example.retrofittest3.database.Movie
import kotlinx.android.synthetic.main.layout_movie_entry.view.*

class MovieRecyclerAdapter( private val items : List<Movie>, private val clickLalaListener: (Movie) -> Unit ) : RecyclerView.Adapter<MovieRecyclerAdapter.MovieViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(parent.context).
            inflate(R.layout.layout_movie_entry, parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        holder.bind(items[position], clickLalaListener)
    }

    override fun getItemCount(): Int {
            return items.size
    }

   /* fun submitList(movieListByActor : List<Movie>){
        items = movieListByActor
    }*/

    inner class MovieViewHolder (
        itemView : View
    ) : RecyclerView.ViewHolder(itemView){

        private val movieImage : ImageView = itemView.movie_Poster
        private val movieTitle: TextView = itemView.movie_title

        fun bind (movie : Movie, clickLiLiListener: (Movie) -> Unit){

            movieTitle.text = movie.title
            itemView.setOnClickListener{clickLiLiListener(movie) }

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load("https://image.tmdb.org/t/p/w500/" +movie.poster_path)
                .into(movieImage)
        }
    }

}