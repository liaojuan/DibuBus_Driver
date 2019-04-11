package dibu.bus.driver.utils.maputils

import android.content.Context
import android.text.TextUtils
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import dibu.bus.driver.utils.logutils.LogUtils

class LocationController(private val context: Context){
    private var aMapLocationClient: AMapLocationClient? = null

    private var locationCallBack: LocationCallBack? = null

    private val amapLocationListener = AMapLocationListener { aMapLocation ->
        //定位回调
        if (aMapLocation != null && aMapLocation.errorCode == AMapLocation.LOCATION_SUCCESS && !TextUtils.isEmpty(aMapLocation.city)) {
            //                LogUtils.e("定位", "设置线路界面定位成功 latitude:" + aMapLocation.getLatitude() + "  longitude:" + aMapLocation.getLongitude() + " address:" + aMapLocation.getAddress());
//            val currentCity = City()
//            currentCity.name = aMapLocation.city
//            currentCity.code = aMapLocation.cityCode
//            currentCity.adcode = aMapLocation.adCode
//            val location = Location(currentCity, aMapLocation.longitude, aMapLocation.latitude, aMapLocation.poiName, aMapLocation.street)
            if (locationCallBack != null)
                locationCallBack!!.onLocationSuccess(aMapLocation)

            //停止定位
            //aMapLocationClient.stopLocation();

        } else {
            LogUtils.e("定位", "定位失败")
            if (locationCallBack != null)
                locationCallBack!!.onLocationFailure()
        }
    }

    /**
     * 开启定位
     */
    fun startLocation(locationCallBack: LocationCallBack) {
        this.locationCallBack = locationCallBack

        val mLocationOption: AMapLocationClientOption

        aMapLocationClient = AMapLocationClient(context)
        //初始化定位参数
        mLocationOption = AMapLocationClientOption()
        //设置定位监听
        aMapLocationClient!!.setLocationListener(amapLocationListener)
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.locationMode = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy
        //设置定位间隔,单位毫秒,默认为2000ms
        //        mLocationOption.setInterval(2000);
        mLocationOption.isOnceLocation = true
        //设置定位参数
        aMapLocationClient!!.setLocationOption(mLocationOption)
        // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
        // 在定位结束后，在合适的生命周期调用onDestroy()方法
        // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
        //启动定位
        aMapLocationClient!!.startLocation()
    }

    fun startLocation(locationCallBack: LocationCallBack, timion: Long) {
        this.locationCallBack = locationCallBack

        val mLocationOption: AMapLocationClientOption

        aMapLocationClient = AMapLocationClient(context)
        //初始化定位参数
        mLocationOption = AMapLocationClientOption()
        //设置定位监听
        aMapLocationClient!!.setLocationListener(amapLocationListener)
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.locationMode = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.interval = timion
        //        mLocationOption.setOnceLocation(true);
        //设置定位参数
        aMapLocationClient!!.setLocationOption(mLocationOption)
        // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
        // 在定位结束后，在合适的生命周期调用onDestroy()方法
        // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
        //启动定位
        aMapLocationClient!!.startLocation()
    }

    interface LocationCallBack {
        /**
         * 定位成功
         *
         * @param aMapLocation
         */
        fun onLocationSuccess(aMapLocation: AMapLocation?)

        /**
         * 定位失败
         */
        fun onLocationFailure()
    }

    /**
     * 停止定位
     */
    fun destroyLocation() {
        try {
            if (aMapLocationClient != null) {
                aMapLocationClient!!.stopLocation()
                if (amapLocationListener != null)
                    aMapLocationClient!!.unRegisterLocationListener(amapLocationListener)
                aMapLocationClient!!.onDestroy()
                aMapLocationClient = null
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}