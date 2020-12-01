package com.genadidharma.lafar.ui.restaurant.sections

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.genadidharma.lafar.data.MenuType
import com.genadidharma.lafar.data.Restaurant
import com.genadidharma.lafar.ui.restaurant.sections.menu.MenuFragment

class MenuSectionPagerAdapter(private val context: Context?,
                              fragmentManager: FragmentManager,
                              private val restaurant: Restaurant?,
                              private val menuType: MenuType?
) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int = MenuType.values().size

    private fun getItemType(position: Int): MenuType {
        return MenuType.values()[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context?.getString(getItemType(position).titleRes)
    }

    override fun getItem(position: Int): Fragment {
        return MenuFragment().newInstance(restaurant, menuType)
    }
}