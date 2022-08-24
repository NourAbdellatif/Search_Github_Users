package com.c1ctech.mvvmwithnetworksource.model

import com.c1ctech.mvvmwithnetworksource.User
import com.google.gson.annotations.SerializedName

data class UserList(@SerializedName("items") val mList: List<User>)
