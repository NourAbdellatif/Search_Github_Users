package com.c1ctech.mvvmwithnetworksource

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.c1ctech.mvvmwithnetworksource.model.User
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    companion object{
        private const val DETAIL_ACTIVITY = "DetailActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val user=intent.getParcelableExtra<User>("user")
        if(user!=null){
            Log.e(DETAIL_ACTIVITY, detailUsername.text as String)

            detailUsername.text=user.username
            Glide.with(this).load(user.avatar).placeholder(R.drawable.placeholder).into(findViewById(R.id.avatar))
            nFollowers.text=user.followers
            nFollowing.text=user.following
            nRepos.text=user.repos

        }

    }
}