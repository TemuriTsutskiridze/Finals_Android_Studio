package com.example.finals_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {

    private lateinit var signupEmailEditText: EditText
    private lateinit var signupPasswordEditText: EditText
    private lateinit var signupRepeatPasswordEditText: EditText
    private lateinit var signupButton: TextView
    private lateinit var signupGoToLogin: TextView

    private val firebaseAuth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        

        init()
        signupListeners()
    }


    private fun init(){
        signupEmailEditText = findViewById(R.id.signupEmailEditText)
        signupPasswordEditText = findViewById(R.id.currentPasswordEditText)
        signupRepeatPasswordEditText = findViewById(R.id.newPasswordEditText)
        signupButton = findViewById(R.id.changePassword)
        signupGoToLogin = findViewById(R.id.signupGoToLogin)
    }

    private fun signupListeners() {
        signupPasswordEditText.setOnClickListener {
            signupPasswordEditText.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        }
        signupRepeatPasswordEditText.setOnClickListener {
            signupRepeatPasswordEditText.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        }
        signupButton.setOnClickListener {
            val email = signupEmailEditText.text.toString()
            val password = signupPasswordEditText.text.toString()
            val repeatPassword = signupRepeatPasswordEditText.text.toString()

            if(repeatPassword!=password){
                Toast.makeText(this, "Wrong Password!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            // checks for an uppercase letter in password
            var upperCase : Int = 0
            for(i in password){
                if(i.isUpperCase()){
                    upperCase++
                }
            }

            // checks for a digit in password
            var digit : Int = 0
            for(i in password){
                if(i.isDigit()){
                    digit++
                }
            }

            if (email.isEmpty() || password.isEmpty()
                || password.length < 8 || password.contains(' ') || digit == 0 || upperCase == 0 || repeatPassword.isEmpty()){
                Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful){
                    Toast.makeText(this, "congrats! now login!", Toast.LENGTH_SHORT).show()
                    goToLogin()
                } else {
                    Toast.makeText(this, "something's wrong, try again", Toast.LENGTH_SHORT).show()
                }
            }

        }
        signupGoToLogin.setOnClickListener {
            goToLogin()
        }
    }

    private fun goToLogin(){
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}