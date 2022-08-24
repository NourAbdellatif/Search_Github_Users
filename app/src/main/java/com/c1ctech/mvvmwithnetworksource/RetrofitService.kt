package com.c1ctech.mvvmwithnetworksource

import com.c1ctech.mvvmwithnetworksource.model.UserList
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("users")
    fun getAllUsers(@Query("q") username :String): Call<UserList>

    companion object {

        var retrofitService: RetrofitService? = null

        //Create the RetrofitService instance using the retrofit.
        fun getInstance(): RetrofitService {

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.github.com/search/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}