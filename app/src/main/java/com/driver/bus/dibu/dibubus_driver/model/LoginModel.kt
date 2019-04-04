package com.driver.bus.dibu.dibubus_driver.model

import com.driver.bus.dibu.dibubus_driver.contract.LoginContract

class LoginModel : LoginContract.Model, BaseModel(){
    var data: LoginData ?= null

    class LoginData {
        var score : Int = 0
        var gender : Int = 0
        var mobile : String ?= null
        var userType : Int = 1
        var age : Int = 30
        var status : Int = 1
    }
}
