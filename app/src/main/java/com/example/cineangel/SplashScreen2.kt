package com.example.cineangel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cineangel.databinding.ActivitySplashScreen2Binding

class SplashScreen2 : AppCompatActivity() {
    private lateinit var binding:ActivitySplashScreen2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreen2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            registerBtn.setOnClickListener {
                val intentregsiter = Intent(this@SplashScreen2, Login::class.java)
                startActivity(intentregsiter)
            }
        }
    }
}