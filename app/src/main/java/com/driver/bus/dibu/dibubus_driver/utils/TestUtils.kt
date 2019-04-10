package com.driver.bus.dibu.dibubus_driver.utils

import android.content.Context
import com.driver.bus.dibu.dibubus_driver.R
import com.driver.bus.dibu.dibubus_driver.utils.logutils.LogUtils

object TestUtils {

    /**
     * 初始化数据 测试数据
     */
     fun initData(mContext: Context): ArrayList<String> {
        var list = ArrayList<String>()
        val shareData = mContext.resources.getStringArray(R.array.line_city)
        for (i in shareData.indices) {
            list.add(i, shareData[i])
        }
        LogUtils.e("我在点击", "----list------${list.size}")
        return list
    }
}