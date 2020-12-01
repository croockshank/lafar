package com.genadidharma.lafar.ui.restaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import com.genadidharma.lafar.databinding.ItemCarouselImagesBinding
import com.smarteist.autoimageslider.SliderViewAdapter


class ImageCarouselAdapter : SliderViewAdapter<ImageCarouselAdapter.SliderAdapterViewHolder>() {
    @DrawableRes
    private var mSliderItems: List<Int> = mutableListOf()

    fun addItems(sliderItems: List<Int>) {
        mSliderItems = sliderItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterViewHolder {
        return SliderAdapterViewHolder(
                ItemCarouselImagesBinding.inflate(
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

    inner class SliderAdapterViewHolder(private val binding: ItemCarouselImagesBinding) : ViewHolder(binding.root) {
        fun bind(@DrawableRes image: Int) {
            binding.image = image
        }
    }

}