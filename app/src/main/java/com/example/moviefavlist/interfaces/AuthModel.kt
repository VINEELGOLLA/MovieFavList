package com.example.moviefavlist.interfaces

interface AuthModel {
    fun onResultRequest(state: AuthState)
    fun onResultLoggedinState(state: LoginState)
}