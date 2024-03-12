package com.example.androidrecap.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidrecap.R
import com.example.androidrecap.databinding.FragmentBBinding

class FragmentB : Fragment() {
    private lateinit var view: FragmentBBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = FragmentBBinding.inflate(inflater, container, false)
        return (view.root)
    }
}