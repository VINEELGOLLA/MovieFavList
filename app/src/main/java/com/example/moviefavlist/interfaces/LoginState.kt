package com.example.moviefavlist.interfaces

enum class LoginState(var msg: String) {
    Loggedin("Authentication success."),
    NotLogginin("Authentication failed.")
}