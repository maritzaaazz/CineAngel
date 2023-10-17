package com.example.cineangel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.cineangel.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            loginbtn.setOnClickListener {
                val username = editTextUsername.text.toString()
                val password = editTextPassword.text.toString()

                if (otentifikasi(username, password)) {
                    val intentLogin = Intent(this@Login, Home::class.java)
                    intentLogin.putExtra("usn", username)
                    startActivity(intentLogin)
                    finish()
                }
                else {
                    Toast.makeText(this@Login, "Username atau Password salah", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun otentifikasi(username: String, password: String): Boolean {
        val userOtentifikasi = listOf(
            Pair("maritzaaazz", "12345"),
            Pair("zaangelaz", "12345"),
        )
        for ((storedUsername, storedPassword) in userOtentifikasi) {
            if (username == storedUsername && password == storedPassword) {
                return true
            }
        }
        return false
    }
}