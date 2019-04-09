package com.driver.bus.dibu.dibubus_driver.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.driver.bus.dibu.dibubus_driver.R
import com.driver.bus.dibu.dibubus_driver.utils.logutils.LogUtils
import kotlinx.android.synthetic.main.main_message_list_item.view.*
import kotlinx.android.synthetic.main.order_list_item.view.*

class MainMessageAdapter(list: List<String>, mContext: Context) : BaseAdapter() {
    var itemList: List<String> ?= null
    var mViewHolder: MyViewHolder ?= null
    var mContext: Context

    init {
        itemList = list
//        LogUtils.e("MainMessageAdapter", "----------MainMessageAdapter------${list.size}")
        this.mContext = mContext
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var mConvertView = convertView
        if (convertView == null){
            mConvertView = LayoutInflater.from(mContext).inflate(R.layout.main_message_list_item, null)
            mViewHolder = MyViewHolder(mConvertView)
            mConvertView!!.tag = mViewHolder
        }else{
            mViewHolder = convertView.tag as MyViewHolder
        }
//        mViewHolder!!.main_message_content_txt!!.text = itemList!![position]
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
//        var main_message_item_notice : ImageView ?= null //左边图片
//        var main_message_item_type_txt: TextView?=null  //消息类型
        var main_message_content_txt: TextView?=null  //消息内容
//        var main_message_item_right_normal: ImageView ?= null//右边图片

        init {
//            main_message_item_notice = view.main_message_item_notice
//            main_message_item_type_txt = view.main_message_item_type_txt
            main_message_content_txt = view.main_message_content_txt
//            main_message_item_right_normal = view.main_message_item_right_normal
        }
    }
}