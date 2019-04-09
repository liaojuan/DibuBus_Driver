package com.driver.bus.dibu.dibubus_driver.presenter

import android.content.Context
import com.driver.bus.dibu.dibubus_driver.contract.LoginContract
import com.driver.bus.dibu.dibubus_driver.model.LoginModel
import com.driver.bus.dibu.dibubus_driver.utils.Constans
import com.driver.bus.dibu.dibubus_driver.utils.httpUtils.CallManager
import com.driver.bus.dibu.dibubus_driver.utils.httpUtils.RetrofitApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter(val v: LoginContract.View, val mContext: Context) : LoginContract.Presenter, CallManager() {

    override fun login(userName: String, password: String, userType: Int) {
        val call = RetrofitApi.instance!!.login(userName, password, userType)
        addCall(call)
        call.enqueue(object : Callback<LoginModel> {
            override fun onFailure(call: Call<LoginModel>, t: Throwable) {
                if (call != null && call.isCanceled){
                    //取消掉的请求
                }else{
                    //异常请求
                    v.showLoginFail(null)
                }
            }

            override fun onResponse(call: Call<LoginModel>, response: Response<LoginModel>) {
                if (response != null && response.isSuccessful) {
                    val loginModel = response.body()
                    if (loginModel != null) {
                        if (loginModel.code == Constans.CODE_SUCCESS) {
                            if (loginModel.data != null)
                                v.showLoginSuccess(loginModel.data!!)
                        } else {
                            v.showLoginFail(loginModel.msg)
                        }
                    } else v.showLoginFail(null)
                } else v.showLoginFail(null)
            }

        })
    }

    override fun onDestory() {
        destory()
    }
}
