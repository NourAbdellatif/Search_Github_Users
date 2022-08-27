package com.c1ctech.mvvmwithnetworksource

import android.content.Intent
import android.os.Bundle
import android.util.Log
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.c1ctech.mvvmwithnetworksource.databinding.ActivityMainBinding
import com.c1ctech.mvvmwithnetworksource.repository.MainRepository
import com.c1ctech.mvvmwithnetworksource.viewmodel.MainViewModel
import kotlinx.coroutines.channels.ticker


class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    var page = 1
    var name = ""
    var sameName = false

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
        adapter.onItemClick = {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("user", it)
            startActivity(intent)
        }
        viewModel.userListLive.observe(this) {
            Log.d(TAG, "userList: $it")
            if (it == null) {
                Toast.makeText(
                    this,
                    "API limit reached try again in a few moments",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                if (sameName) {
                    adapter.setUserList(adapter.users + it)
                } else {
                    adapter.setUserList(it)
                }
            }
        }
        viewModel.errorMessage.observe(this) {
            Log.d(TAG, "errorMessage: $it")
        }
        val searchView = binding.searchView
        val recyclerView = binding.recyclerview
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                val totalItemCount = recyclerView.layoutManager!!.itemCount
                val lastPosition = linearLayoutManager?.findLastCompletelyVisibleItemPosition()
                Log.d(TAG, "Last Pos= $lastPosition")
                if (linearLayoutManager != null && lastPosition == totalItemCount - 1) {
                    //bottom of list!
                    page += 1
                    sameName = true
                    viewModel.addUsers(name, page)
                }
            }
        })
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(username: String): Boolean {
                name = username
                sameName = false
                page = 1
                viewModel.getAllUsers(username, page)
                return false
            }

            override fun onQueryTextChange(p0: String): Boolean {
                return false
            }

        })
    }
}