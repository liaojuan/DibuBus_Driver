package com.driver.bus.dibu.dibubus_driver.contract

interface LoginContract {
    interface Model

    interface View{
        fun showLoginSuccess()
        fun showLoginFail(msg: String?)
    }

    interface Presenter{
        fun login(userName: String, password: String, userType: Int)
        fun onDestory()
    }
}
