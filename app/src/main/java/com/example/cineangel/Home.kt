package com.example.cineangel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cineangel.databinding.ActivityHomeBinding
import com.example.cineangel.databinding.ActivityLoginBinding

class Home : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val usn = intent.getStringExtra("usn")

        with(binding) {
            editTextUsername.text= usn
            spiderman.setOnClickListener {
                val intentview = Intent(this@Home, Details::class.java)
                startActivity(intentview)
            }
        }
    }
}