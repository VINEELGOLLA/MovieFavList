package com.example.moviekotlinmvvm.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object MovieApiService {

    const val BASE_URL: String = "https://api.themoviedb.org/"

    val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }



    val apiService: MovieInterface by lazy {
        retrofitBuilder
            .build()
            .create(MovieInterface::class.java)
    }



}