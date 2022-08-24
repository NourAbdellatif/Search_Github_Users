package com.c1ctech.mvvmwithnetworksource.model

import com.c1ctech.mvvmwithnetworksource.Movie
import com.google.gson.annotations.SerializedName

data class MovieList(@SerializedName("Search") val mList: List<Movie>)
