package com.driver.bus.dibu.dibubus_driver.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.RelativeLayout
import android.widget.TextView
import com.driver.bus.dibu.dibubus_driver.R
import kotlinx.android.synthetic.main.order_list_item.view.*

class OrderListAdapter(list: List<String>, mContext: Context) : BaseAdapter() {
    var itemList: List<String> ?= null
    var mViewHolder: MyViewHolder ?= null
    var mContext: Context

    init {
        itemList = list
        this.mContext = mContext
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var mConvertView = convertView
        if (convertView == null){
            mConvertView = LayoutInflater.from(mContext).inflate(R.layout.order_list_item, null)
            mViewHolder = MyViewHolder(mConvertView)
            mConvertView!!.tag = mViewHolder
        }else{
            mViewHolder = convertView.tag as MyViewHolder
        }
//        mViewHolder!!.mContentTv!!.text = itemList!![position]
        return mConvertView!!
    }

    override fun getItem(position: Int): Any {
        return itemList!![position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return itemList?.size ?: 0
    }

    inner class MyViewHolder(view: View){
        var order_list_item_up_layout : RelativeLayout ?= null //上半部分布局
        var order_list_item_date_txt: TextView?=null  //年月日日期
        var order_list_item_start_car_txt : TextView ?= null //出站时间
        var order_list_item_recycler : RecyclerView ?= null //站点列表
        var order_list_item_up_car_time_txt : TextView ?= null //上车时间
        var order_list_item_down_car_time_txt : TextView ?= null //下车时间
        var order_list_item_type_txt: TextView ?= null //订单状态
        var order_list_item_start_address_txt : TextView ?= null //上车地点
        var order_list_item_end_address_txt : TextView ?= null //下车地点

        init {
            order_list_item_up_layout = view.order_list_item_up_layout
            order_list_item_date_txt = view.order_list_item_date_txt
            order_list_item_start_car_txt = view.order_list_item_start_car_txt
            order_list_item_recycler = view.order_list_item_recycler
            order_list_item_up_car_time_txt = view.order_list_item_up_car_time_txt
            order_list_item_down_car_time_txt = view.order_list_item_down_car_time_txt
            order_list_item_type_txt = view.order_list_item_type_txt
            order_list_item_start_address_txt = view.order_list_item_start_address_txt
            order_list_item_end_address_txt = view.order_list_item_end_address_txt
        }
    }
}