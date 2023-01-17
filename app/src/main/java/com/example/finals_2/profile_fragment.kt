package com.example.finals_2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment


class profile_fragment: Fragment(R.layout.fragment_profile_fragment) {
    lateinit var changePasswordButton: TextView


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        listeners()
    }

    private fun init(){
        changePasswordButton = requireView().findViewById(R.id.changePasswordButton)
    }

    private fun listeners(){
        changePasswordButton.setOnClickListener(){
            val i = Intent(activity, ProfileActivity::class.java)
            startActivity(i)
        }
    }
}