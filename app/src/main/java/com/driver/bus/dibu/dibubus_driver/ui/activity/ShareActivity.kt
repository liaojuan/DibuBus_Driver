package com.driver.bus.dibu.dibubus_driver.ui.activity

import android.support.v7.widget.LinearLayoutManager
import com.driver.bus.dibu.dibubus_driver.R
import com.driver.bus.dibu.dibubus_driver.bean.ShareBean
import com.driver.bus.dibu.dibubus_driver.ui.activity.base.BaseActivity
import com.driver.bus.dibu.dibubus_driver.ui.adapter.ShareItemAdapter
import com.driver.bus.dibu.dibubus_driver.utils.LogUtils
import com.driver.bus.dibu.dibubus_driver.view.RecyclerViewSpacesItemDecoration
import kotlinx.android.synthetic.main.activity_share.*
import java.util.HashMap

class ShareActivity : BaseActivity() {

    override fun getContentResId(): Int {
        return R.layout.activity_share
    }

    override fun initViews() {
        showTitleLeftTxt()

//        mRecyclerView.addItemDecoration(newRecyclerViewSpacesItemDecoration(stringIntegerHashMap))


        var adapter = ShareItemAdapter(this, initData()!!)
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        share_recycler.layoutManager = linearLayoutManager
//        share_recycler.setPadding(15, 0, 15, 0)
        val mm = RecyclerViewSpacesItemDecoration(sher())
        share_recycler.addItemDecoration(mm)
        share_recycler.adapter = adapter
        adapter.setOnclicklistener(object : ShareItemAdapter.MyInter {     //自定义接口回调
            override fun onItemClick() {
                LogUtils.e("我在点击", "----我在点击分享------")
            }

        })
    }

    private fun sher(): HashMap<String, Int> {
        val stringIntegerHashMap = HashMap<String, Int>()
        stringIntegerHashMap[RecyclerViewSpacesItemDecoration.TOP_DECORATION] = 0//top间距
        stringIntegerHashMap[RecyclerViewSpacesItemDecoration.BOTTOM_DECORATION] = 30//底部间距
        stringIntegerHashMap[RecyclerViewSpacesItemDecoration.LEFT_DECORATION] = 15//左间距
        stringIntegerHashMap[RecyclerViewSpacesItemDecoration.RIGHT_DECORATION] = 15//右间距
        return stringIntegerHashMap
    }


    /**
     * 初始化数据
     */
    private fun initData(): ArrayList<ShareBean> {
        var list = ArrayList<ShareBean>()
        val shareData = this!!.resources.getStringArray(R.array.share_items)
        val drawableIds = intArrayOf(R.mipmap.qq_qzone, R.mipmap.qq_friends, R.mipmap.weixin, R.mipmap.weixin_friends)
        for (i in shareData.indices) {
            var shareItem = ShareBean(shareData[i], drawableIds[i])
            list.add(i, shareItem)
        }
        LogUtils.e("我在点击", "----list------${list.size}")
        return list
    }
}