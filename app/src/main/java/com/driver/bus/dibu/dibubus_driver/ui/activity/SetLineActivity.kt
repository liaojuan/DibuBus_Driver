package com.driver.bus.dibu.dibubus_driver.ui.activity

import com.driver.bus.dibu.dibubus_driver.R
import com.driver.bus.dibu.dibubus_driver.ui.activity.base.BaseActivity

class SetLineActivity : BaseActivity() {

    override fun getContentResId(): Int {
        return R.layout.activity_set_line
    }

    override fun initViews() {
        showTitleLeftTxt()
        showTitleRightTxt(resources.getString(R.string.add_line)) //设置右边标题栏为新增线路
    }
}