package com.example.retrofittest3.views


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofittest3.R
import com.example.retrofittest3.database.Movie


class DatabaseRecycleAdapter : RecyclerView.Adapter<DatabaseRecycleAdapter.DatabaseViewHolder>() {

    private  var items = emptyList<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DatabaseViewHolder {
       return DatabaseViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.database_entry_layout,parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: DatabaseViewHolder, position: Int) {
        holder.bind(items[position])

    }
    fun submitList(movieList : List<Movie>){
        items = movieList
    }

    inner class DatabaseViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        private val movie_id : TextView = itemView.findViewById(R.id.database_id)
        private val movie_title : TextView = itemView.findViewById(R.id.movie_title_db)
        private val release_date : TextView = itemView.findViewById(R.id.release)

        fun bind(movie : Movie){
            movie_id.text = movie.id.toString()
            movie_title.text = movie.title
            release_date.text = movie.release_date
        }
    }


}