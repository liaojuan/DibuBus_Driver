package dibu.bus.driver.contract

import dibu.bus.driver.model.LoginModel

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
