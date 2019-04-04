package com.driver.bus.dibu.dibubus_driver.utils.logutils

import android.content.Context
import android.text.TextUtils
import android.widget.Toast

/**
 * Toast显示打印或者提示信息
 */
object ToastUtil {
    fun showShortToast(msg: String, mContext: Context) {
        if (!TextUtils.isEmpty(msg))
            Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show()
    }

    fun showShortToast(msgId: Int, mContext: Context) {
        Toast.makeText(mContext, msgId, Toast.LENGTH_SHORT).show()
    }

    fun showLongToast(msg: String, mContext: Context) {
        if (!TextUtils.isEmpty(msg))
            Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show()
    }

    fun showLongToast(msgId: Int, mContext: Context) {
        Toast.makeText(mContext, msgId, Toast.LENGTH_LONG).show()
    }
}