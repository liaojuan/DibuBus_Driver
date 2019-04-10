package com.driver.bus.dibu.dibubus_driver.ui.adapter

import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.driver.bus.dibu.dibubus_driver.R
import kotlinx.android.synthetic.main.set_save_line_item.view.*

/**
 * 针对主页面出站后线路处理
 */
class SetSaveLineAdapter(val context: Context, val list: ArrayList<String>) : RecyclerView.Adapter<SetSaveLineAdapter.MyViewHolder>() {

    /**
     * 接受变量
     */
    var inter: MyInter? = null
    var width = 0 //所有字体和间距之和

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        var view: View = LayoutInflater.from(context).inflate(R.layout.set_save_line_item, p0, false) //这种方式布局里面宽度要用wrap_content  不然 他默认是全屏
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        p0?.addressTxt.text = list[p1]


        p0!!.minusAddressImg.setOnClickListener {
            inter?.onItemClick(p1)
        }
    }

    fun setOnclicklistener(inter: MyInter) {
        this.inter = inter
    }


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var minusAddressImg: ImageView = view.minus_address_img
        var addressTxt : TextView = view.set_save_line_item_address_txt
    }

    /**
     * 自定义接口，方便点击处理事件
     */
    interface MyInter {
        fun onItemClick(position: Int)
    }





}