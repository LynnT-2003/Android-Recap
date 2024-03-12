package com.example.androidrecap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidrecap.databinding.ActivityFragment2Binding
import com.example.androidrecap.fragments.FragmentA
import com.example.androidrecap.fragments.FragmentB
import com.example.androidrecap.fragments.FragmentC

class Fragment2Activity : AppCompatActivity() {
    private val view: ActivityFragment2Binding by lazy {
        ActivityFragment2Binding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)

        view.btnFragmentA.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainerView, FragmentA())
                .addToBackStack(FragmentA::class.java.name)
                .commit()
        }

        view.btnFragmentB.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainerView, FragmentB())
                .addToBackStack(FragmentB::class.java.name)
                .commit()
        }

        view.btnFragmentC.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainerView, FragmentC())
                .addToBackStack(FragmentB::class.java.name)
                .commit()
        }
    }
}