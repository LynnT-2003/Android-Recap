package com.example.androidrecap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.androidrecap.databinding.ActivityBottomNavigationBinding
import com.example.androidrecap.fragments.FragmentA
import com.example.androidrecap.fragments.FragmentB
import com.example.androidrecap.fragments.FragmentC

class BottomNavigationActivity : AppCompatActivity() {
    private val view: ActivityBottomNavigationBinding by lazy {
        ActivityBottomNavigationBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)

        view.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.item_home -> changeFragment(FragmentA())
                R.id.item_profile -> changeFragment(FragmentB())
                R.id.item_settings -> changeFragment(FragmentC())
                else -> false
            }
        }
    }

    private fun changeFragment(fragment: Fragment): Boolean {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView1, fragment)
            .addToBackStack(fragment::class.java.name)
            .commit()
        return true
    }
}