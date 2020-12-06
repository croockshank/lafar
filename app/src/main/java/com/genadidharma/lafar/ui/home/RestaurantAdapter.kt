package com.genadidharma.lafar.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.genadidharma.lafar.R
import com.genadidharma.lafar.data.Restaurant
import com.genadidharma.lafar.data.RestaurantDiffCallback
import com.genadidharma.lafar.data.RestaurantSection
import com.genadidharma.lafar.databinding.ItemLgRestaurantCardBinding
import com.genadidharma.lafar.databinding.ItemMdRestaurantCardBinding
import com.genadidharma.lafar.databinding.ItemRestaurantTypeChipBinding
import com.genadidharma.lafar.util.MarginItemDecorationHorizontal

class RestaurantAdapter(
        private val listener: RestaurantAdapterListener
) : ListAdapter<Restaurant, RecyclerView.ViewHolder>(RestaurantDiffCallback) {

    companion object{
        const val TYPE_LARGE = 1
        const val TYPE_MEDIUM = 2
    }

    val attachmentAdapter = FriendsAttachmentAdapter()

    interface RestaurantAdapterListener {
        fun onRestaurantClicked(cardView: View, restaurant: Restaurant)
        fun onRestaurantFavoriteClicked(restaurant: Restaurant): Boolean
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position).section){
            RestaurantSection.LAGI_NGETREND_BANGET -> {
                TYPE_LARGE
            }
            RestaurantSection.TEMPATNYA_INSTAGRAMMABLE -> {
                TYPE_MEDIUM
            }
        }
    }

    inner class RestaurantBgAdapterViewHolder(
            private val binding: ItemLgRestaurantCardBinding,
            listener: RestaurantAdapterListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(restaurant: Restaurant) {
            binding.restaurant = restaurant
            binding.cgType.removeAllViews()
            binding.cgType.run {
                binding.restaurant?.types?.forEach {
                    val chipBinding = ItemRestaurantTypeChipBinding.inflate(
                            LayoutInflater.from(context),
                            this,
                            false
                    ).apply {
                        type = it
                    }
                    addView(chipBinding.root)
                }
            }
            attachmentAdapter.submitList(restaurant.friends)
        }

        init {
            binding.run {
                this.listener = listener
                this.rvRelatedFriends.addItemDecoration(MarginItemDecorationHorizontal(
                        0,
                        0,
                        binding.root.context.resources.getDimensionPixelSize(R.dimen.sm_margin_padding),
                        0,
                        0
                ))
                this.rvRelatedFriends.adapter = attachmentAdapter
            }

        }
    }

    inner class RestaurantMdAdapterViewHolder(
            private val binding: ItemMdRestaurantCardBinding,
            listener: RestaurantAdapterListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(restaurant: Restaurant) {
            binding.restaurant = restaurant
        }

        init {
            binding.run {
                this.listener = listener
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_LARGE -> {
                RestaurantBgAdapterViewHolder(
                        ItemLgRestaurantCardBinding.inflate(
                                LayoutInflater.from(parent.context),
                                parent,
                                false
                        ),
                        listener
                )
            }
            else -> {
                RestaurantMdAdapterViewHolder(
                        ItemMdRestaurantCardBinding.inflate(
                                LayoutInflater.from(parent.context),
                                parent,
                                false
                        ),
                        listener
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)){
            TYPE_LARGE -> {
                (holder as RestaurantBgAdapterViewHolder).bind(getItem(position))
            }
            TYPE_MEDIUM -> {
                (holder as RestaurantMdAdapterViewHolder).bind(getItem(position))
            }
        }
    }

}