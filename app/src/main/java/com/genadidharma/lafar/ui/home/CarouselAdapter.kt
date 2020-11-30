package com.genadidharma.lafar.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.genadidharma.lafar.data.Promotion
import com.genadidharma.lafar.databinding.ItemCarouselSliderBinding
import com.smarteist.autoimageslider.SliderViewAdapter


class CarouselAdapter : SliderViewAdapter<CarouselAdapter.SliderAdapterViewHolder>() {
    private var mSliderItems: List<Promotion> = mutableListOf()

    fun addItems(sliderItems: List<Promotion>) {
        mSliderItems = sliderItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterViewHolder {
        return SliderAdapterViewHolder(
                ItemCarouselSliderBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterViewHolder, position: Int) {
        viewHolder.bind(mSliderItems[position])
    }

    override fun getCount(): Int {
        return mSliderItems.size
    }

    inner class SliderAdapterViewHolder(private val binding: ItemCarouselSliderBinding) : ViewHolder(binding.root) {
        fun bind(promotion: Promotion) {
            binding.promotion = promotion
        }
    }

}