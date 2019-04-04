package com.driver.bus.dibu.dibubus_driver.utils.sharedpreutils

/**
 * 本地存储数据的名称
 */
interface SharedpreApi {
    companion object {
        val PREFERENCE_NAME = "App_Config"
        val BUS_TOKEN = "bus_token"  //保存的token
        val USER_TYPE = "user_type" //登录类型：1 乘客，2 司机， 99 管理员
    }
}