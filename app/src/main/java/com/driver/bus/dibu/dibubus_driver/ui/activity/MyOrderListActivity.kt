package com.driver.bus.dibu.dibubus_driver.ui.activity

import android.support.v4.widget.SwipeRefreshLayout
import android.view.View
import android.widget.AdapterView
import com.driver.bus.dibu.dibubus_driver.R
import com.driver.bus.dibu.dibubus_driver.ui.activity.base.BaseActivity
import com.driver.bus.dibu.dibubus_driver.view.LoadMoreListView

class MyOrderListActivity : BaseActivity() ,SwipeRefreshLayout.OnRefreshListener, LoadMoreListView.OnLoadMoreListener, AdapterView.OnItemClickListener{

    override fun getContentResId(): Int {
        return R.layout.activity_order_list
    }

    override fun initViews() {
        showTitleLeftTxt()

    }

    /**
     * 下拉刷新
     */
    override fun onRefresh() {

    }

    /**
     * 上拉加载更多
     */
    override fun onLoadMore() {
    }

    /**
     * item点击事件
     */
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
    }
}