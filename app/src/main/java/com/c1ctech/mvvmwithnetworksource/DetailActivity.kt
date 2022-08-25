package com.c1ctech.mvvmwithnetworksource

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.layout_rv_item.*
import org.w3c.dom.Text

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val user=intent.getParcelableExtra<User>("user")
        if(user!=null){
            Log.e("DetailActivity", detailUsername.text as String)

            detailUsername.text=user.username
            Glide.with(this).load(user.avatar).placeholder(R.drawable.placeholder).into(findViewById(R.id.avatar))
            nFollowers.text=user.followers
            nFollowing.text=user.following
            nRepos.text=user.repos

        }

    }
}