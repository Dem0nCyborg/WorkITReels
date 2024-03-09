package com.chandan.workitreels

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewAdapter(private val userList : ArrayList<User>) : RecyclerView.Adapter<ViewAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.user_item,
            parent,false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = userList[position]
        holder.day.text = currentitem.day
        holder.workout.text = currentitem.workout
        holder.wrkt1.text = currentitem.wrkt1
        holder.wrkt2.text = currentitem.wrkt2

    }

    override fun getItemCount(): Int {

        return userList.size
    }


    class MyViewHolder(itemView : android.view.View) : RecyclerView.ViewHolder(itemView){
        val day : TextView = itemView.findViewById(R.id.tvday)
        val workout : TextView = itemView.findViewById(R.id.tvwrkt)
        val wrkt1 : TextView = itemView.findViewById(R.id.tvwrkt1)
        val wrkt2 : TextView = itemView.findViewById(R.id.tvwrkt2)

    }

}