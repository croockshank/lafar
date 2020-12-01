package com.genadidharma.lafar.ui.restaurant.sections.menu

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.genadidharma.lafar.R
import com.genadidharma.lafar.data.Menu
import com.genadidharma.lafar.data.MenuType
import com.genadidharma.lafar.data.Restaurant
import com.genadidharma.lafar.data.RestaurantMenuItem
import com.genadidharma.lafar.databinding.FragmentMenuBinding
import com.genadidharma.lafar.ui.restaurant.sections.MenuSectionFragment
import com.genadidharma.lafar.ui.restaurant.sections.RestaurantMenuAdapter
import com.genadidharma.lafar.util.MarginItemDecorationVertical
import com.google.android.material.transition.MaterialElevationScale


class MenuFragment : Fragment(),
        RestaurantMenuAdapter.RestaurantMenuAdapterListener {

    companion object {
        const val MENU_TYPE = "menu"
        val TAG = MenuFragment::class.simpleName
    }

    private lateinit var binding: FragmentMenuBinding
    private val menuAdapter = RestaurantMenuAdapter(this)

    private var restaurant: Restaurant? = null
    private var menuType: MenuType? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val bundle = this.arguments
        restaurant = bundle?.getParcelable(MenuSectionFragment.RESTAURANT_TAG)
        menuType = bundle?.getSerializable(MENU_TYPE) as MenuType?

        Log.i(MenuSectionFragment.TAG, "Resto: ${restaurant?.title}, MenuType: $menuType")

        binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMenuList()
    }

    private fun initMenuList() {
        binding.rvMenu.adapter = menuAdapter
        binding.rvMenu.addItemDecoration(MarginItemDecorationVertical(
                resources.getDimensionPixelSize(R.dimen.md_margin_padding),
                resources.getDimensionPixelSize(R.dimen.sm_margin_padding),
                resources.getDimensionPixelSize(R.dimen.md_margin_padding),
                resources.getDimensionPixelSize(R.dimen.sm_margin_padding),
                resources.getDimensionPixelSize(R.dimen.md_margin_padding)
        ))
        RestaurantMenuItem.getMenus(restaurant, MenuType.BEST_SELLER).observe(viewLifecycleOwner) {
            menuAdapter.submitList(it)
        }
    }

    override fun onMenuClicked(cardView: View, menu: Menu) {
        exitTransition = MaterialElevationScale(false).apply {
            duration = resources.getInteger(R.integer.lafar_motion_duration_large).toLong()
        }
        reenterTransition = MaterialElevationScale(true).apply {
            duration = resources.getInteger(R.integer.lafar_motion_duration_large).toLong()
        }
        val menuCardDetailTransitionName = getString(R.string.menu_to_detail_transition_name)
        val extras = FragmentNavigatorExtras(cardView to menuCardDetailTransitionName)
        val directions = MenuFragmentDirections.actionMenuFragmentToMenuDetailFragment(menu)
        findNavController().navigate(directions, extras)
    }

    override fun onMenuFavoriteClicked(menu: Menu): Boolean {
        TODO("Not yet implemented")
    }

    fun newInstance(restaurant: Restaurant?, menuType: MenuType?): Fragment {
        val fragment = MenuFragment()
        val bundle = Bundle()
        bundle.putParcelable(MenuSectionFragment.RESTAURANT_TAG, restaurant)
        bundle.putSerializable(MENU_TYPE, menuType)
        fragment.arguments = bundle
        return fragment
    }
}