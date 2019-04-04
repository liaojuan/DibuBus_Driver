package com.driver.bus.dibu.dibubus_driver.view.dialog

import android.content.Context
import android.view.View
import com.driver.bus.dibu.dibubus_driver.R
import com.driver.bus.dibu.dibubus_driver.view.dialog.base.BaseDialog


class RegisterSuccessDialog(private val mContext: Context) : BaseDialog(mContext, R.style.dialog), View.OnClickListener{

    init {
        window.attributes.windowAnimations = R.style.ActionSheetDialogAnimation
        setContentView(layoutInflater.inflate(R.layout.dialog_register_success, null))


    }

    override fun onClick(v: View?) {

    }

}