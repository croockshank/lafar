package com.genadidharma.lafar.ui.restaurant

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.genadidharma.lafar.R
import com.genadidharma.lafar.data.Restaurant
import com.genadidharma.lafar.databinding.FragmentRestaurantBinding
import com.genadidharma.lafar.databinding.ItemRestaurantFeatureChipBinding
import com.genadidharma.lafar.ui.home.FriendsAttachmentAdapter
import com.genadidharma.lafar.util.MarginItemDecorationHorizontal
import com.genadidharma.lafar.util.themeColor
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialElevationScale
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType

class RestaurantFragment : Fragment() {
    private val args: RestaurantFragmentArgs by navArgs()
    private val restaurant: Restaurant by lazy(LazyThreadSafetyMode.NONE) { args.restaurant }

    private lateinit var binding: FragmentRestaurantBinding
    private lateinit var sectionPagerAdapter: RestaurantSectionPagerAdapter
    private val restaurantCarouselAdapter = ImageCarouselAdapter()
    private val friendsAttachmentAdapter = FriendsAttachmentAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.nav_host_fragment
            duration = resources.getInteger(R.integer.lafar_motion_duration_large).toLong()
            scrimColor = Color.TRANSPARENT
            setAllContainerColors(requireContext().themeColor(R.attr.colorSurface))
        }
        reenterTransition = MaterialElevationScale(true).apply {
            duration = resources.getInteger(R.integer.lafar_motion_duration_large).toLong()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentRestaurantBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.restaurant = restaurant

        initCarouselImages()
        initFeatureChips()
        initSectionTabLayout(view)
    }

    private fun initFeatureChips() {
        binding.cgFeatures.run {
            restaurant.features.forEach {
                val chipBinding = ItemRestaurantFeatureChipBinding.inflate(
                        LayoutInflater.from(context),
                        this,
                        false
                ).apply {
                    features = it
                }
                addView(chipBinding.root)
            }
        }
    }

    private fun initSectionTabLayout(view: View) {
        binding.tlSection.setupWithViewPager(binding.vpSection)
        sectionPagerAdapter = RestaurantSectionPagerAdapter(view.context, childFragmentManager, restaurant)
        binding.vpSection.adapter = sectionPagerAdapter

        binding.tlSection.getTabAt(0)?.setIcon(RestaurantSectionPagerAdapter.SectionFragments.MENU.image)
        binding.tlSection.getTabAt(1)?.setIcon(RestaurantSectionPagerAdapter.SectionFragments.GALLERY.image)
        binding.tlSection.getTabAt(2)?.setIcon(RestaurantSectionPagerAdapter.SectionFragments.REVIEW.image)
    }

    private fun initCarouselImages() {
        binding.svImages.setIndicatorAnimation(IndicatorAnimationType.WORM)
        binding.svImages.setSliderAdapter(restaurantCarouselAdapter)
        restaurantCarouselAdapter.addItems(restaurant.images)
        binding.svImages.setInfiniteAdapterEnabled(true)
        initFriendsAttachmentAdapter()
    }

    private fun initFriendsAttachmentAdapter() {
        binding.rvRelatedFriends.addItemDecoration(MarginItemDecorationHorizontal(
                0,
                0,
                resources.getDimensionPixelSize(R.dimen.sm_margin_padding),
                0,
                0
        ))
        binding.rvRelatedFriends.adapter = friendsAttachmentAdapter
        friendsAttachmentAdapter.submitList(restaurant.friends)
    }
}