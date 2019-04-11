package dibu.bus.driver.model

import dibu.bus.driver.contract.LoginContract
import java.io.Serializable

class LoginModel : LoginContract.Model, BaseModel(){
    var data: LoginData ?= null  //该对象是可以为null的 这样写

    class LoginData : Serializable{
        var score : Int = 0
        var vehicleNo : String ?= null //车牌号
        var brandName : String ?= null //车站名称
        var gender : Int = 0
        var mobile : String ?= null
        var userType : Int = 1
        var age : Int = 30
        var status : Int = 1
        var avatarUrl : String ?= null //用户头像
        var orderCount : Long = 0 //订单数
        var nickName : String ?= null //昵称
        var actionType : Int = 2//1：出车，2：收车
    }
}
