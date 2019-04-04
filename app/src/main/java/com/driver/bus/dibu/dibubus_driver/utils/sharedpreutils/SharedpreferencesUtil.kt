package com.driver.bus.dibu.dibubus_driver.utils.sharedpreutils

import android.content.Context
import android.content.SharedPreferences
import com.driver.bus.dibu.dibubus_driver.App


/**
 * Sharedpreferences 本地存储数据
 */
object SharedpreferencesUtil {
    private val name = "APP_Config"
    private val prefs: SharedPreferences by lazy {
        App.instance.applicationContext.getSharedPreferences(name, Context.MODE_PRIVATE)
    }

    /**
     * 获取存放数据
     * return 值
     */
    fun getValue(key: String, default: Any): Any = with(prefs){
        return when(default){
            is Int -> getInt(key, default)
            is String -> getString(key, default)
            is Long -> getLong(key, default)
            is Boolean -> getBoolean(key, default)
            is Float -> getFloat(key, default)
            else -> {
                throw IllegalArgumentException("SharedPreferences 类型错误")
            }
        }
    }

    /**
     * 获取string
     */
    fun getString(key: String, default: String ="") : String{
        return getValue(key, default) as String
    }

    /**
     * 获取Int
     */
    fun getInt(key: String, default: Int = 0) : Int{
        return getValue(key, default) as Int
    }

    /**
     * 获取long
     */
    fun getLong(key: String, default: Long = 0) : Long{
        return getValue(key, default) as Long
    }

    /**
     * 获取boolean
     */
    fun getBoolean(key: String, default: Boolean = false) : Boolean{
        return getValue(key, default) as Boolean
    }

    /**
     * 获取float
     */
    fun getFloat(key: String, default: Float = 0f) : Float{
        return getValue(key, default) as Float
    }

    /**
     * 存放SharedPreferences
     * @param key 键
     * @param value 值
     */
    fun saveValue(key: String, value: Any) = with(prefs.edit()){
        when(value){
            is Long -> putLong(key, value)
            is Int -> putInt(key, value)
            is String -> putString(key, value)
            is Boolean -> putBoolean(key, value)
            is Float -> putFloat(key, value)
            else -> {
                throw IllegalArgumentException("SharedPreferences 类型错误")
            }
        }.apply()
    }

    /**
     * 清除所有
     */
    fun clear(){
        prefs.edit().clear().apply()
    }

    /**
     * 删除某key的值
     */
    fun remove(key: String){
        prefs.edit().remove(key).apply()
    }
}