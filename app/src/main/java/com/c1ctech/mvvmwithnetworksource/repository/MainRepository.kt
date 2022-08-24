package com.c1ctech.mvvmwithnetworksource.repository

import com.c1ctech.mvvmwithnetworksource.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService) {

    fun getAllUsers() = retrofitService.getAllUsers()
}