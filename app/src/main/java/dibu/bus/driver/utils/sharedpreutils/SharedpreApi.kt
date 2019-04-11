package dibu.bus.driver.utils.sharedpreutils

/**
 * 本地存储数据的名称
 */
interface SharedpreApi {
    companion object {
        val PREFERENCE_NAME = "App_Config"
        val BUS_TOKEN = "bus_token"  //保存的token
        val USER_TYPE = "user_type" //登录类型：1 乘客，2 司机， 99 管理员
        val KEY_DEVICEID="device_id"//设备唯一标识

        //登录返回的值
        val LOGINMODEL = "login_model_data"

        var SCORE = "score" //星级
        var GENDER = "gender" //性别
        var MOBILE  = "mobile" //电话号码
        var AGE  ="age" //年龄
        var STATUS = "status" //用户状态
        var AVATARURL = "avatarUrl" //用户头像
        var NICKNAME = "nickName" //用户昵称
    }
}