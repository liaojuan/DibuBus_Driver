package com.driver.bus.dibu.dibubus_driver.ui.activity

import com.driver.bus.dibu.dibubus_driver.R
import com.driver.bus.dibu.dibubus_driver.ui.activity.base.BaseActivity

class ShareActivity : BaseActivity() {

    override fun getContentResId(): Int {
        return R.layout.activity_share
    }

    override fun initViews() {
        showTitleLeftImg()
    }
}