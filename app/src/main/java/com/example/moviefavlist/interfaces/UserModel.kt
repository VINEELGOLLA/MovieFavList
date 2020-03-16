package com.example.moviefavlist.interfaces

import com.example.moviefavlist.model.user

interface UserModel {
    fun onResultRequestS(user: user)
    fun onResultRequestF(msg: String)
}