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

class OrderListAdapter(val list: List<String>, val mContext: Context) : BaseAdapter() {


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
//        mViewHolder.view.order_list_item_date_txt.text
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

}