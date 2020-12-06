package com.genadidharma.lafar.ui.restaurant.sections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.genadidharma.lafar.data.MenuType
import com.genadidharma.lafar.data.Restaurant
import com.genadidharma.lafar.databinding.FragmentMenuSectionBinding
import com.google.android.material.tabs.TabLayout


class MenuSectionFragment : Fragment(),
        TabLayout.OnTabSelectedListener {

    companion object {
        val TAG = MenuSectionFragment::class.simpleName
        const val RESTAURANT_TAG = "restaurant"
        lateinit var viewModel: MenuSectionViewModel
    }

    private lateinit var binding: FragmentMenuSectionBinding
    private lateinit var sectionPagerAdapter: MenuSectionPagerAdapter

    private var restaurant: Restaurant? = null
    private var menuType: MenuType? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val bundle = this.arguments
        restaurant = bundle?.getParcelable(RESTAURANT_TAG)

        binding = FragmentMenuSectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tlType.addOnTabSelectedListener(this)

        initSectionTabLayout(view)
    }

    private fun initSectionTabLayout(view: View?) {
        binding.tlType.setupWithViewPager(binding.vpMenu)
        sectionPagerAdapter = MenuSectionPagerAdapter(view?.context, childFragmentManager, restaurant, menuType)
        binding.vpMenu.adapter = sectionPagerAdapter
    }

    fun newInstance(restaurant: Restaurant?): Fragment {
        val fragment = MenuSectionFragment()
        val bundle = Bundle()
        bundle.putParcelable(RESTAURANT_TAG, restaurant)
        fragment.arguments = bundle
        return fragment
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        menuType = MenuType.values().find {
            resources.getString(it.titleRes) == tab?.text
        }

        val factory = MenuSectionViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(MenuSectionViewModel::class.java).apply {
            getMenus(restaurant, menuType)
        }
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {

    }

    override fun onTabReselected(tab: TabLayout.Tab?) {

    }

}