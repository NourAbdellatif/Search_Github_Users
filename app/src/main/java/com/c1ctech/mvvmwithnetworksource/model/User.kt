package com.c1ctech.mvvmwithnetworksource

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("login") val username: String,
    @SerializedName("avatar_url") val avatar: String,

)
