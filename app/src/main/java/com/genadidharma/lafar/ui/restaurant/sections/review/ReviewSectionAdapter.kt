package com.genadidharma.lafar.ui.restaurant.sections.review

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.genadidharma.lafar.data.Review
import com.genadidharma.lafar.data.ReviewDiffCallback
import com.genadidharma.lafar.databinding.ItemReviewCardBinding

class ReviewSectionAdapter(
        private val listener: ReviewSectionAdapterListener
) : ListAdapter<Review, ReviewSectionAdapter.ReviewSectionAdapterViewHolder>(ReviewDiffCallback) {
    interface ReviewSectionAdapterListener {
        fun onReviewClick(cardView: View, review: Review)
        fun onAddCommentClick(review: Review)
    }

    inner class ReviewSectionAdapterViewHolder(
            private val binding: ItemReviewCardBinding,
            listener: ReviewSectionAdapterListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(review: Review) {
            binding.review = review
        }

        init {
            binding.run {
                this.listener = listener
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewSectionAdapterViewHolder {
        return ReviewSectionAdapterViewHolder(
                ItemReviewCardBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                ),
                listener
        )
    }

    override fun onBindViewHolder(holder: ReviewSectionAdapterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}