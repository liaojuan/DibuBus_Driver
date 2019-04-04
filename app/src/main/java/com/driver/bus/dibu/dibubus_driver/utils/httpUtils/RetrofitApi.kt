package com.driver.bus.dibu.dibubus_driver.utils.httpUtils

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextUtils
import com.driver.bus.dibu.dibubus_driver.model.LoginModel
import com.driver.bus.dibu.dibubus_driver.utils.Constans
import com.driver.bus.dibu.dibubus_driver.utils.LogUtils
import com.driver.bus.dibu.dibubus_driver.utils.sharedpreutils.SharedpreApi
import com.driver.bus.dibu.dibubus_driver.utils.sharedpreutils.SharedpreferencesUtil
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.HashMap

class RetrofitApi private constructor(val mContext: Context){

    var retrofit: Retrofit? = null

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        var instance: RetrofitApi? = null

        fun getInstance(context: Context): RetrofitApi {
            if (instance == null) {
                synchronized(RetrofitApi::class) {
                    if (instance == null) {
                        instance = RetrofitApi(context.applicationContext)
                    }

                }
            }
            instance!!.initParam()
            return instance!!
        }
    }

    /**
     * 读取本地缓存token 和 网络请求
     */
    private fun initParam(){
        val bus_token = SharedpreferencesUtil.getString(SharedpreApi.Companion.BUS_TOKEN)
        retrofit = Retrofit.Builder().baseUrl(Constans.apiUrls).addConverterFactory(GsonConverterFactory.create()).client(genericClient(bus_token!!)).build()
    }

    fun genericClient(accessToken: String): OkHttpClient {
        LogUtils.e("http请求返回数据", "data:accessToken=======$accessToken")
        val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> LogUtils.e("http请求返回数据", "data:-----$message") })
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor { chain ->
                    var request = chain.request()
                    val builder = request.newBuilder()

                    request = builder
                            .addHeader(SharedpreApi.Companion.BUS_TOKEN, accessToken)
                            .build()

                    val response = chain.proceed(request)
                    var result = response.body()!!.string()
                    if (!TextUtils.isEmpty(result)) {
                        result = result.replace("\\r".toRegex(), "\r")
                        result = result.replace("\\n".toRegex(), "\n")
                        if (result.startsWith("\"") && result.endsWith("\"")) {
                            result = result.substring(1, result.length - 1)
                        }
                        LogUtils.e("bus-token-返回数据", request.url().toString() + "=====" + result)
                        if (!TextUtils.isEmpty(result)) {
                            val responseBody = ResponseBody.create(response.body()!!.contentType(), result)
                            return@addInterceptor response.newBuilder().body(responseBody).build()
                        }
                    }
                    return@addInterceptor response

                }.build()
    }

    /**
     * 登录
     */
    fun login(userName: String, password: String, userType: Int): Call<LoginModel>{
        val retrofitApiInterface = retrofit!!.create(RetrofitApiInterface::class.java)
        val strMap = HashMap<String, String>()
        strMap["userName"] = userName
        strMap["password"] = password
        strMap["userType"] = userType.toString()
        return retrofitApiInterface.login(strMap)
    }
}