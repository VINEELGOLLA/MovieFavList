package com.example.moviefavlist.viewmodel

import androidx.lifecycle.ViewModel
import com.example.moviefavlist.firebase.FirebaseSource

class LoginViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    var lol1: FirebaseSource =
        FirebaseSource()


    fun login(email: String, password: String){
        lol1.signInWithEmailAndPassword(email, password)
    }

    fun check(){
        lol1.Check_loggedin_loggedout()
    }
}
