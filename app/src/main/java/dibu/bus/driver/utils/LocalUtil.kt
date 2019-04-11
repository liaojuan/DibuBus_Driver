package dibu.bus.driver.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.os.Build
import android.provider.Settings
import android.telephony.TelephonyManager
import android.text.TextUtils
import dibu.bus.driver.utils.sharedpreutils.SharedpreApi
import dibu.bus.driver.utils.sharedpreutils.SharedpreferencesUtil
import java.util.*
import java.util.regex.Pattern

object LocalUtil {
    /**
     * 检查是否存在动态权限
     * @return
     */
    val isCanDynamicPermission: Boolean
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

    /**
     * 获取本地版本号
     *
     * @param context
     * @return
     */
    fun getLocalVsersionName(context: Context): String {
        var versionName = "1.0"
        try {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            versionName = packageInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return versionName
    }

    /**
     * 获取本地版本code
     *
     * @param context
     * @return
     */
    fun getLocalVsersionCode(context: Context): Int {
        var versionCode = 1
        try {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            versionCode = packageInfo.versionCode
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return versionCode
    }

    /**
     * 是否需要升级判断
     *
     * @param newVersionCode
     * @param context
     * @return
     */
    fun isUpgral(newVersionCode: Int, context: Context): Boolean {
        val localVersionCode = getLocalVsersionCode(context)
        return newVersionCode > localVersionCode
    }

    /**
     * 是否需要升级判断
     *
     * @param newVersionName
     * @param context
     * @return
     */
    fun isUpgral(newVersionName: String, context: Context): Boolean {
        val localVersionName = getLocalVsersionName(context)
        val newSplit = newVersionName.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val localSplit = localVersionName.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val count = Math.max(newSplit.size, localSplit.size)

        var newP = 0
        var localP = 0
        for (i in 0 until count) {
            newP = 0
            localP = 0
            if (i < newSplit.size) newP = Integer.valueOf(newSplit[i])!!
            if (i < localSplit.size) localP = Integer.valueOf(localSplit[i])!!
            if (newP > localP) {
                return true
            }
        }
        return false
    }

    /**
     * 验证手机号码是否正确
     */
    fun validatePhone(phone: String): Boolean {
        if (TextUtils.isEmpty(phone) || phone.length < 11) return false
        val pattern = Pattern.compile("^((1[0-9]))\\d{9}\$")
        val matcher = pattern.matcher(phone)
        return matcher.matches()
    }

    /**
     * 验证车牌号是否正确
     */
    fun validateCarCode(carCode: String): Boolean {
        if (TextUtils.isEmpty(carCode)) return false
        val pattern = Pattern.compile("^[京,津,渝,沪,冀,晋,辽,吉,黑,苏,浙,皖,闽,赣,鲁,豫,鄂,湘,粤,琼,川,贵,云,陕,秦,甘,陇,青,台,内蒙古,桂,宁,新,藏,澳,军,海,航,警][A-Z][0-9,A-Z]{5}\$")
        val matcher = pattern.matcher(carCode)
        return matcher.matches()
    }

    /**
     * 验证当前是否连接网络
     */
    fun validateNetwork(mContext: Context): Boolean {
        val connManager: ConnectivityManager = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = connManager.activeNetworkInfo
        if (info != null) return info.isAvailable
        return false
    }


    @SuppressLint("MissingPermission")
            /**
     * 获取设备标识
     *
     * @param context
     * @return
     */
    fun getDeviceID(context: Context): String {
        var deviceId = SharedpreferencesUtil.getString(SharedpreApi.KEY_DEVICEID, "")
        if (TextUtils.isEmpty(deviceId)) {
            val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            val phyDeviceId = telephonyManager.deviceId
            if (!TextUtils.isEmpty(phyDeviceId)) {
                deviceId = phyDeviceId
            } else {
                val androidId = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
                if (!TextUtils.isEmpty(androidId)) {
                    deviceId = androidId
                } else {
                    deviceId = UUID.randomUUID().toString()
                }
            }
            SharedpreferencesUtil.saveValue(SharedpreApi.KEY_DEVICEID, deviceId!!)
        }
        return deviceId!!
    }

}