package com.driver.bus.dibu.dibubus_driver.ui.activity

import android.app.Activity
import com.driver.bus.dibu.dibubus_driver.R
import com.driver.bus.dibu.dibubus_driver.ui.activity.base.BaseActivity
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity() {

    override fun getContentResId(): Int {
        return R.layout.activity_register
    }

    override fun initViews() {

    }

    override fun onBackPressed() {
        if (webView != null) {
            if (webView.canGoBack()) {
                webView.goBack()
                return
            }
        }
        setResult(Activity.RESULT_OK)
        super.onBackPressed()
    }
}