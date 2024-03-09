package com.chandan.workitreels.scheduling

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.chandan.workitreels.*

import com.chandan.workitreels.databinding.ActivitySchedulingBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class SchedulingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySchedulingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySchedulingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.save.setOnClickListener {
            val intent = Intent(this, SW::class.java)
            startActivity(intent)
            Toast.makeText(this,"Save Your Workouts",Toast.LENGTH_SHORT).show()
        }

        binding.view.setOnClickListener {
            val intent = Intent(this, View::class.java)
            startActivity(intent)
            Toast.makeText(this,"Saved Workouts",Toast.LENGTH_SHORT).show()
        }



        binding.navbar.setSelectedItemId(R.id.schedule)

        binding.navbar.setOnNavigationItemSelectedListener { item ->
            when(item.itemId)
            {
                R.id.schedule -> {

                    true
                }
                R.id.workouts -> {
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    overridePendingTransition(0,0)
                    finish()
                    true
                }
                R.id.reels -> {
                    startActivity(Intent(applicationContext, ReelsActivity::class.java))
                    overridePendingTransition(0,0)
                    finish()
                    true
                }
                R.id.profile -> {
                    startActivity(Intent(applicationContext, UserProfile::class.java))
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
}