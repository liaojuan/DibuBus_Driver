package com.driver.bus.dibu.dibubus_driver.utils.httpUtils

import com.driver.bus.dibu.dibubus_driver.model.LoginModel
import retrofit2.Call
import retrofit2.http.FieldMap
import retrofit2.http.POST
import retrofit2.http.FormUrlEncoded


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
}