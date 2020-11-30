package com.genadidharma.lafar.util

import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecorationHorizontal(private val startAndEnd: Int, private val topSize: Int, private val rightSize: Int, private val bottomSize: Int, private val leftSize: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View,
                                parent: RecyclerView, state: RecyclerView.State) {

        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) {
                this.top = topSize
            }
            bottom = bottomSize
        }

        val itemPosition = parent.getChildAdapterPosition(view)
        val itemCount = state.itemCount

        if (itemPosition == RecyclerView.NO_POSITION) {
            return
        }
        when (itemPosition) {
            0 -> {
                with(outRect) {
                    left = startAndEnd
                    right = rightSize
                }
            }
            itemCount - 1 -> {
                with(outRect) {
                    left = leftSize
                    right = startAndEnd
                }
            }
            else -> {
                with(outRect){
                    left = leftSize
                    right = rightSize
                }
            }
        }
    }
}