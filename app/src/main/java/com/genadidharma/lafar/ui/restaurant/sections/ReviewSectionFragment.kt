package com.genadidharma.lafar.ui.restaurant.sections

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.genadidharma.lafar.R
import com.genadidharma.lafar.data.Restaurant
import com.genadidharma.lafar.data.Review
import com.genadidharma.lafar.data.ReviewItem
import com.genadidharma.lafar.databinding.FragmentReviewSectionBinding
import com.genadidharma.lafar.ui.restaurant.sections.review.ReviewSectionAdapter
import com.genadidharma.lafar.util.MarginItemDecorationHorizontal

class ReviewSectionFragment :
        Fragment(),
        ReviewSectionAdapter.ReviewSectionAdapterListener {

    companion object {
        val TAG = ReviewSectionFragment::class.simpleName
        const val RESTAURANT_TAG = "restaurant"
    }

    private lateinit var binding: FragmentReviewSectionBinding

    private var restaurant: Restaurant? = null
    private val ulasanTerakhirSectionAdapter = ReviewSectionAdapter(this)
    private val ulasanTemanSectionAdapter = ReviewSectionAdapter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bundle = this.arguments
        restaurant = bundle?.getParcelable(GallerySectionFragment.RESTAURANT_TAG)

        binding = FragmentReviewSectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUlasanTerakhirSection()
        initUlasanTemanSection()
    }

    fun initUlasanTerakhirSection() {
        binding.rvUlasanTerakhir.adapter = ulasanTerakhirSectionAdapter
        binding.rvUlasanTerakhir.addItemDecoration(MarginItemDecorationHorizontal(
                resources.getDimension(R.dimen.md_margin_padding).toInt(),
                0,
                resources.getDimension(R.dimen.sm_margin_padding).toInt(),
                0,
                resources.getDimension(R.dimen.sm_margin_padding).toInt()
        ))
        ReviewItem.getReviews().observe(viewLifecycleOwner) {
            ulasanTerakhirSectionAdapter.submitList(it)

            Log.i(TAG, "Review: $it")
        }
    }

    fun initUlasanTemanSection() {
        binding.rvUlasanTeman.adapter = ulasanTemanSectionAdapter
        binding.rvUlasanTeman.addItemDecoration(MarginItemDecorationHorizontal(
                resources.getDimension(R.dimen.md_margin_padding).toInt(),
                0,
                resources.getDimension(R.dimen.sm_margin_padding).toInt(),
                0,
                resources.getDimension(R.dimen.sm_margin_padding).toInt()
        ))
        ReviewItem.getReviews().observe(viewLifecycleOwner) {
            ulasanTemanSectionAdapter.submitList(it)
        }
    }

    fun newInstance(restaurant: Restaurant): ReviewSectionFragment {
        val fragment = ReviewSectionFragment()
        val bundle = Bundle()
        bundle.putParcelable(RESTAURANT_TAG, restaurant)
        fragment.arguments = bundle
        return fragment
    }

    override fun onReviewClick(cardView: View, review: Review) {
        TODO("Not yet implemented")
    }

    override fun onAddCommentClick(review: Review) {
        TODO("Not yet implemented")
    }
}