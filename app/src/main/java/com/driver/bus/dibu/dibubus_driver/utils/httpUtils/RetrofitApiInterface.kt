package com.driver.bus.dibu.dibubus_driver.utils.httpUtils

import com.driver.bus.dibu.dibubus_driver.model.LoginModel
import com.driver.bus.dibu.dibubus_driver.model.MyOrderListModel
import retrofit2.Call
import retrofit2.http.*


/**
 * 网络请求接口
 */
interface RetrofitApiInterface {

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("/user/login")
    fun login(@FieldMap fields: Map<String, String>) : Call<LoginModel>

    /**
     * 获取订单列表
     */
    @GET("/driver/order/list")
    fun getDriverOrderList(@QueryMap maps: Map<String, Int>) : Call<MyOrderListModel>
}