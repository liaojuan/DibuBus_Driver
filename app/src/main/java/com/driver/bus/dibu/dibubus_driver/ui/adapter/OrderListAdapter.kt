package com.driver.bus.dibu.dibubus_driver.ui.adapter

import android.content.Context
import android.graphics.Rect
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.driver.bus.dibu.dibubus_driver.R
import com.driver.bus.dibu.dibubus_driver.utils.ScreenUtil
import com.driver.bus.dibu.dibubus_driver.view.RecyclerViewSpacesItemDecoration
import kotlinx.android.synthetic.main.order_list_item.view.*
import kotlinx.android.synthetic.main.order_list_line_item.view.*

class OrderListAdapter(val list: ArrayList<String>, val mContext: Context) : BaseAdapter() {


    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var mViewHolder: MyViewHolder? = null
        var mConvertView = convertView
        if (mConvertView == null){
            mConvertView = LayoutInflater.from(mContext).inflate(R.layout.order_list_item, null)
            mViewHolder = MyViewHolder(mConvertView)
            mConvertView!!.tag = mViewHolder
        }else{
            mViewHolder = mConvertView.tag as MyViewHolder
        }
        if (position % 2 == 0) { //单数颜色
            mViewHolder.view.order_list_item_up_layout.background = mContext.resources.getDrawable(R.drawable.blue_top_shape_bg)
            mViewHolder.view.order_list_item_start_car_txt.background = mContext.resources.getDrawable(R.drawable.blue_type12_shape_bg)
        } else { //双数颜色
            mViewHolder.view.order_list_item_up_layout.background = mContext.resources.getDrawable(R.drawable.green_top_shape_bg)
            mViewHolder.view.order_list_item_start_car_txt.background = mContext.resources.getDrawable(R.drawable.green_type12_shape_bg)
        }

        var adapter = OrderLineAdapter(mContext, list)
        val linearLayoutManager = LinearLayoutManager(mContext)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        mViewHolder.view.order_list_item_recycler.layoutManager = linearLayoutManager

        val decoration = getItemDecoration(getWidth(list), list)
        val mm = if (decoration > 0)
            RecyclerViewSpacesItemDecoration(ScreenUtil.sher(0, 0, decoration, 0))
        else
            RecyclerViewSpacesItemDecoration(ScreenUtil.sher(0, 0, decoration, decoration))
        mViewHolder.view.order_list_item_recycler.addItemDecoration(mm)
        mViewHolder.view.order_list_item_recycler.adapter = adapter
        
        return mConvertView!!
    }

    override fun getItem(position: Int): Any {
        return list!![position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }

    inner class MyViewHolder(val view: View)


    /**
     * 获取字体宽度
     */
    private fun getWidth(list: ArrayList<String>): Int {
        var width = 0
        val view = LayoutInflater.from(mContext).inflate(R.layout.order_list_line_item, null)
        for (i in 0 until list.size) {
            val rect = Rect()
            view.order_list_line_item_txt.paint.getTextBounds(list[i], 0, list[i].length, rect)
            width += rect.width()
        }
        var s = 20 * list.size
//        LogUtils.e("MainActivity", "----------MainActivity------$width----$s")
        width += s
        return width
    }

    /**
     * 设置item间距
     */
    private fun getItemDecoration(width: Int, list: ArrayList<String>): Int {
        val screenWidth = ScreenUtil.getScreenWidth(mContext) //获取屏幕宽度
        var itemDecoration = 20
        var widths = 20 * list.size
        if (screenWidth < width) {
            //这个距离就不变
        } else {
            itemDecoration = ((screenWidth - width + widths) / list.size) / 2
        }
//        LogUtils.e("MainActivity", "----------MainActivity------screenWidth----$screenWidth----width---$width-----$widths----itemDecoration----$itemDecoration")
        return itemDecoration
    }
}