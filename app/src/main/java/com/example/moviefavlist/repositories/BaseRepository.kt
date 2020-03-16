package com.example.moviefavlist.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviekotlinmvvm.api.MovieApiService
import com.example.moviekotlinmvvm.pojo.tdmovieresults
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object BaseRepository {

    var job: CompletableJob? = null
    //var user: tdmovieresults = tdmovieresults()


    fun getUser(query: String): MutableLiveData<tdmovieresults> {
        job = Job()
        println("maya" + query)
        return object : MutableLiveData<tdmovieresults>(){
            override fun onActive(){
                super.onActive()
                job?.let {job ->
                    CoroutineScope(IO + job).launch {
                        System.out.println("inside base lol repository")
                        val user =  MovieApiService.apiService.getMovies(query)
                        System.out.println("inside base lol repository")

                        withContext(Main){
                            value = user
                            job.complete()
                        }
                    }
                }
            }
        }
    }

        fun cancelJobs(){
        println("destroy")
        job?.cancel()
    }
}