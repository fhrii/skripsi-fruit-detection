package com.fahriaf.fruitdetection.core.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LinearMarginItemDecoration(
    private val spaceSize: Int,
    private val orientation: Int = GridLayoutManager.VERTICAL
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        with(outRect) {
            when (orientation) {
                LinearLayoutManager.VERTICAL -> bottom = spaceSize
                LinearLayoutManager.HORIZONTAL -> right = spaceSize
            }
        }
    }
}