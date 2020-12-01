package com.genadidharma.lafar.ui.restaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.genadidharma.lafar.R
import com.genadidharma.lafar.data.Restaurant
import com.genadidharma.lafar.databinding.*
import com.genadidharma.lafar.ui.home.FriendsAttachmentAdapter
import com.genadidharma.lafar.util.MarginItemDecorationHorizontal
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType

class RestaurantFragment : Fragment() {
    private val args: RestaurantFragmentArgs by navArgs()
    private val restaurant: Restaurant by lazy(LazyThreadSafetyMode.NONE) { args.restaurant }

    private lateinit var binding: FragmentRestaurantBinding
    private lateinit var sectionPagerAdapter: RestaurantSectionPagerAdapter
    private val restaurantCarouselAdapter = ImageCarouselAdapter()
    private val friendsAttachmentAdapter = FriendsAttachmentAdapter()

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
        initFriendsAttachmentAdapter()
    }

    private fun initFriendsAttachmentAdapter() {
        binding.rvRelatedFriends.addItemDecoration(MarginItemDecorationHorizontal(
                0,
                0,
                resources.getDimensionPixelSize(R.dimen.sm_margin_padding)
        ))
        binding.rvRelatedFriends.adapter = friendsAttachmentAdapter
        friendsAttachmentAdapter.submitList(restaurant.friends)
    }
}