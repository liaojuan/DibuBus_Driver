package dibu.bus.driver.ui.adapter

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.RelativeLayout
import android.widget.TextView
import dibu.bus.driver.R
import dibu.bus.driver.utils.logutils.LogUtils
import kotlinx.android.synthetic.main.main_order_list_item.view.*
import kotlinx.android.synthetic.main.order_list_item.view.*

class MainOrderAdapter(val list: List<String>, val mContext: Context) : BaseAdapter() {

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var mViewHolder: MyViewHolder? = null
        LogUtils.e("OrderAdapter", "------------OrderAdapter---$position")
        var mConvertView = convertView
        if (mConvertView == null) {
            mConvertView = LayoutInflater.from(mContext).inflate(R.layout.main_order_list_item, null)
            mViewHolder = MyViewHolder(mConvertView)
            mConvertView!!.tag = mViewHolder
        } else {
            mViewHolder = mConvertView.tag as MyViewHolder
        }

//        mViewHolder.view.main_start_address_txt.text = position.toString()
        if (position % 2 == 0) { //上车
            mViewHolder.view.main_type_txt.background = mContext.resources.getDrawable(R.drawable.orange_shape_bg)
            mViewHolder.view.main_type_txt.text = mContext.resources.getString(R.string.up_car)
        } else { //下车
            mViewHolder.view.main_type_txt.background = mContext.resources.getDrawable(R.drawable.blue_type_shape_bg)
            mViewHolder.view.main_type_txt.text = mContext.resources.getString(R.string.down_car)
        }
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