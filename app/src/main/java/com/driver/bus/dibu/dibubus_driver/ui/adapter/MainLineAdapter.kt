package com.driver.bus.dibu.dibubus_driver.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.driver.bus.dibu.dibubus_driver.R
import com.driver.bus.dibu.dibubus_driver.bean.ShareBean
import kotlinx.android.synthetic.main.main_layout_line_item.view.*

/**
 * 针对主页面出站后线路处理
 */
class MainLineAdapter(context: Context, list: ArrayList<String>) : RecyclerView.Adapter<MainLineAdapter.MyViewHolder>() {

    /**
     * 接受变量
     */
    var context = context
    var list = list
    var inter: MyInter ?= null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        var view: View = LayoutInflater.from(context).inflate(R.layout.main_layout_line_item, p0, false) //这种方式布局里面宽度要用wrap_content  不然 他默认是全屏
        var holder = MyViewHolder(view)
        return holder
    }

    override fun getItemCount(): Int {
        return list?.size
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        p0?.name?.text = list[p1]

        p0!!.itemView.setOnClickListener {
            inter?.onItemClick()
        }
    }

    fun setOnclicklistener(inter: MyInter){
        this.inter = inter
    }


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var name: TextView = view.main_layout_line_item_txt
    }

    /**
     * 自定义接口，方便点击处理事件
     */
    interface MyInter{
        fun onItemClick()
    }
}