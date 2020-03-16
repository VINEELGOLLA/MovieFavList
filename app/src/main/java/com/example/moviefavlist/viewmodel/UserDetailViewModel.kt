package com.example.moviefavlist.viewmodel

import androidx.lifecycle.ViewModel
import com.example.moviefavlist.firebase.FirebaseSource
import com.google.firebase.auth.FirebaseAuth

class UserDetailViewModel : ViewModel() {

    var lol1: FirebaseSource =
        FirebaseSource()




    companion object{
        var mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    }

    fun signout(){
        mAuth.signOut()
    }

    fun downuserdetail(){
        lol1.download_user_details()
    }




}
