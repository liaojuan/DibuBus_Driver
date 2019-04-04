package com.driver.bus.dibu.dibubus_driver.utils.httpUtils

import retrofit2.Call

/**
 * 请求管理类
 */
open class CallManager() {
    var calls: ArrayList<Call<*>>

    open fun addCall(call: Call<*>) {
        calls.add(call)
    }

    open fun destory() {
        if (calls.size > 0) {
            for (call in calls) {
                if (!call.isCanceled)
                    call.cancel()
            }
        }
        calls.clear()
    }

    init {
        calls = arrayListOf()
    }
}