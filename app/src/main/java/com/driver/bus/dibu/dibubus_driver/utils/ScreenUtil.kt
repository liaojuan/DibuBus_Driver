package com.driver.bus.dibu.dibubus_driver.utils

import android.content.Context
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import com.driver.bus.dibu.dibubus_driver.view.RecyclerViewSpacesItemDecoration
import java.util.HashMap

/**
 * 获取屏幕高度
 */
object ScreenUtil {
    /**
     * 获取屏幕宽度 单位：px
     *
     * @param context
     * @return
     */
    fun getScreenWidth(context: Context): Int {
        return context.resources.displayMetrics.widthPixels

    }

    /**
     * 获取屏幕高度 单位:px
     *
     * @param context
     * @return
     */
    fun getScreenHeight(context: Context): Int {
        return context.resources.displayMetrics.heightPixels

    }

    /**
     * 获取状态栏高度
     *
     * @param context
     * @return
     */
    fun getStatusHeight(context: Context): Int {
        var statusBarHeight = -1
        //获取status_bar_height资源的ID
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight = context.resources.getDimensionPixelSize(resourceId)
        }
        return statusBarHeight
    }

    /**
     * px转换DP
     *
     * @param px
     * @param context
     * @return
     */
    fun getPxToDp(px: Int, context: Context): Int {
        val density = context.resources.displayMetrics.density
        return (px / density + 0.5).toInt()
    }

    /**
     * dp转换px
     *
     * @param dp
     * @param context
     * @return
     */
    fun getDpToPx(dp: Int, context: Context): Int {
        val density = context.resources.displayMetrics.density
        return (dp * density + 0.5).toInt()
    }

    /**
     * 获取虚拟按键高度
     * @param context
     * @return
     */
    fun getNaviHeight(context: Context): Int {
        var vh = 0
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val dm = DisplayMetrics()
        try {
            @SuppressWarnings("rawtypes")
            val c = Class.forName("android.view.Display")
            @SuppressWarnings("unchecked")
            val method = c.getMethod("getRealMetrics", DisplayMetrics::class.java)
            method.invoke(display, dm)
            vh = dm.heightPixels - windowManager.defaultDisplay.height
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return vh
    }

    /**
     * 获取控件的高度或者宽度  isHeight=true则为测量该控件的高度，isHeight=false则为测量该控件的宽度
     * @param view
     * @param isHeight
     * @return
     */
    fun getViewHeight(view: View?, isHeight: Boolean): Int {
        val result: Int
        if (view == null) return 0
        if (isHeight) {
            val h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            view!!.measure(h, 0)
            result = view!!.measuredHeight
        } else {
            val w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            view!!.measure(0, w)
            result = view!!.measuredWidth
        }
        return result
    }

    /**
     * 主界面的
     */
    fun sher(top: Int, bottom: Int, left: Int, right: Int): HashMap<String, Int> {
        val stringIntegerHashMap = HashMap<String, Int>()
        stringIntegerHashMap[RecyclerViewSpacesItemDecoration.TOP_DECORATION] = top//top间距
        stringIntegerHashMap[RecyclerViewSpacesItemDecoration.BOTTOM_DECORATION] = bottom//底部间距
        stringIntegerHashMap[RecyclerViewSpacesItemDecoration.LEFT_DECORATION] = left//左间距
        stringIntegerHashMap[RecyclerViewSpacesItemDecoration.RIGHT_DECORATION] = right//右间距
        return stringIntegerHashMap
    }

}
