package com.driver.bus.dibu.dibubus_driver.utils

/**
 * 司机现在的状态
 */
interface ActionType {
    companion object {
        val DRIVER_ACTIONTYPE_CLOSE_CAR = 2 //收车
        val DRIVER_ACTIONTYPE_START_CAR = 1 //出车
    }
}