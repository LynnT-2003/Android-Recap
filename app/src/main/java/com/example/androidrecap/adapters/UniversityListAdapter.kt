package com.example.androidrecap.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidrecap.databinding.ItemUniversityBinding
import com.example.androidrecap.models.University


/*
1. Accept list of University Model Object in Adapter
2. Create ViewHolder (has only one value which is binding of the item to populate)
3. Extend appropriately for Adapter and ViewHolder
 */

class UniversityListAdapter(private val universities: List<University>) :
    RecyclerView.Adapter<UniversityListAdapter.UniversityViewHolder>() {

    class UniversityViewHolder(val binding: ItemUniversityBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UniversityViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemUniversityBinding.inflate(layoutInflater, parent, false)
        return UniversityViewHolder(binding)
    }

    override fun getItemCount(): Int = universities.size

    override fun onBindViewHolder(holder: UniversityViewHolder, position: Int) {
        // assign data into viewHolder in this function
        val university = universities[position]

        holder.binding.tvUniversityName.text = university.name
        holder.binding.tvUniversityProvince.text = university.`state-province`
    }
}