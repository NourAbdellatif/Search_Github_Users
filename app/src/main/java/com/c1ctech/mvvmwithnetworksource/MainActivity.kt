package com.c1ctech.mvvmwithnetworksource

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.c1ctech.mvvmwithnetworksource.databinding.ActivityMainBinding
import com.c1ctech.mvvmwithnetworksource.repository.MainRepository
import com.c1ctech.mvvmwithnetworksource.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel

    private val retrofitService = RetrofitService.getInstance()
    val adapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        viewModel =
            ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(
                MainViewModel::class.java
            )


        binding.recyclerview.adapter = adapter

        viewModel.userList.observe(this, Observer {
            Log.d(TAG, "userList: $it")
            adapter.setUserList(it)
        })

        viewModel.errorMessage.observe(this, Observer {
            Log.d(TAG, "errorMessage: $it")
        })

        val searchview = binding.searchView
        searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(username: String): Boolean {
                viewModel.getAllUsers(username)
                return false
            }

            override fun onQueryTextChange(p0: String): Boolean {
                return false
            }

        })
    }

}