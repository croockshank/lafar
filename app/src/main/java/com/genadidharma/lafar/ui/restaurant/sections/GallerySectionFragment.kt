package com.genadidharma.lafar.ui.restaurant.sections

import GalleryItem
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.genadidharma.lafar.R
import com.genadidharma.lafar.data.Asset
import com.genadidharma.lafar.data.GalleryType
import com.genadidharma.lafar.data.Restaurant
import com.genadidharma.lafar.databinding.FragmentGallerySectionBinding
import com.genadidharma.lafar.ui.restaurant.sections.gallery.GalleryAssetSectionAdapter
import com.genadidharma.lafar.util.MarginItemDecorationHorizontal

class GallerySectionFragment :
        Fragment(),
        GalleryAssetSectionAdapter.GalleryAssetSectionAdapterListener {

    companion object {
        val TAG = GallerySectionFragment::class.simpleName
        const val RESTAURANT_TAG = "restaurant"
    }

    private lateinit var binding: FragmentGallerySectionBinding

    private var restaurant: Restaurant? = null
    private val gallerySectionAdapter = GalleryAssetSectionAdapter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val bundle = this.arguments
        restaurant = bundle?.getParcelable(RESTAURANT_TAG)

        Log.i(TAG, "Restaurant: $restaurant")

        binding = FragmentGallerySectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initGallerySection()
        initPostinganTemanSection()
    }

    private fun initGallerySection() {
        binding.rvAlbum.adapter = gallerySectionAdapter
        binding.rvAlbum.addItemDecoration(MarginItemDecorationHorizontal(
                resources.getDimension(R.dimen.md_margin_padding).toInt(),
                0,
                resources.getDimension(R.dimen.sm_margin_padding).toInt(),
                0,
                resources.getDimension(R.dimen.sm_margin_padding).toInt()
        ))
        GalleryItem.getGalleries()?.observe(viewLifecycleOwner) {
            gallerySectionAdapter.submitList(it)
        }
    }

    private fun initPostinganTemanSection() {
        binding.rvPostinganTeman.adapter = gallerySectionAdapter
        binding.rvPostinganTeman.addItemDecoration(MarginItemDecorationHorizontal(
                resources.getDimension(R.dimen.md_margin_padding).toInt(),
                0,
                resources.getDimension(R.dimen.sm_margin_padding).toInt(),
                0,
                resources.getDimension(R.dimen.sm_margin_padding).toInt()
        ))
        GalleryItem.getGalleries()?.observe(viewLifecycleOwner) {
            gallerySectionAdapter.submitList(it)

            Log.i(TAG, "Galleries item: $it")
        }
    }

    fun newInstance(restaurant: Restaurant?): Fragment {
        val fragment = GallerySectionFragment()
        val bundle = Bundle()
        bundle.putParcelable(MenuSectionFragment.RESTAURANT_TAG, restaurant)
        fragment.arguments = bundle
        return fragment
    }

    override fun onAssetClick(cardView: View, asset: Asset) {
        TODO("Not yet implemented")
    }
}