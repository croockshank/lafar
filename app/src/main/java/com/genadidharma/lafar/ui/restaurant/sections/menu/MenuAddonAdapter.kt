package com.genadidharma.lafar.ui.restaurant.sections.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.genadidharma.lafar.data.AddOn
import com.genadidharma.lafar.data.AddonDiffCallback
import com.genadidharma.lafar.databinding.ItemMenuAddonBinding

class MenuAddonAdapter : ListAdapter<AddOn, MenuAddonAdapter.MenuAddonAdapterViewHolder>(AddonDiffCallback) {

    inner class MenuAddonAdapterViewHolder(
            private val binding: ItemMenuAddonBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(addOn: AddOn) {
            binding.addon = addOn
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuAddonAdapterViewHolder {
        return MenuAddonAdapterViewHolder(
                ItemMenuAddonBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: MenuAddonAdapterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}