package com.chandan.workitreels

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class Splash_Screen : AppCompatActivity() {

    private val Splash_Time : Long = 4000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({

            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
        ,Splash_Time)

    }
}