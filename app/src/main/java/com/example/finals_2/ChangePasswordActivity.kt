package com.example.finals_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ChangePasswordActivity : AppCompatActivity() {

    lateinit var currentPasswordEditText: EditText
    lateinit var newPasswordEditText: EditText
    lateinit var changePassword: TextView
    lateinit var changePasswordGoBackButton: ImageView

    private val firebaseAuth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        init()
        listeners()

    }

    private fun init(){
        currentPasswordEditText = findViewById(R.id.currentPasswordEditText)
        newPasswordEditText = findViewById(R.id.newPasswordEditText)
        changePassword = findViewById(R.id.changePassword)
        changePasswordGoBackButton = findViewById(R.id.changePasswordGoBackButton)
    }

    private fun listeners(){
        changePassword.setOnClickListener(){
            val currentPassword = currentPasswordEditText.text.toString()
            val newPassword = newPasswordEditText.text.toString()

            if(newPassword == currentPassword){
                Toast.makeText(this, "Use Other Password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else if(newPassword.isEmpty() || currentPassword.isEmpty()){
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            firebaseAuth.currentUser?.updatePassword(newPassword)?.addOnCompleteListener(){ task ->
                if (task.isSuccessful){
                    Toast.makeText(this, "Password Updated", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }else {
                    Toast.makeText(this, "Something's Wrong", Toast.LENGTH_SHORT).show()
                }
            }
        }

        changePasswordGoBackButton.setOnClickListener(){
            startActivity(Intent(this, profile_fragment::class.java))
            finish()
        }
    }
}