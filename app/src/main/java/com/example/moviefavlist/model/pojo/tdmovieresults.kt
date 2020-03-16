package com.example.moviekotlinmvvm.pojo

data class tdmovieresults(
     var page: String? = null,
     val total_results: String? = null,
     val total_pages: String? = null,
     val results: List<movieslist>? = null
)

