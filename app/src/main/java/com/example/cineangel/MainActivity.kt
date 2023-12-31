package com.example.cineangel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cineangel.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            imageView2.alpha = 0f
            imageView2.animate().setDuration(1500).alpha(1f).withEndAction() {
                val intent = Intent(this@MainActivity, SplashScreen2::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}