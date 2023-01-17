package com.example.finals_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ProfileActivity : AppCompatActivity() {

    lateinit var changePasswordButton: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_profile_fragment)

        init()
        listeners()
    }

    private fun init(){
        changePasswordButton = findViewById(R.id.changePasswordButton)
    }

    private fun listeners(){
        changePasswordButton.setOnClickListener(){
            startActivity(Intent(this, ChangePasswordActivity::class.java))
            finish()
        }
    }
}