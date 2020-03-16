package com.example.moviekotlinmvvm.api

import com.example.moviekotlinmvvm.pojo.tdmovieresults
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieInterface {
    @GET("3/search/movie?api_key=f1693850078bf7989f7c084671b57a5b")
    suspend fun  getMovies(
        @Query("query") query: String
    ): tdmovieresults

}