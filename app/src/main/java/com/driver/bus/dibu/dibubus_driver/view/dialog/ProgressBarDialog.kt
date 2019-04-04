package com.driver.bus.dibu.dibubus_driver.view.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.widget.ImageView
import com.driver.bus.dibu.dibubus_driver.R


class ProgressBarDialog(context: Context) : Dialog(context, R.style.dialog) {
    private var loadingImage: ImageView? = null

    init {
        initView()
    }

    private fun initView() {
        setContentView(R.layout.layout_progressbar_dialog)
        setCancelable(false)
        window!!.setBackgroundDrawableResource(R.color.transparent)
        loadingImage = findViewById<ImageView>(R.id.loading_img)


    }


    override fun show() {
        val animationDrawable = loadingImage!!.drawable as AnimationDrawable
        animationDrawable.start()
        super.show()
    }
}