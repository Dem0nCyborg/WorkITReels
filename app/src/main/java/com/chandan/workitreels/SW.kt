package com.chandan.workitreels

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.chandan.workitreels.databinding.ActivitySwBinding
import com.chandan.workitreels.scheduling.Users
import com.google.firebase.database.FirebaseDatabase

class SW : AppCompatActivity() {

    lateinit var  binding : ActivitySwBinding
    lateinit var day :String
    lateinit var workout :String
    lateinit var wrkt1 : String
    lateinit var wrkt2 :String
    var db = FirebaseDatabase.getInstance()
    var reference = db.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySwBinding.inflate(layoutInflater)
        setContentView(binding.root)




        binding.save.setOnClickListener {
            day = binding.day.text.toString()
            workout = binding.workout.text.toString()
            wrkt1 = binding.wrkt1.text.toString()
            wrkt2 = binding.wrkt2.text.toString()

            if (!day.isEmpty() && !workout.isEmpty() && !wrkt1.isEmpty() && !wrkt2.isEmpty()) {
                val users = Users(day, workout, wrkt1, wrkt2)
                val db = FirebaseDatabase.getInstance()
                val reference = db.getReference("Users")
                reference.child(day).setValue(users).addOnCompleteListener { task ->
                    binding.day.setText("")
                    binding.workout.setText("")
                    binding.wrkt1.setText("")
                    binding.wrkt2.setText("")
                    Toast.makeText(this, "Successfully Updated", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}