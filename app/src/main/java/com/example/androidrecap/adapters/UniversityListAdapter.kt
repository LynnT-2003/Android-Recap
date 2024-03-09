package com.example.androidrecap.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidrecap.databinding.ItemUniversityBinding
import com.example.androidrecap.models.University


/*
1. Accept list of University Model Object in Adapter
2. Create ViewHolder (has only one value which is binding of the item to populate
3. Extend appropriately for Adapter and ViewHolder
 */
class UniversityListAdapter(private val universities: List<University>) :
    RecyclerView.Adapter<UniversityListAdapter.UniversityViewHolder>() {

    class UniversityViewHolder(val binding: ItemUniversityBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UniversityViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: UniversityViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}