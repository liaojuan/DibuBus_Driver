package com.driver.bus.dibu.dibubus_driver.presenter

import android.content.Context
import com.driver.bus.dibu.dibubus_driver.contract.MyOrderListContract
import com.driver.bus.dibu.dibubus_driver.model.LoginModel
import com.driver.bus.dibu.dibubus_driver.model.MyOrderListModel
import com.driver.bus.dibu.dibubus_driver.utils.Constans
import com.driver.bus.dibu.dibubus_driver.utils.httpUtils.CallManager
import com.driver.bus.dibu.dibubus_driver.utils.httpUtils.RetrofitApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyOrderListPresenter(val v: MyOrderListContract.View, val mContext: Context) : MyOrderListContract.Presenter,CallManager(){

    override fun getDriverOrderList(status: Int, pageNum: Int, pageSize: Int) {
        val call = RetrofitApi.instance!!.getDriverOrderList(status, pageNum, pageSize)
        addCall(call)
        call.enqueue(object : Callback<MyOrderListModel> {
            override fun onFailure(call: Call<MyOrderListModel>, t: Throwable) {
                if (call != null && call.isCanceled){
                    //取消掉的请求
                    v.getDataFailure(null)
                }else{
                    //异常请求
                    v.getDataFailure(null)
                }
            }

            override fun onResponse(call: Call<MyOrderListModel>, response: Response<MyOrderListModel>) {
                if (response != null && response.isSuccessful) {
                    val myOrderListModel = response.body()
                    if (myOrderListModel != null) {
                        if (myOrderListModel.code == Constans.CODE_SUCCESS) {
                            if (myOrderListModel.data != null){
                                //数据不为null
                                v.getDataSuccess(myOrderListModel.data!!)
                            }
                        } else {
                            //获取数据为null
                            v.getDataFailure(myOrderListModel.msg)
                        }
                        //获取数据失败
                    } else{
                        v.getDataFailure(null)
                    }
                } else {
                    //获取数据失败
                    v.getDataFailure(null)
                }
            }

        })
    }

}
