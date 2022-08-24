package com.c1ctech.mvvmwithnetworksource.repository

import com.c1ctech.mvvmwithnetworksource.RetrofitService
import retrofit2.http.Query

class MainRepository constructor(private val retrofitService: RetrofitService) {

    fun getAllUsers(@Query("q") username : String) = retrofitService.getAllUsers(username)
}