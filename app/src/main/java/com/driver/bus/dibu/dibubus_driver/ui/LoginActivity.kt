package com.driver.bus.dibu.dibubus_driver.ui

import android.content.Intent
import android.view.View
import com.driver.bus.dibu.dibubus_driver.R
import com.driver.bus.dibu.dibubus_driver.ui.activity.MainActivity
import com.driver.bus.dibu.dibubus_driver.ui.activity.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() , View.OnClickListener{

    override fun getContentResId(): Int {
        return R.layout.activity_login
    }

    override fun initViews() {
        dissTitleLayout()

        user_click_register_txt.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.user_click_register_txt ->
                startActivity(Intent(this, MainActivity::class.java))
        }
    }


}