package com.genadidharma.lafar.ui.restaurant.sections.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.genadidharma.lafar.R
import com.genadidharma.lafar.data.Menu
import com.genadidharma.lafar.databinding.FragmentMenuDetailBinding
import com.genadidharma.lafar.ui.restaurant.ImageCarouselAdapter
import com.genadidharma.lafar.util.MarginItemDecorationVertical
import com.google.android.material.transition.MaterialSharedAxis
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType

class MenuDetailFragment : Fragment() {
    private val args: MenuDetailFragmentArgs by navArgs()
    private val menu: Menu by lazy(LazyThreadSafetyMode.NONE) { args.menu }

    private lateinit var binding: FragmentMenuDetailBinding

    private val menuCarouselAdapter = ImageCarouselAdapter()
    private val menuAddonAdapter = MenuAddonAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true).apply {
            duration = resources.getInteger(R.integer.lafar_motion_duration_large).toLong()
        }
        returnTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false).apply {
            duration = resources.getInteger(R.integer.lafar_motion_duration_large).toLong()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMenuDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.menu = menu

        initCarouselImages()
        initAddonAdapter()
    }

    private fun initCarouselImages() {
        binding.svImages.setIndicatorAnimation(IndicatorAnimationType.WORM)
        binding.svImages.setSliderAdapter(menuCarouselAdapter)
        menuCarouselAdapter.addItems(menu.images)
        binding.svImages.setInfiniteAdapterEnabled(true)
    }

    private fun initAddonAdapter() {
        binding.rvAddon.addItemDecoration(MarginItemDecorationVertical(
                resources.getDimension(R.dimen.md_margin_padding).toInt(),
                resources.getDimension(R.dimen.sm_margin_padding).toInt(),
                resources.getDimension(R.dimen.md_margin_padding).toInt(),
                resources.getDimension(R.dimen.sm_margin_padding).toInt(),
                resources.getDimension(R.dimen.md_margin_padding).toInt(),
        ))
        binding.rvAddon.adapter = menuAddonAdapter
        menuAddonAdapter.submitList(menu.addOns)
    }
}