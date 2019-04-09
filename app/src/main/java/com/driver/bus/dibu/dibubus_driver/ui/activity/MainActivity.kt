package com.driver.bus.dibu.dibubus_driver.ui.activity

import android.content.Intent
import android.graphics.Rect
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.Gravity
import android.view.View

import com.driver.bus.dibu.dibubus_driver.R
import com.driver.bus.dibu.dibubus_driver.model.LoginModel
import com.driver.bus.dibu.dibubus_driver.ui.activity.base.BaseActivity
import com.driver.bus.dibu.dibubus_driver.ui.adapter.MainLineAdapter
import com.driver.bus.dibu.dibubus_driver.ui.adapter.MainMessageAdapter
import com.driver.bus.dibu.dibubus_driver.ui.adapter.MainOrderAdapter
import com.driver.bus.dibu.dibubus_driver.utils.ActionType
import com.driver.bus.dibu.dibubus_driver.utils.ScreenUtil
import com.driver.bus.dibu.dibubus_driver.utils.imageutils.GlideUtils
import com.driver.bus.dibu.dibubus_driver.utils.logutils.LogUtils
import com.driver.bus.dibu.dibubus_driver.utils.sharedpreutils.SharedpreApi
import com.driver.bus.dibu.dibubus_driver.utils.sharedpreutils.SharedpreferencesUtil
import com.driver.bus.dibu.dibubus_driver.view.RecyclerViewSpacesItemDecoration
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.head_layout.view.*
import kotlinx.android.synthetic.main.main_layout.*
import kotlinx.android.synthetic.main.main_layout_line_item.view.*

class MainActivity : BaseActivity(), View.OnClickListener {
//    val data = SharedpreferencesUtil.getObject("login") as LoginModel.LoginData //读取本地sharedpreferences数据
    var data: LoginModel.LoginData ?= null
    var messageAdapter: MainMessageAdapter ?= null //通知adapter
    var orderAdapter: MainOrderAdapter ?= null //预约乘客adapter

    override fun getContentResId(): Int {
        return R.layout.activity_main
    }

    override fun initViews() {
        dissTitleLayout()

//        //设置Drawerlayout的开关,并且和Home图标联动
//        val mToggle = ActionBarDrawerToggle(this, drawer_layout, main_toolbar, 0, 0)
//        drawer_layout.addDrawerListener(mToggle)
//        //同步drawerlayout的状态
//        mToggle.syncState()

        setTitler(title)
        drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

        initLinstener()

        var datas = SharedpreferencesUtil.getObject(SharedpreApi.LOGINMODEL)
        if (datas != null){
            data = datas as LoginModel.LoginData
            LogUtils.e("MainActivity", "----------MainActivity----data--${data!!.actionType}")
        }

        val list = initData()
        var adapter = MainLineAdapter(this, list)
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        main_layout_departure_recycler.layoutManager = linearLayoutManager

        val decoration = getItemDecoration(getWidth(list), list)
        val mm = if (decoration > 0)
            RecyclerViewSpacesItemDecoration(ScreenUtil.sher(0, 0, decoration, 0))
        else
            RecyclerViewSpacesItemDecoration(ScreenUtil.sher(0, 0, decoration, decoration))
        main_layout_departure_recycler.addItemDecoration(mm)
        main_layout_departure_recycler.adapter = adapter
        var bo = decoration > 20
        if (bo) {
            main_layout_line_item_fillet.visibility = View.VISIBLE
        } else {
            main_layout_line_item_fillet.visibility = View.GONE
        }
        main_layout_departure_recycler!!.setOnScrollListener(object : RecyclerView.OnScrollListener() {
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
                        main_layout_line_item_fillet.visibility = View.VISIBLE
                    } else {
                        main_layout_line_item_fillet.visibility = View.GONE
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

        initDatas()
    }

    /**
     * 显示数据
     */
    private fun initDatas() {
        if (data != null){
            main_layout_user_name_txt.text = data!!.nickName  //!!代表可以为null，没有做判断
            if (!TextUtils.isEmpty(data!!.avatarUrl))
                GlideUtils.showCircleImage(mContext, user_head_img, data!!.avatarUrl)
//            main_layout_rating_bar.setRating(data!!.score.toFloat())
//            main_layout_license_plate_txt.text = data!!.vehicleNo
//            main_layout_car_type_txt.text = data!!.brandName
//            main_layout_order_num_txt.text = data!!.orderCount.toString() + "单"

            if (data!!.actionType == ActionType.DRIVER_ACTIONTYPE_START_CAR){//出车
                main_message_list.visibility = View.GONE
                main_order_list.visibility = View.VISIBLE
            }else if (data!!.actionType == ActionType.DRIVER_ACTIONTYPE_CLOSE_CAR){ //收车
                main_order_list.visibility = View.GONE
                main_message_list.visibility = View.VISIBLE
            }
        }
        LogUtils.e("MainActivity", "----------MainActivity----actionType--${data!!.actionType}")
        messageAdapter = MainMessageAdapter(initData(), mContext)
        main_layout_message_list_view.adapter = messageAdapter

        orderAdapter = MainOrderAdapter(initData(), mContext)
        main_layout_order_list_view.adapter = orderAdapter
    }

    /**
     * 获取字体宽度
     */
    private fun getWidth(list: ArrayList<String>): Int {
        var width = 0
        val view = layoutInflater.inflate(R.layout.main_layout_line_item, null)
        for (i in 0 until list.size) {
            val rect = Rect()
            view.main_layout_line_item_txt.paint.getTextBounds(list[i], 0, list[i].length, rect)
            width += rect.width()
        }
        var s = 20 * list.size
//        LogUtils.e("MainActivity", "----------MainActivity------$width----$s")
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
//        LogUtils.e("MainActivity", "----------MainActivity------screenWidth----$screenWidth----width---$width-----$widths----itemDecoration----$itemDecoration")
        return itemDecoration
    }

    fun setTitler(str: CharSequence) {
        if (!TextUtils.isEmpty(str))
            main_center_txt.text = str
    }

    /**
     * 注册监听
     */
    fun initLinstener() {
        val navigationView = nav_view.getHeaderView(0)
        navigationView.user_head.setOnClickListener(this)
        navigationView.my_order.setOnClickListener(this)
        navigationView.my_line.setOnClickListener(this)
        navigationView.my_agreement.setOnClickListener(this)
        navigationView.my_about.setOnClickListener(this)
        navigationView.share_txt.setOnClickListener(this)
        navigationView.edit_login_txt.setOnClickListener(this)
        main_left_img.setOnClickListener(this)
        main_right_img.setOnClickListener(this)
    }

    //    if (drawer.isDrawerOpen(GravityCompat.START)) {
//        drawer.closeDrawer(GravityCompat.START);
    override fun onClick(v: View?) {
        when (v!!.id) {
            //左边菜单栏部分
            R.id.user_head -> {
                //用户头像
                drawer_layout.closeDrawer(GravityCompat.START)
            }
//            R.id.user_driver_name -> {
//                //用户名称
//            }
//            R.id.ratingBar -> {
//                //用户评分
//            }
            R.id.my_order -> {
                //我的订单
                drawer_layout.closeDrawer(GravityCompat.START)
                startActivity(Intent(this, MyOrderListActivity::class.java))
            }
            R.id.my_line -> {
                //线路管理
                drawer_layout.closeDrawer(GravityCompat.START)
                startActivity(Intent(this, SetLineActivity::class.java))
            }
            R.id.my_agreement -> {
                //使用协议
                drawer_layout.closeDrawer(GravityCompat.START)
            }
            R.id.my_about -> {
                //关于我们
                startActivity(Intent(this, AboutActivity::class.java))
                drawer_layout.closeDrawer(GravityCompat.START)
            }
            R.id.share_txt -> {
                //分享
                startActivity(Intent(this, ShareActivity::class.java))
                drawer_layout.closeDrawer(GravityCompat.START)
            }
            R.id.edit_login_txt -> {
                //退出当前账号
                drawer_layout.closeDrawer(GravityCompat.START)
            }
            //左边菜单栏部分

            //主界面
            R.id.main_left_img -> {
                //标题栏左边图片
//                drawer_layout.isDrawerVisible(GravityCompat.START)
//                drawer_layout.isDrawerOpen(GravityCompat.START)
                drawer_layout.openDrawer(Gravity.LEFT)
                LogUtils.e("我正在打印", "------我正在打印---")
            }
            R.id.main_right_img -> {
                //标题栏右边图片
            }
            //主界面
        }
    }


    /**
     * 初始化数据
     */
    private fun initData(): ArrayList<String> {
        var list = ArrayList<String>()
        val shareData = this.resources.getStringArray(R.array.line_city)
        for (i in shareData.indices) {
            list.add(i, shareData[i])
        }
        LogUtils.e("我在点击", "----list------${list.size}")
        return list
    }

}
