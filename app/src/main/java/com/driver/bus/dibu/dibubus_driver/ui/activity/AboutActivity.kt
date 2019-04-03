package com.driver.bus.dibu.dibubus_driver.ui.activity

import com.driver.bus.dibu.dibubus_driver.R
import com.driver.bus.dibu.dibubus_driver.ui.activity.base.BaseActivity

class AboutActivity : BaseActivity() {

    override fun getContentResId(): Int {
        return R.layout.activity_about
    }

    override fun initViews() {
        showTitleLeftTxt()
    }
}