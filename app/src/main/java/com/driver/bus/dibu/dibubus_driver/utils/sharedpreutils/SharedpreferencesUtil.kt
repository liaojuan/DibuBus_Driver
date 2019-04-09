package com.driver.bus.dibu.dibubus_driver.utils.sharedpreutils

import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import com.driver.bus.dibu.dibubus_driver.App
import com.driver.bus.dibu.dibubus_driver.utils.logutils.LogUtils
import java.io.*


/**
 * Sharedpreferences 本地存储数据
 */
object SharedpreferencesUtil {
    private val prefs: SharedPreferences by lazy {
        App.instance.applicationContext.getSharedPreferences(SharedpreApi.PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    /**
     * 获取存放数据
     * return 值
     */
    fun getValue(key: String, default: Any?): Any = with(prefs){
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
        }.commit()
    }

    /**
     * 清除所有
     */
    fun clear(){
        prefs.edit().clear().commit() //直接提交
//        prefs.edit().clear().apply() //延迟提交
    }

    /**
     * 删除某key的值
     */
    fun remove(key: String){
        prefs.edit().remove(key).commit()
    }

//    fun savaObject(key: String, value: Class<Any> ) = with(prefs.edit()){
//        try {
//            val baos = ByteArrayOutputStream()
//            val oos = ObjectOutputStream(baos)
//            oos.writeObject(value) //把对象写到流里
//            val temp = String(Base64.encode(baos.toByteArray(), Base64.DEFAULT))
//            putString(key, temp).commit()
//        } catch (e: IOException){
//            e.printStackTrace()
//        }
//    }
//
    /**
     * writeObject 方法负责写入特定类的对象的状态，以便相应的 readObject 方法可以还原它
     * 最后，用Base64.encode将字节文件转换成Base64编码保存在String中
     *
     * @param object 待加密的转换为String的对象
     * @return String   加密后的String
     */
    private fun Object2String(`object`: Any): String? {
        val byteArrayOutputStream = ByteArrayOutputStream()
        var objectOutputStream: ObjectOutputStream? = null
        try {
            objectOutputStream = ObjectOutputStream(byteArrayOutputStream)
            objectOutputStream.writeObject(`object`)
            val string = String(Base64.encode(byteArrayOutputStream.toByteArray(), Base64.DEFAULT))
            objectOutputStream.close()
            LogUtils.e("---","--------Sharedpreferences-----object----$string")
            return string
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }

    }

    /**
     * 使用Base64解密String，返回Object对象
     *
     * @param objectString 待解密的String
     * @return object      解密后的object
     */
    private fun String2Object(objectString: String): Any? {
        val mobileBytes = Base64.decode(objectString.toByteArray(), Base64.DEFAULT)
        val byteArrayInputStream = ByteArrayInputStream(mobileBytes)
        var objectInputStream: ObjectInputStream? = null
        try {
            objectInputStream = ObjectInputStream(byteArrayInputStream)
            val `object` = objectInputStream.readObject()
            objectInputStream.close()
            return `object`
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

    }

    /**
     * 使用SharedPreference保存对象
     *
     * @param fileKey    储存文件的key
     * @param key        储存对象的key
     * @param saveObject 储存的对象
     */
    fun saveObject(key: String, saveObject: Any) {
        LogUtils.e("---","--------Sharedpreferences-----$saveObject")
        val string = Object2String(saveObject) as String
        if (string != null) {
            saveValue(key, string)
        }
        prefs.edit().commit()
    }

    /**
     * 获取SharedPreference保存的对象
     *
     * @param fileKey 储存文件的key
     * @param key     储存对象的key
     * @return object 返回根据key得到的对象
     */
    fun getObject(key: String): Any? {
        val string = prefs.getString(key, "") as String
        LogUtils.e("---","--------Sharedpreferences-----get-----$string")
        return if (string != null) {
            String2Object(string)
        } else {
            null
        }
    }
}