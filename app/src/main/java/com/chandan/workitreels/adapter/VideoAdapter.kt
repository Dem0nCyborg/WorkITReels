package com.chandan.workitreels.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chandan.workitreels.MainActivity
import com.chandan.workitreels.R
import com.chandan.workitreels.VideoItem
import com.chandan.workitreels.model.VideoModel
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions



class VideoAdapter(private val videoList: List<VideoItem>) :
    RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.workout_video_single, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val videoItem = videoList[position]
        holder.titleTextView.text = videoItem.title
        // Load thumbnail image using Glide or any other image loading library
        Glide.with(holder.itemView.context)



        holder.itemView.setOnClickListener {
            // Launch activity to play video using ExoPlayer
            val intent = Intent(holder.itemView.context, MainActivity::class.java)
            intent.putExtra("videoUrl", videoItem.videoUrl)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return videoList.size
    }



    inner class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.grid_image)
        val titleTextView: TextView = itemView.findViewById(R.id.item_name)
    }
}