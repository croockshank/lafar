package com.genadidharma.lafar.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.genadidharma.lafar.R
import com.genadidharma.lafar.data.*
import com.genadidharma.lafar.databinding.FragmentHomeBinding
import com.genadidharma.lafar.databinding.ItemCuisineChipBinding
import com.genadidharma.lafar.util.MarginItemDecorationHorizontal
import com.google.android.material.chip.ChipGroup
import com.google.android.material.transition.MaterialElevationScale
import com.google.android.material.transition.MaterialFadeThrough
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType

class HomeFragment :
        Fragment(),
        ChipGroup.OnCheckedChangeListener,
        RestaurantAdapter.RestaurantAdapterListener {

    companion object {
        val TAG = HomeFragment::class.simpleName
    }

    private lateinit var binding: FragmentHomeBinding

    private var checkedCuisine: Cuisine? = null

    private val sliderAdapter = CarouselAdapter()
    private val restaurantBgAdapter = RestaurantAdapter(this)
    private val restaurantMdAdapter = RestaurantAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialFadeThrough().apply {
            duration = resources.getInteger(R.integer.lafar_motion_duration_large).toLong()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPromotionCarousel()
        initCuisineChips()

        binding.cgCuisine.setOnCheckedChangeListener(this)
        initYangLagiNgetrendSection()
        initTempatnyaInstagrammable()
    }

    private fun initPromotionCarousel() {
        binding.svCarousel.setIndicatorAnimation(IndicatorAnimationType.WORM)
        binding.svCarousel.setSliderAdapter(sliderAdapter)
        PromotionItem.getPromotions().observe(viewLifecycleOwner) {
            sliderAdapter.addItems(it)
        }
    }

    @SuppressLint("ResourceType")
    private fun initCuisineChips() {
        binding.cgCuisine.run {
            CuisineItem.getCuisines().observe(viewLifecycleOwner) { cuisines ->
                cuisines.forEach {
                    val chipBinding = ItemCuisineChipBinding.inflate(
                            LayoutInflater.from(context),
                            this,
                            false
                    ).apply {
                        cuisine = it
                    }
                    addView(chipBinding.root)
                    chipBinding.root.id = it.key.id
                }
            }
        }
    }

    private fun initYangLagiNgetrendSection() {
        binding.rvLagiNgetrendBanget.adapter = restaurantBgAdapter
        binding.rvLagiNgetrendBanget.addItemDecoration(MarginItemDecorationHorizontal(
                resources.getDimension(R.dimen.md_margin_padding).toInt(),
                0,
                resources.getDimension(R.dimen.sm_margin_padding).toInt(),
                0,
                resources.getDimension(R.dimen.sm_margin_padding).toInt()
        ))
    }

    private fun initTempatnyaInstagrammable() {
        binding.rvTempatnyaInstagrammable.adapter = restaurantMdAdapter
        binding.rvTempatnyaInstagrammable.addItemDecoration(MarginItemDecorationHorizontal(
                resources.getDimension(R.dimen.md_margin_padding).toInt(),
                0,
                resources.getDimension(R.dimen.sm_margin_padding).toInt(),
                0,
                resources.getDimension(R.dimen.sm_margin_padding).toInt()
        ))
    }

    override fun onCheckedChanged(group: ChipGroup?, checkedId: Int) {
        checkedCuisine = CuisineItem.getCuisine(checkedId)

        restaurantBgAdapter.submitList(null)
        restaurantMdAdapter.submitList(null)

        RestaurantItem.getRestaurants(RestaurantSection.LAGI_NGETREND_BANGET, checkedCuisine).observe(viewLifecycleOwner) {
            restaurantBgAdapter.submitList(it)
        }

        RestaurantItem.getRestaurants(RestaurantSection.TEMPATNYA_INSTAGRAMMABLE, checkedCuisine).observe(viewLifecycleOwner) {
            restaurantMdAdapter.submitList(it)
        }
    }

    override fun onRestaurantClicked(cardView: View, restaurant: Restaurant) {
        exitTransition = MaterialElevationScale(false).apply {
            duration = resources.getInteger(R.integer.lafar_motion_duration_large).toLong()
        }
        reenterTransition = MaterialElevationScale(true).apply {
            duration = resources.getInteger(R.integer.lafar_motion_duration_large).toLong()
        }
        val restaurantCardDetailTransitionName = getString(R.string.home_to_restaurant_detail_transition)
        val extras = FragmentNavigatorExtras(cardView to restaurantCardDetailTransitionName)
        val directions = HomeFragmentDirections.actionHomeFragmentToRestaurantFragment(restaurant)
        findNavController().navigate(directions, extras)
    }

    override fun onRestaurantFavoriteClicked(restaurant: Restaurant): Boolean {
        TODO("Not yet implemented")
    }
}