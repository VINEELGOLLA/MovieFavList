package com.example.moviekotlinmvvm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviefavlist.R
import com.example.moviekotlinmvvm.pojo.movieslist

class movielistAdapter(var list: List<movieslist>, val Clicklistener: ClickListener): RecyclerView.Adapter<movie_view_holder>() {

    interface ClickListener{
        fun itemclicked(task: movieslist)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): movie_view_holder {
        val view = LayoutInflater.from(parent?.context)
            .inflate(R.layout.movie_details,parent,false)
        return  movie_view_holder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: movie_view_holder, position: Int) {
        holder.moviename?.text = list.get(position).original_title
        holder.rating?.text = list.get(position).vote_average
        holder.releasedate?.text = list.get(position).release_date

        holder.itemView.setOnLongClickListener(View.OnLongClickListener {
            Clicklistener.itemclicked(list[position])
            true
        })


        if (list.get(position).poster_path != null) {
            Glide.with(holder.itemView.context).load("https://image.tmdb.org/t/p/w500//" + list.get(position).poster_path)
                .into(holder.image)
        } else {
            holder.image.setImageResource(R.drawable.noimage)
        }
    }
}


