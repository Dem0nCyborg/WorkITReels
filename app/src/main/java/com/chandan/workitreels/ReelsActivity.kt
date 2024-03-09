package com.chandan.workitreels

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.chandan.workitreels.Exo.ExoPlayerItem
import com.chandan.workitreels.Exo.Video
import com.chandan.workitreels.Exo.VideoAdapter1
import com.chandan.workitreels.databinding.ActivityReelsBinding
import com.chandan.workitreels.scheduling.SchedulingActivity

class   ReelsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReelsBinding
    private lateinit var  adapter1 : VideoAdapter1
    private val videos = ArrayList<Video>()
    private val exoPlayerItems = ArrayList<ExoPlayerItem>()
   // lateinit var viewPager2: ViewPager2
   // lateinit var adapter: VideoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReelsBinding.inflate(layoutInflater)
        setContentView(binding.root)

//Exoplayer List

        videos.add(
            Video("User_1","Full Body Workout","https://firebasestorage.googleapis.com/v0/b/workitreels.appspot.com/o/WhatsApp%20Video%202023-02-28%20at%2011.07.10.mp4?alt=media&token=aa251909-7e0a-413f-babf-6d12efd28e5a")
        )
        videos.add(
            Video("User_2","Best Forearms Workout","https://firebasestorage.googleapis.com/v0/b/workitreels.appspot.com/o/WhatsApp%20Video%202023-02-28%20at%2011.32.48.mp4?alt=media&token=893eb327-afec-45a5-a343-98347afab00a")
        )
        videos.add(
            Video ("User_3","Leg Workout At Best","https://firebasestorage.googleapis.com/v0/b/workitreels.appspot.com/o/WhatsApp3.mp4?alt=media&token=f71f3cac-28cb-40a3-9651-a0efbd6a62d0")
        )
        videos.add(
            Video("User_4","Get Ready For Bigger Biceps💪","https://firebasestorage.googleapis.com/v0/b/workitreels.appspot.com/o/WhatsApp4.mp4?alt=media&token=8ac090bb-bc1c-4157-b38c-43404817925e")
        )
        videos.add(
            Video("User_5","Complete Abs Workout","https://firebasestorage.googleapis.com/v0/b/workitreels.appspot.com/o/WhatsApp5.mp4?alt=media&token=f853c2a1-266b-4496-b76f-91bb7f41aa2a")
        )
        videos.add(
            Video("User_6","Workout Shoulders Like Never Before","https://firebasestorage.googleapis.com/v0/b/workitreels.appspot.com/o/WhatsApp5.mp4?alt=media&token=f853c2a1-266b-4496-b76f-91bb7f41aa2a")
        )
        videos.add(
            Video("User_7","Guys..! Get Ready For Bigger Back","https://firebasestorage.googleapis.com/v0/b/workitreels.appspot.com/o/WhatsApp7.mp4?alt=media&token=98dae003-acc4-4dd5-b26f-2490f941ef7e")
        )
        videos.add(
            Video("User_8","It,s Time to Flex Ur Shoulders","https://firebasestorage.googleapis.com/v0/b/workitreels.appspot.com/o/WhatsApp8.mp4?alt=media&token=fd0c688d-5961-4cfc-8286-b83b9ea47651")
        )


        adapter1 = VideoAdapter1(this,videos,object : VideoAdapter1.OnVideoPreparedListener{
            override fun onVideoPrepared(exoPlayerItem: ExoPlayerItem) {
                exoPlayerItems.add(exoPlayerItem)
            }
        })
        binding.vpager.adapter = adapter1
        binding.vpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                val previousIndex = exoPlayerItems.indexOfFirst { it.exoPlayer.isPlaying }
                if(previousIndex != -1){
                    val player = exoPlayerItems[previousIndex].exoPlayer
                    player.pause()
                    player.playWhenReady = false
                }
                val newIndex = exoPlayerItems.indexOfFirst { it.position == position }
                if (newIndex != -1){
                    val player = exoPlayerItems[newIndex].exoPlayer
                    player.playWhenReady = true
                }
            }
        })





        binding.navbar.setSelectedItemId(R.id.reels)
        binding.navbar.setOnNavigationItemSelectedListener { item ->
            when(item.itemId)
            {
                R.id.schedule -> {
                    startActivity(Intent(applicationContext, SchedulingActivity::class.java))
                    overridePendingTransition(0,0)
                    finish()
                    true
                }
                R.id.workouts -> {
                    startActivity(Intent(applicationContext,MainActivity::class.java))
                    overridePendingTransition(0,0)
                    finish()
                    true
                }
                R.id.reels -> {

                    true
                }
                R.id.profile -> {
                    startActivity(Intent(applicationContext,UserProfile::class.java))
                    overridePendingTransition(0,0)
                    finish()
                    true
                }

                else -> {
                    false
                }
            }
        }

    }

   /* override fun onStart(){
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }

    fun run (){
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        viewPager2 = findViewById(R.id.vpager)

        val mDataBase = Firebase.database.getReference("Videos")

        val options = FirebaseRecyclerOptions.Builder<VideoModel>()
            .setQuery(mDataBase,VideoModel::class.java)
            .build()

        adapter = VideoAdapter(options)
        viewPager2.adapter = adapter
    }*/


    override fun onPause() {
        super.onPause()

        val index = exoPlayerItems.indexOfFirst { it.position == binding.vpager.currentItem }
        if (index != -1 ){
            val player = exoPlayerItems[index].exoPlayer
            player.playWhenReady = true
            player.play()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (exoPlayerItems.isNotEmpty()) {
            for (item in exoPlayerItems) {
                val player = item.exoPlayer
                player.stop()
                player.clearMediaItems()
            }
        }
    }

}