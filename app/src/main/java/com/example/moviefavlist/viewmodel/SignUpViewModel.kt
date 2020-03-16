package com.example.moviefavlist.viewmodel

import androidx.lifecycle.ViewModel
import com.example.moviefavlist.firebase.FirebaseSource

class SignUpViewModel : ViewModel() {



    var lol1: FirebaseSource =
        FirebaseSource()

    fun Create_User(
        email: String,
        password: String,
        phonenumber: String,
        username: String
    ){
        lol1.CreateInWithEmailAndPassword(email,password,phonenumber,username)
    }


}
