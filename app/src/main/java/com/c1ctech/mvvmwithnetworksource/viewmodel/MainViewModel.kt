package com.c1ctech.mvvmwithnetworksource.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.c1ctech.mvvmwithnetworksource.model.User
import com.c1ctech.mvvmwithnetworksource.model.UserList
import com.c1ctech.mvvmwithnetworksource.repository.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    val userListLive = MutableLiveData<List<User>>()
    val errorMessage = MutableLiveData<String>()
    fun getAllUsers(username:String,page:Int) {

        val response = repository.getAllUsers(username,page)
        response.enqueue(object : Callback<UserList> {
            override fun onResponse(call: Call<UserList>, response: Response<UserList>) {
                userListLive.postValue(response.body()?.mList)
            }

            override fun onFailure(call: Call<UserList>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
    fun addUsers(username:String,page:Int){
        val response = repository.getAllUsers(username,page)
        response.enqueue(object : Callback<UserList> {
            override fun onResponse(call: Call<UserList>, response: Response<UserList>) {
                userListLive.postValue(response.body()?.mList)
            }

            override fun onFailure(call: Call<UserList>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}

