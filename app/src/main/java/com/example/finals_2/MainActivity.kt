package com.example.finals_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.finals_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(home_fragment())

        binding.bottomNavigationView.setOnItemSelectedListener {

            when(it.itemId){

                R.id.home_item -> replaceFragment(home_fragment())
                R.id.tracker_item -> replaceFragment(tracker_fragment())
                R.id.profile_item -> replaceFragment(profile_fragment())

                else ->{


                }


            }
            true

        }
    }
    private fun replaceFragment(fragment : Fragment){

        val fragnentManager = supportFragmentManager
        val fragmentTransaction = fragnentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }
}