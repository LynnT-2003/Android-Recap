package com.example.androidrecap.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidrecap.R
import com.example.androidrecap.databinding.FragmentABinding


class FragmentA : Fragment() {
    private lateinit var view: FragmentABinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = FragmentABinding.inflate(inflater, container, false)
        return (view.root)
    }
}