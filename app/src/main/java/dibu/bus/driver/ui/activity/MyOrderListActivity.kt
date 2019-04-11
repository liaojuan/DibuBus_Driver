package dibu.bus.driver.ui.activity

import android.content.Intent
import android.support.v4.widget.SwipeRefreshLayout
import android.view.View
import android.widget.AdapterView
import dibu.bus.driver.R
import dibu.bus.driver.contract.MyOrderListContract
import dibu.bus.driver.model.MyOrderListModel
import dibu.bus.driver.presenter.MyOrderListPresenter
import dibu.bus.driver.ui.activity.base.BaseActivity
import dibu.bus.driver.ui.adapter.OrderListAdapter
import dibu.bus.driver.utils.logutils.LogUtils
import dibu.bus.driver.view.LoadMoreListView
import kotlinx.android.synthetic.main.activity_order_list.*

class MyOrderListActivity : BaseActivity() , MyOrderListContract.View, SwipeRefreshLayout.OnRefreshListener, LoadMoreListView.OnLoadMoreListener, AdapterView.OnItemClickListener{
    lateinit var myOrderListPresenter: MyOrderListPresenter
    var orderAdapter : OrderListAdapter ?= null
    var isLoad : Boolean = false //是否要进行加载数据
    var pageSize : Int = 10 //基础页数
    var pageNum : Int = 1 //当前页数
    var status : Int = 0 //订单状态(0:标识获取所有订单)  如等待上车，行程中，已完成
    var isRefresh : Boolean = true //是刷新还是下拉加载更多
    var mList: ArrayList<MyOrderListModel.OrderListData> ? = null //adapter里面的list数据

    override fun getContentResId(): Int {
        return R.layout.activity_order_list
    }

    override fun initViews() {
        showTitleLeftTxt()

        myOrderListPresenter = MyOrderListPresenter(this, mContext)
        onRefresh()
    }

    /**
     * 下拉刷新
     */
    override fun onRefresh() {
        if (!isLoad){
            isRefresh = true
            showProgress()
            isLoad = true
            myOrderListPresenter.getDriverOrderList(status, pageNum, pageSize)
        }
    }

    /**
     * 上拉加载更多
     */
    override fun onLoadMore() {
        if (!isLoad){
            isRefresh = false //是进行下拉加载更多
            showProgress()
            isLoad = true
            myOrderListPresenter.getDriverOrderList(status, pageNum, pageSize)
        }
    }

    /**
     * item点击事件
     */
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        //点击跳转到订单详情页面
        if (orderAdapter != null){
            var orderItem : MyOrderListModel.OrderListData = orderAdapter!!.getItem(position) as MyOrderListModel.OrderListData
            if (orderItem != null){
                var intent = Intent(this@MyOrderListActivity, OrderDetailsActivity::class.java)
                intent.putExtra("orderItem", orderItem)
                startActivity(intent)
            }
        }
    }

    /**
     * 获取订单列表成功
     */
    override fun getDataSuccess(data : MyOrderListModel.Data){
        dismissProgress()
        refreshLayout.isRefreshing = false
        listView.isLoadMore = false
        isLoad = false
        if (isRefresh && orderAdapter != null){
            mList!!.removeAll(mList!!)
            orderAdapter!!.notifyDataSetInvalidated()
        }else{
            if (orderAdapter == null){
                mList = data.list as ArrayList<MyOrderListModel.OrderListData>?
                orderAdapter = OrderListAdapter(mList!!, mContext)
                listView.adapter = orderAdapter
            }else{
                mList!!.addAll((data.list as ArrayList<MyOrderListModel.OrderListData>?)!!)
                orderAdapter!!.notifyDataSetInvalidated()
            }
            tripDataIsEmpty()
        }

    }

    /**
     * 获取订单列表失败
     */
    override fun getDataFailure(msg: String?){
        dismissProgress()
        isLoad = false
        refreshLayout.isRefreshing = false
        listView.isLoadMore = false
        tripDataIsEmpty()
    }

    /**
     * 是否显示当前页面没有订单的提示信息
     */
    private fun tripDataIsEmpty(){
        if (orderAdapter == null || orderAdapter!!.count == 0)
            empty_hint.visibility = View.VISIBLE
        else
            empty_hint.visibility = View.GONE
    }

//    /**
//     * 初始化数据 测试数据
//     */
//    private fun initData(): ArrayList<String> {
//        var list = ArrayList<String>()
//        val shareData = this.resources.getStringArray(R.array.line_city)
//        for (i in shareData.indices) {
//            list.add(i, shareData[i])
//        }
//        LogUtils.e("我在点击", "----list------${list.size}")
//        return list
//    }
}