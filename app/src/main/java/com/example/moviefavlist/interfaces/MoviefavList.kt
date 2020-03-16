package com.example.moviefavlist.interfaces

import com.example.moviefavlist.model.user
import com.example.moviekotlinmvvm.pojo.movieslist

interface MoviefavList {
    fun onResultRequestS(favlist: ArrayList<movieslist> )
    fun onResultRequestF(msg: String)
}