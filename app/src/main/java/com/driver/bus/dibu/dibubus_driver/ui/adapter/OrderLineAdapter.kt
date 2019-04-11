package com.driver.bus.dibu.dibubus_driver.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.driver.bus.dibu.dibubus_driver.R
import kotlinx.android.synthetic.main.order_list_line_item.view.*

/**
 * 针对主页面出站后线路处理
 */
class OrderLineAdapter(val context: Context, val list: ArrayList<String>, val type: Int) : RecyclerView.Adapter<OrderLineAdapter.MyViewHolder>() {

    /**
     * 接受变量
     */
    var inter: MyInter? = null
    var width = 0 //所有字体和间距之和

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        var view: View = LayoutInflater.from(context).inflate(R.layout.order_list_line_item, p0, false) //这种方式布局里面宽度要用wrap_content  不然 他默认是全屏
        if (type == 0)
            view.order_list_line_item_iv.setImageResource(R.mipmap.blue_line_ellipse)
        else
            view.order_list_line_item_iv.setImageResource(R.mipmap.green_line_ellipse)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("ResourceType")
    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        p0?.name.text = list[p1]

        p0!!.itemView.setOnClickListener {
            inter?.setImageResourece()
        }
    }

    fun setOnclicklistener(inter: MyInter) {
        this.inter = inter
    }


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.order_list_line_item_txt
        var image: ImageView = view.order_list_line_item_iv
    }

    /**
     * 自定义接口，方便点击处理事件
     */
    interface MyInter {
        fun setImageResourece()
    }

}