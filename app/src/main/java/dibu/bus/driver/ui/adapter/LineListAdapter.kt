package dibu.bus.driver.ui.adapter

import android.content.Context
import android.graphics.Rect
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import dibu.bus.driver.R
import dibu.bus.driver.utils.ScreenUtil
import dibu.bus.driver.view.RecyclerViewSpacesItemDecoration
import kotlinx.android.synthetic.main.main_layout.*
import kotlinx.android.synthetic.main.order_list_item.view.*
import kotlinx.android.synthetic.main.order_list_line_item.view.*

class LineListAdapter(val list: ArrayList<String>, val mContext: Context) : BaseAdapter() {

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var mViewHolder: MyViewHolder? = null
        var mConvertView = convertView
        if (mConvertView == null) {
            mConvertView = LayoutInflater.from(mContext).inflate(R.layout.line_list_item, null)
            mViewHolder = MyViewHolder(mConvertView)
            mConvertView!!.tag = mViewHolder
        } else {
            mViewHolder = mConvertView.tag as MyViewHolder
        }
        var adapter: OrderLineAdapter
        if (position % 2 == 0) { //单数颜色
            mViewHolder.view.order_list_item_up_layout.background = mContext.resources.getDrawable(R.drawable.blue_top_shape_bg)
            mViewHolder.view.order_list_item_start_car_txt.background = mContext.resources.getDrawable(R.drawable.blue_type12_shape_bg)
            adapter= OrderLineAdapter(mContext, list, 0)
        } else { //双数颜色
            mViewHolder.view.order_list_item_up_layout.background = mContext.resources.getDrawable(R.drawable.green_top_shape_bg)
            mViewHolder.view.order_list_item_start_car_txt.background = mContext.resources.getDrawable(R.drawable.green_type12_shape_bg)
            adapter= OrderLineAdapter(mContext, list, 1)
        }


        val linearLayoutManager = LinearLayoutManager(mContext)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        mViewHolder.view.order_list_item_recycler.layoutManager = linearLayoutManager
        val decoration = getItemDecoration(getWidth(list), list)
        val mm = if (decoration > 0)
            RecyclerViewSpacesItemDecoration(ScreenUtil.sher(0, 0, decoration, 0))
        else
            RecyclerViewSpacesItemDecoration(ScreenUtil.sher(0, 0, decoration, decoration))
        mViewHolder.view.order_list_item_recycler.addItemDecoration(mm)
        mViewHolder.view.order_list_item_recycler.adapter = adapter
        mViewHolder.view.order_list_item_recycler.isNestedScrollingEnabled = false //处理滑动不流畅问题
        var bo = decoration > 20
        if (bo) {
            mViewHolder.view.order_list_line_item_fillet.visibility = View.VISIBLE
        } else {
            mViewHolder.view.order_list_line_item_fillet.visibility = View.GONE
        }
        mViewHolder.view.order_list_item_recycler!!.setOnScrollListener(object : RecyclerView.OnScrollListener() {
            //用来标记是否正在向最后一个滑动
            var isSlidingToLast = false

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (recyclerView != null) {
                    super.onScrollStateChanged(recyclerView, newState)
                }
                val manager = recyclerView!!.layoutManager as LinearLayoutManager
                // 当不滚动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //获取最后一个完全显示的ItemPosition
                    val lastVisibleItem = manager.findLastCompletelyVisibleItemPosition()
                    val totalItemCount = manager.itemCount

                    // 判断是否滚动到底部，并且是向右滚动
                    if (lastVisibleItem == totalItemCount - 1 && isSlidingToLast) {
                        //加载更多功能的代码
                        mViewHolder.view.order_list_line_item_fillet.visibility = View.VISIBLE
                    } else {
                        mViewHolder.view.order_list_line_item_fillet.visibility = View.GONE
                    }
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (recyclerView != null) {
                    super.onScrolled(recyclerView, dx, dy)
                }
                //dx用来判断横向滑动方向，dy用来判断纵向滑动方向
                isSlidingToLast = dx > 0
            }
        })


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


    /**
     * 获取字体宽度
     */
    private fun getWidth(list: ArrayList<String>): Int {
        var width = 0
        val view = LayoutInflater.from(mContext).inflate(R.layout.order_list_line_item, null)
        for (i in 0 until list.size) {
            val rect = Rect()
            view.order_list_line_item_txt.paint.getTextBounds(list[i], 0, list[i].length, rect)
            width += rect.width()
        }
        var s = 20 * list.size
        width += s
        return width
    }

    /**
     * 设置item间距
     */
    private fun getItemDecoration(width: Int, list: ArrayList<String>): Int {
        val screenWidth = ScreenUtil.getScreenWidth(mContext) //获取屏幕宽度
        var itemDecoration = 20
        var widths = 20 * list.size
        if (screenWidth < width) {
            //这个距离就不变
        } else {
            itemDecoration = ((screenWidth - width + widths) / list.size) / 2
        }
        return itemDecoration
    }
}