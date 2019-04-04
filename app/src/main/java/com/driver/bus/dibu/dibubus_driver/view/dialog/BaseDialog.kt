package com.driver.bus.dibu.dibubus_driver.view.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.View
import com.driver.bus.dibu.dibubus_driver.ui.activity.base.BaseActivity

open class BaseDialog : Dialog {

    private var mContext: Context? = null

    constructor(context: Context) : super(context) {
        mContext = context
    }

    constructor(context: Context, themeResId: Int) : super(context, themeResId) {
        mContext = context
    }

    protected constructor(context: Context, cancelable: Boolean, cancelListener: DialogInterface.OnCancelListener) : super(context, cancelable, cancelListener) {
        mContext = context
    }

    /**
     * 获取组件
     *
     * @param resId
     * @param <T>
     * @return
    </T> */
    protected fun <T : View> getViewById(resId: Int): T {
        return findViewById(resId) as T
    }

    /**
     * 获取组件
     *
     * @param resId
     * @param <T>
     * @return
    </T> */
    protected fun <T : View> getViewById(resId: Int, rootView: View?): T {
        return if (rootView != null)
            rootView.findViewById(resId) as T
        else
            findViewById(resId) as T
    }

    fun showProgressDialog() {
        if (mContext is BaseActivity) {
            (mContext as BaseActivity).showProgress()
        }
    }

    fun dismissProgressDialog() {
        if (mContext is BaseActivity) {
            (mContext as BaseActivity).dismissProgress()
        }
    }
}