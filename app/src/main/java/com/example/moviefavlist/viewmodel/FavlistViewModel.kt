package com.example.moviefavlist.viewmodel

import androidx.lifecycle.ViewModel
import com.example.moviefavlist.firebase.FirebaseSource

class FavlistViewModel : ViewModel() {
    var lol1: FirebaseSource =
        FirebaseSource()

    fun downloadfavlist(){
        lol1.download_fav_list()
    }

    fun delete(id: String){
        lol1.deletefav(id)
    }
}
