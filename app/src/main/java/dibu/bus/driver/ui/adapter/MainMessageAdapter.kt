package dibu.bus.driver.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import dibu.bus.driver.R
import kotlinx.android.synthetic.main.main_message_list_item.view.*

class MainMessageAdapter(val list: List<String>, val mContext: Context) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var mViewHolder: MyViewHolder? = null
        var mConvertView = convertView
        if (mConvertView == null){
            mConvertView = LayoutInflater.from(mContext).inflate(R.layout.main_message_list_item, null)
            mViewHolder = MyViewHolder(mConvertView)
            mConvertView!!.tag = mViewHolder
        }else{
            mViewHolder = mConvertView.tag as MyViewHolder
        }
//        mViewHolder.view.main_message_content_txt.text =
        return mConvertView!!
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }

    inner class MyViewHolder(val view: View)
}