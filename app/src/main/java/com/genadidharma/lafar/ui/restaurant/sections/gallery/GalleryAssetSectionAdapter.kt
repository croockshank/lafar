package com.genadidharma.lafar.ui.restaurant.sections.gallery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.genadidharma.lafar.data.Asset
import com.genadidharma.lafar.data.AssetDiffCallback
import com.genadidharma.lafar.data.GalleryType
import com.genadidharma.lafar.databinding.ItemGalleryAssetLgBinding
import com.genadidharma.lafar.databinding.ItemGalleryAssetMdBinding

class GalleryAssetSectionAdapter(
        private val listener: GalleryAssetSectionAdapterListener
) : ListAdapter<Asset, RecyclerView.ViewHolder>(AssetDiffCallback) {

    companion object {
        val TAG = GalleryAssetSectionAdapter::class.simpleName
        const val TYPE_LARGE = 1
        const val TYPE_MEDIUM = 2
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).galleryType) {
            GalleryType.ALBUM -> {
                TYPE_MEDIUM
            }
            GalleryType.POSTINGAN_TEMAN -> {
                TYPE_LARGE
            }
        }
    }

    interface GalleryAssetSectionAdapterListener {
        fun onAssetClick(cardView: View, asset: Asset)
    }

    inner class GalleryAssetMdSectionAdapter(
            private val binding: ItemGalleryAssetMdBinding,
            listener: GalleryAssetSectionAdapterListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(asset: Asset) {
            binding.asset = asset
        }

        init {
            binding.run {
                this.listener = listener
            }

        }
    }

    inner class GalleryAssetLgSectionAdapter(
            private val binding: ItemGalleryAssetLgBinding,
            listener: GalleryAssetSectionAdapterListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(asset: Asset) {
            binding.asset = asset
        }

        init {
            binding.run {
                this.listener = listener
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_MEDIUM -> {
                GalleryAssetMdSectionAdapter(
                        ItemGalleryAssetMdBinding.inflate(
                                LayoutInflater.from(parent.context),
                                parent,
                                false
                        ),
                        listener
                )
            }
            else -> {
                GalleryAssetLgSectionAdapter(
                        ItemGalleryAssetLgBinding.inflate(
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
        when (getItemViewType(position)) {
            TYPE_MEDIUM -> {
                (holder as GalleryAssetSectionAdapter.GalleryAssetMdSectionAdapter).bind(getItem(position))
            }
            TYPE_LARGE -> {
                (holder as GalleryAssetSectionAdapter.GalleryAssetLgSectionAdapter).bind(getItem(position))
            }
        }
    }
}