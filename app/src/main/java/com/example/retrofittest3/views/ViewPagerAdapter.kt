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
import com.example.retrofittest3.database.Cast
import kotlinx.android.synthetic.main.cast_layout.view.*

class ViewPagerAdapter(private val castList:List<Cast>, private val clickListener:(Cast) -> Unit) : RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cast_layout,parent,false)
        return ViewPagerViewHolder(view)
    }

    override fun getItemCount(): Int {
       return castList.size
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        holder.bind(castList[position],clickListener)
    }

    inner class ViewPagerViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        private val tvNameAndCharacter : TextView = itemView.findViewById(R.id.textViewCastName)
        private val castImage : ImageView = itemView.findViewById(R.id.imageView2)

        fun bind(cast : Cast, clickListener:(Cast) -> Unit){

            tvNameAndCharacter.text = cast.name + " as " + cast.character
            itemView.setOnClickListener { clickListener(cast) }

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load("https://image.tmdb.org/t/p/w500/" + cast.profile_path)
                .into(castImage)
        }
    }

}