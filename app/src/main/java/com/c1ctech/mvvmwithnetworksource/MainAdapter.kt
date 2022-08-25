package com.c1ctech.mvvmwithnetworksource

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.c1ctech.mvvmwithnetworksource.databinding.LayoutRvItemBinding

class MainAdapter : RecyclerView.Adapter<MainViewHolder>() {
    private val temp = User("API LIMIT REACHED","https://cdn-icons-png.flaticon.com/512/25/25231.png?w=360","","","")
    var users = mutableListOf<User>((temp))

    var onItemClick: ((User)->Unit)?=null

    fun setUserList(users: List<User>) {
        this.users = users.toMutableList()
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = LayoutRvItemBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val user = users[position]
        holder.itemView.setOnClickListener{
            onItemClick?.invoke(user)
        }
        holder.binding.username.text = user.username
        Glide.with(holder.itemView.context).load(user.avatar).placeholder(R.drawable.placeholder)
            .into(holder.binding.avatar)

    }

    override fun getItemCount(): Int {
        return users.size
    }

}

class MainViewHolder(val binding: LayoutRvItemBinding) : RecyclerView.ViewHolder(binding.root) {

}