package com.example.cineangel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.cineangel.databinding.ActivityLayoutBinding

class Layout : AppCompatActivity() {
    private lateinit var binding: ActivityLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra("EXTUSERNAME")
        val bundle = Bundle()
        bundle.putString("EXTUSERNAME", username)

        replaceFragment(DashboardFragment())

        with(binding) {
            val dashboardFragment = DashboardFragment()
            dashboardFragment.arguments = bundle

            binding.bottomNavbar.setOnItemSelectedListener() {
                when(it.itemId){
                    R.id.itemDashboard -> replaceFragment(DashboardFragment())
                    R.id.itemSummarize -> replaceFragment(SummarizeFragment())
                    R.id.itemAccount -> replaceFragment(AccountFragment())

                    else -> {}
                }
                true
            }
        }
    }

    //untuk menggantikan fragmen yang ditampilkan di dalam tampilan yang memiliki id dengan nama
    // "frameLayout." Ini adalah bagian dari pengelolaan fragmen dalam Android.
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }

}