package com.example.moviefavlist.interfaces

enum class AuthState(var msg: String) {
    SUCCESS("Authentication success."),
    FAILED("Authentication failed."),
    EXCEPTION("Authentication exception.")
}