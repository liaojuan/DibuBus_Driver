package com.driver.bus.dibu.dibubus_driver.ui.activity.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.view.Window
import com.driver.bus.dibu.dibubus_driver.R
import com.driver.bus.dibu.dibubus_driver.ui.MessageActivity
import com.driver.bus.dibu.dibubus_driver.ui.activity.AddLineActivity
import com.driver.bus.dibu.dibubus_driver.view.dialog.ProgressBarDialog
import kotlinx.android.synthetic.main.activity_base.*

abstract class BaseActivity : AppCompatActivity() , View.OnClickListener{
    lateinit var mContext: Context
    var progressBarDialog : ProgressBarDialog ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        mContext = this

        setContentView(R.layout.activity_base)
        setLinstener()

        val contentView = getContent()
        setTitleStr(title)
        if (contentView != null) {
            child_contair.addView(contentView)
            initViews()
        }
    }

    fun getContent(): View? {
        var content = getContentView()
        if (content == null) {
            val contentResId = getContentResId()
            if (contentResId != -1) {
                content = layoutInflater.inflate(contentResId, null)
            }
        }
        return content
    }

    open fun getContentResId(): Int {
        return -1
    }

    open fun getContentView(): View? {
        return null
    }

    /**
     * 加载数据
     */
    abstract fun initViews()

    /**
     * 设置标题栏监听
     */
    fun setLinstener(){
        title_left_img.setOnClickListener(this)
        title_left_txt.setOnClickListener(this)
        title_right_img.setOnClickListener(this)
        title_right_txt.setOnClickListener(this)
    }

    open fun dissTitleLayout(){
        toolbar.visibility = View.GONE
    }

    /**
     * 设置标题
     */
    open fun setTitleStr(str: CharSequence){
        if (!TextUtils.isEmpty(str))
            title_center_txt.text = str
    }

    /**
     * 设置标题栏左边图片显示
     */
    open fun showTitleLeftImg(){
        title_left_txt.visibility = View.GONE
        title_left_img.visibility = View.VISIBLE
    }

    /**
     * 设置标题栏左边文字显示
     */
    open fun showTitleLeftTxt(){
        title_left_img.visibility = View.GONE
        title_left_txt.visibility = View.VISIBLE
    }

    /**
     * 设置标题栏左边都不显示
     */
    open fun dissTitleLeft(){
        title_left_img.visibility = View.GONE
        title_left_txt.visibility = View.GONE
    }

    /**
     * 设置标题栏右边文字显示
     */
    open fun showTitleRightTxt(){
        title_right_img.visibility = View.GONE
        title_right_txt.visibility = View.VISIBLE
    }

    /**
     * 设置标题栏右边图片显示
     */
    open fun showTitleRightImg(){
        title_right_txt.visibility = View.GONE
        title_right_img.visibility = View.VISIBLE
    }

    /**
     * 设置标题栏右边都不显示
     */
    open fun dissTitleRight(){
        title_right_txt.visibility = View.GONE
        title_right_img.visibility = View.GONE
    }

    /**
     * 显示加载进度条
     */
    fun showProgress(){
        if (progressBarDialog == null){
            progressBarDialog = ProgressBarDialog(mContext)
        }
        progressBarDialog!!.show()
    }

    /**
     * 隐藏加载进度条
     */
    fun dismissProgress(){
        if (progressBarDialog != null && progressBarDialog!!.isShowing) {
            progressBarDialog!!.dismiss()
        }
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.title_left_txt ->
                finish()
            R.id.title_right_img ->
                startActivity(Intent(this, MessageActivity::class.java))
            R.id.title_right_txt ->
                startActivity(Intent(this, AddLineActivity::class.java))
        }
    }


}