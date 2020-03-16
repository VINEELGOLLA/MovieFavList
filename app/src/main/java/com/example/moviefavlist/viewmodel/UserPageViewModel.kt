package com.example.moviefavlist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviefavlist.firebase.FirebaseSource
import com.example.moviefavlist.repositories.BaseRepository
import com.example.moviekotlinmvvm.pojo.movieslist
import com.example.moviekotlinmvvm.pojo.tdmovieresults

class UserPageViewModel : ViewModel() {

    var lol1: FirebaseSource =
        FirebaseSource()

    //var newsLiveData: List<movieslist>? = null
    var newsLiveData1: MutableLiveData<tdmovieresults> = MutableLiveData()


    fun getquery(query: String) {
        println("view model" + query)
        //val latestNews = BaseRepository.getUser(query)
        //newsLiveData = latestNews.value?.results
        newsLiveData1 = BaseRepository.getUser(query)
        System.out.println("lopoi")
        //System.out.println(newsLiveData1.value)

        //newsLiveData = latestNews.results

        //print(newsLiveData)
    }

    fun cancelJobs(){
        BaseRepository.cancelJobs()
    }

    fun uploadfav(list: movieslist){
        lol1.uploadfavroute(list)
    }
}
