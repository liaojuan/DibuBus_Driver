package com.driver.bus.dibu.dibubus_driver.view

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

import java.util.HashMap

class RecyclerViewSpacesItemDecoration(private val mSpaceValueMap: HashMap<String, Int>) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View,
                                parent: RecyclerView, state: RecyclerView.State) {
        if (mSpaceValueMap[TOP_DECORATION] != null)
            outRect.top = mSpaceValueMap[TOP_DECORATION]!!
        if (mSpaceValueMap[LEFT_DECORATION] != null)

            outRect.left = mSpaceValueMap[LEFT_DECORATION]!!
        if (mSpaceValueMap[RIGHT_DECORATION] != null)
            outRect.right = mSpaceValueMap[RIGHT_DECORATION]!!
        if (mSpaceValueMap[BOTTOM_DECORATION] != null)

            outRect.bottom = mSpaceValueMap[BOTTOM_DECORATION]!!
    }

    private fun sher(): HashMap<*, *> {
        val stringIntegerHashMap = HashMap<String, Int>()
        stringIntegerHashMap[RecyclerViewSpacesItemDecoration.TOP_DECORATION] = 50//top间距
        stringIntegerHashMap[RecyclerViewSpacesItemDecoration.BOTTOM_DECORATION] = 100//底部间距
        stringIntegerHashMap[RecyclerViewSpacesItemDecoration.LEFT_DECORATION] = 50//左间距
        stringIntegerHashMap[RecyclerViewSpacesItemDecoration.RIGHT_DECORATION] = 100//右间距
        return stringIntegerHashMap
    }

    companion object {

        val TOP_DECORATION = "top_decoration"
        val BOTTOM_DECORATION = "bottom_decoration"
        val LEFT_DECORATION = "left_decoration"
        val RIGHT_DECORATION = "right_decoration"
    }
}
