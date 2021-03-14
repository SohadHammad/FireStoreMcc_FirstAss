package com.example.firestoremcc.Adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firestoremcc.R
import com.example.firestoremcc.modle.User

class UserAdapter(var activity: Activity, var data: MutableList<User>) :
    RecyclerView.Adapter<UserAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val usersName: TextView = itemView.findViewById(R.id.name)
        val usersNumber: TextView = itemView.findViewById(R.id.phone)
        val usersAddress: TextView = itemView.findViewById(R.id.address)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(activity).inflate(R.layout.item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.usersName.text = data[position].name
        holder.usersNumber.text = data[position].number
        holder.usersAddress.text = data[position].address
    }
}