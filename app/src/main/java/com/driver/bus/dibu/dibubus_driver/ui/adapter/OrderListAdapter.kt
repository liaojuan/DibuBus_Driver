package com.driver.bus.dibu.dibubus_driver.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
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
        mViewHolder!!.mContentTv!!.text = itemList!![position]
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
        var mContentTv: TextView?=null

        init {
            mContentTv = view.test_item_txt
        }
    }
}