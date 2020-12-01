package com.genadidharma.lafar.ui.restaurant.sections

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.genadidharma.lafar.data.Menu
import com.genadidharma.lafar.data.MenuDiffCallback
import com.genadidharma.lafar.data.Restaurant
import com.genadidharma.lafar.databinding.ItemRestaurantMenuBinding

class RestaurantMenuAdapter(
        private val listener: RestaurantMenuAdapterListener
) : ListAdapter<Menu, RestaurantMenuAdapter.RestaurantMenuViewHolder>(MenuDiffCallback) {

    interface RestaurantMenuAdapterListener {
        fun onMenuClicked(cardView: View, menu: Menu)
        fun onMenuFavoriteClicked(menu: Menu): Boolean
    }

    inner class RestaurantMenuViewHolder(
            private val binding: ItemRestaurantMenuBinding,
            listener: RestaurantMenuAdapterListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(menu: Menu) {
            binding.menu = menu
        }

        init {
            binding.run {
                this.listener = listener
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantMenuViewHolder {
        return RestaurantMenuViewHolder(
                ItemRestaurantMenuBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                ),
                listener
        )
    }

    override fun onBindViewHolder(holder: RestaurantMenuViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}