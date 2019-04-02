package com.driver.bus.dibu.dibubus_driver.ui.activity

import android.content.Intent
import android.view.View
import com.driver.bus.dibu.dibubus_driver.R
import com.driver.bus.dibu.dibubus_driver.ui.LoginActivity
import com.driver.bus.dibu.dibubus_driver.ui.activity.base.BaseActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity(), View.OnClickListener{

    override fun getContentResId(): Int {
        return R.layout.activity_splash
    }

    override fun initViews() {
        dissTitleLayout()

        test.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.test ->
                startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}