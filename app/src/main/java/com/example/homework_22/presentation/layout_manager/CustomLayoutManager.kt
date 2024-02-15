package com.example.homework_22.presentation.layout_manager

import androidx.recyclerview.widget.RecyclerView

class CustomLayoutManager() : RecyclerView.LayoutManager() {

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.WRAP_CONTENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State) {
        if (itemCount == 0) {
            detachAndScrapAttachedViews(recycler)
            return
        }

        detachAndScrapAttachedViews(recycler)

        val totalWidth = width - paddingLeft - paddingRight
        val totalHeight = height - paddingTop - paddingBottom
        var offsetX = 0
        var offsetY = 0

        val itemHeight = totalHeight / 2

        for (i in 0 until itemCount) {
            val view = recycler.getViewForPosition(i)
            measureChildWithMargins(view, 0, 0)
            addView(view)

            when (itemCount) {
                1 -> {
                    layoutDecoratedWithMargins(
                        view,
                        paddingLeft,
                        paddingTop,
                        paddingLeft + totalWidth,
                        paddingTop + totalHeight
                    )
                }

                2 -> {
                    val halfWidth = totalWidth / 2
                    layoutDecoratedWithMargins(
                        view,
                        paddingLeft + offsetX,
                        paddingTop,
                        paddingLeft + offsetX + halfWidth,
                        paddingTop + totalHeight
                    )
                    offsetX += halfWidth
                }

                else -> {
                    if (i % 3 == 0) {
                        layoutDecoratedWithMargins(
                            view,
                            paddingLeft,
                            paddingTop + offsetY,
                            paddingLeft + totalWidth / 2,
                            paddingTop + offsetY + totalHeight
                        )
                        offsetX = totalWidth / 2
                    } else if (i % 3 == 1) {
                        layoutDecoratedWithMargins(
                            view,
                            paddingLeft + offsetX,
                            paddingTop + offsetY,
                            paddingLeft + totalWidth,
                            paddingTop + offsetY + itemHeight
                        )
                    } else {
                        layoutDecoratedWithMargins(
                            view,
                            paddingLeft + offsetX,
                            paddingTop + offsetY + itemHeight,
                            paddingLeft + totalWidth,
                            paddingTop + offsetY + totalHeight
                        )
                        offsetX = 0
                        offsetY += totalHeight
                    }
                }
            }
            if (i % 3 == 2) {
                offsetX = 0
            }
        }
    }
    override fun canScrollVertically(): Boolean = true
}