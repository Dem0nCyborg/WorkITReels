package com.chandan.workitreels.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chandan.workitreels.MainActivity
import com.chandan.workitreels.databinding.ActivityLoginSignupBinding
import com.google.firebase.auth.FirebaseAuth

class LoginSignup : AppCompatActivity() {

    private lateinit var binding: ActivityLoginSignupBinding
    private lateinit var auth : FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginSignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        binding.btnRegister.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }


    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null){
            Intent(this, MainActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }
    }

}