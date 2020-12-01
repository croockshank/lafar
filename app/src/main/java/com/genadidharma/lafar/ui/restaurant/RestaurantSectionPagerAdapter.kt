package com.genadidharma.lafar.ui.restaurant

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.genadidharma.lafar.R
import com.genadidharma.lafar.data.Restaurant
import com.genadidharma.lafar.ui.restaurant.sections.GallerySectionFragment
import com.genadidharma.lafar.ui.restaurant.sections.MenuSectionFragment
import com.genadidharma.lafar.ui.restaurant.sections.ReviewSectionFragment

class RestaurantSectionPagerAdapter(private val context: Context,
                                    fragmentManager: FragmentManager,
                                    private val restaurant: Restaurant
) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    enum class SectionFragments(val titleRes: Int, @DrawableRes val image: Int) {
        MENU(R.string.tab_title_menu, R.drawable.ic_section_menu),
        GALLERY(R.string.tab_title_gallery, R.drawable.ic_section_gallery),
        REVIEW(R.string.tab_title_review, R.drawable.ic_section_review)
    }

    override fun getCount(): Int = SectionFragments.values().size

    private fun getItemType(position: Int): SectionFragments {
        return SectionFragments.values()[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.getString(getItemType(position).titleRes)
    }

    override fun getItem(position: Int): Fragment {
        return when (getItemType(position)) {
            SectionFragments.MENU -> MenuSectionFragment().newInstance(restaurant)
            SectionFragments.GALLERY -> GallerySectionFragment()
            SectionFragments.REVIEW -> ReviewSectionFragment()
        }
    }
}