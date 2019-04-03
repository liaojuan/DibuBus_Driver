package com.driver.bus.dibu.dibubus_driver.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.text.TextUtils
import android.view.Gravity
import android.view.View

import com.driver.bus.dibu.dibubus_driver.R
import com.driver.bus.dibu.dibubus_driver.ui.activity.base.BaseActivity
import com.driver.bus.dibu.dibubus_driver.utils.LogUtils
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.head_layout.*
import kotlinx.android.synthetic.main.head_layout.view.*
import kotlinx.android.synthetic.main.main_layout.*
import kotlinx.android.synthetic.main.main_layout.view.*

class MainActivity : BaseActivity() , View.OnClickListener{

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
    }

    fun setTitler(str: CharSequence) {
        if (!TextUtils.isEmpty(str))
            main_center_txt.text = str
    }

    /**
     * 注册监听
     */
    fun initLinstener(){
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
        when(v!!.id){
            //左边菜单栏部分
            R.id.user_head ->{
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
            }
            R.id.my_line -> {
                //线路管理
                drawer_layout.closeDrawer(GravityCompat.START)
            }
            R.id.my_agreement -> {
                //使用协议
                drawer_layout.closeDrawer(GravityCompat.START)
            }
            R.id.my_about -> {
                //关于我们
                drawer_layout.closeDrawer(GravityCompat.START)
            }
            R.id.share_txt -> {
                //分享
                drawer_layout.closeDrawer(GravityCompat.START)
                startActivity(Intent(this, ShareActivity::class.java))
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
                LogUtils.e("我正在打印","------我正在打印---")
            }
            R.id.main_right_img -> {
                //标题栏右边图片
            }
            //主界面
        }
    }
}
