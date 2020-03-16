package com.example.moviekotlinmvvm

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviefavlist.R

class movie_view_holder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val moviename = itemView.findViewById<TextView>(R.id.movie_name)
    val releasedate = itemView.findViewById<TextView>(R.id.releasedate)
    val rating = itemView.findViewById<TextView>(R.id.rating)
    val image = itemView.findViewById<ImageView>(R.id.imageview)
}