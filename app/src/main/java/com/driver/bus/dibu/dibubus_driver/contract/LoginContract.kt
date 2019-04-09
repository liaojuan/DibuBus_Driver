package com.driver.bus.dibu.dibubus_driver.contract

import com.driver.bus.dibu.dibubus_driver.model.LoginModel

interface LoginContract {
    interface Model

    interface View{
        fun showLoginSuccess(loginData: LoginModel.LoginData)
        fun showLoginFail(msg: String?)
    }

    interface Presenter{
        fun login(userName: String, password: String, userType: Int)
        fun onDestory()
    }
}
