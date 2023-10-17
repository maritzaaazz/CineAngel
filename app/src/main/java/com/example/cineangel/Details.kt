package com.example.cineangel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cineangel.databinding.ActivityDetailsBinding
import com.example.cineangel.databinding.ActivityHomeBinding

class Details : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            back.setOnClickListener {
                val intentDetails = Intent(this@Details, Home::class.java)
                startActivity(intentDetails)
            }

            getticketbtn.setOnClickListener {
                val intentDetails2 = Intent(this@Details, PaymentTicket::class.java)
                startActivity(intentDetails2)
            }
        }
    }
}