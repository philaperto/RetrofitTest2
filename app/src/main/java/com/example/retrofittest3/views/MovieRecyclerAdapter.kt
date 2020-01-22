package com.example.retrofittest3.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.retrofittest3.R
import com.example.retrofittest3.models.Movie
import kotlinx.android.synthetic.main.layout_movie_entry.view.*

class MovieRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items : List<Movie> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(
                parent.context
            ).inflate(
                R.layout.layout_movie_entry,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is MovieViewHolder -> {
                holder.bind(items[position])
            }
        }
    }

    override fun getItemCount(): Int {
            return items.size
    }

    fun submitList(movieList : List<Movie>){
        items = movieList
    }

    inner class MovieViewHolder (
        itemView : View
    ) : RecyclerView.ViewHolder(itemView){

        private val movieImage : ImageView = itemView.movie_Poster
        private val movieTitle: TextView = itemView.movie_title

        fun bind (movie : Movie){
            movieTitle.text = movie.title

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