package com.driver.bus.dibu.dibubus_driver.ui.adapter

import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.driver.bus.dibu.dibubus_driver.R
import com.driver.bus.dibu.dibubus_driver.bean.ShareBean
import com.driver.bus.dibu.dibubus_driver.utils.logutils.LogUtils
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
    var inter: MyInter? = null
    var width = 0 //所有字体和间距之和

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        var view: View = LayoutInflater.from(context).inflate(R.layout.main_layout_line_item, p0, false) //这种方式布局里面宽度要用wrap_content  不然 他默认是全屏
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        p0?.name.text = list[p1]

//        if (list.size < 4) {
//            val rect = Rect()
//            p0.itemView.main_layout_line_item_txt.paint.getTextBounds(list[p1], 0, list[p1].length, rect)
//            width += rect.width() + 10
//            LogUtils.e("---", "--------------${rect.width()} ----- $width")
//        }


//        for (i in 0 until list.size) {
//            width += rect.width()

//        }
//        for (i in 0 until list.size + 1){
//            width += 20
//        }
//        if (width > 450){
//
//        }else{
//
//        }

//        LogUtils.e("---","--------------${rect.width()} ----- $width")

        p0!!.itemView.setOnClickListener {
            inter?.onItemClick()
        }
    }

    fun setOnclicklistener(inter: MyInter) {
        this.inter = inter
    }


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.main_layout_line_item_txt
    }

    /**
     * 自定义接口，方便点击处理事件
     */
    interface MyInter {
        fun onItemClick()
    }

//    /**
//     * 自定义接口，获取width值
//     */
//    interface MyWidth{
//        fun getWidth(width: Int)
//    }

//    /**
//     * 获取整个textview宽度
//     */
//    private fun listWidth() : Int{
//        var width = 0
//        for (i in 0 until list.size) {
//            width =
//        }
//        return width
//    }


}