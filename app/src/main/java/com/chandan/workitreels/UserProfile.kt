package com.chandan.workitreels

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import com.bumptech.glide.Glide

import com.chandan.workitreels.databinding.ActivityUserProfileBinding
import com.chandan.workitreels.scheduling.SchedulingActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference


class UserProfile : AppCompatActivity() {

    private lateinit var binding: ActivityUserProfileBinding


    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    private lateinit var firebaseStorage: FirebaseStorage
    private lateinit var storageReference: StorageReference

    private lateinit var usernameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var phoneNumberEditText: EditText
    private lateinit var profileImageView: ImageView

    private var userProfileImage: Bitmap? = null
    private var userProfileImageUri: Uri? = null

    companion object {
        private const val PICK_IMAGE_REQUEST_CODE = 123
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.navbar.setSelectedItemId(R.id.profile)

        binding.navbar.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.schedule -> {
                    startActivity(Intent(applicationContext, SchedulingActivity::class.java))
                    finish()
                    overridePendingTransition(0, 0)
                    finish()
                    true
                }
                R.id.workouts -> {
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    finish()
                    overridePendingTransition(0, 0)
                    finish()
                    true
                }
                R.id.reels -> {
                    startActivity(Intent(applicationContext, ReelsActivity::class.java))
                    finish()
                    overridePendingTransition(0, 0)
                    finish()
                    true
                }
                R.id.profile -> {

                    true
                }

                else -> {
                    false
                }
            }
        }


        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("users")
        firebaseStorage = FirebaseStorage.getInstance()
        storageReference = firebaseStorage.reference

        usernameEditText = findViewById(R.id.usernameEditText)
        emailEditText = findViewById(R.id.emailEditText)
        phoneNumberEditText = findViewById(R.id.phoneNumberEditText)
        profileImageView = findViewById(R.id.profileImageView)

        // Load user profile data from Firebase Realtime Database
        loadUserProfileData()

        // Save user profile data to Firebase Realtime Database when the Save button is clicked
        findViewById<TextView>(R.id.saveButton).setOnClickListener {
            saveUserProfileData()
        }

        // Pick a profile image when the Profile Image View is clicked
        profileImageView.setOnClickListener {
            pickProfileImage()
        }


    }

    private fun pickProfileImage() {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, PICK_IMAGE_REQUEST_CODE)
    }

    private fun loadUserProfileData() {
        val userId = firebaseAuth.currentUser?.uid

        if (userId != null) {
            databaseReference.child(userId).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val user = dataSnapshot.getValue(user0::class.java)
                    if (user != null) {
                        usernameEditText.setText(user.username)
                        emailEditText.setText(user.email)
                        phoneNumberEditText.setText(user.phoneNumber)

                        if (user.profileImageUri != null) {
                            userProfileImageUri = Uri.parse(user.profileImageUri)
                            Glide.with(this@UserProfile)
                                .load(userProfileImageUri)
                                .placeholder(R.drawable.ic_person_pin)
                                .error(R.drawable.ic_person_pin)
                                .into(profileImageView)
                        }
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Toast.makeText(this@UserProfile,
                        databaseError.message,
                        Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun saveUserProfileData() {
        val userId = firebaseAuth.currentUser?.uid

        if (userId != null) {
            val user = User(
                usernameEditText.text.toString().trim(),
                emailEditText.text.toString().trim(),
                phoneNumberEditText.text.toString().trim(),
                userProfileImageUri.toString()
            )

            databaseReference.child(userId).setValue(user)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "User profile saved", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Error saving user profile", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }



}