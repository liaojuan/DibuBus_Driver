package dibu.bus.driver.model

import android.os.Parcel
import android.os.Parcelable
import dibu.bus.driver.contract.MyOrderListContract

/**
 * 订单列表
 */
class MyOrderListModel : MyOrderListContract.Model, BaseModel() {
    var data: Data ?= null  //该对象是可以为null的 这样写

    class Data  {
        var total : Long = 0 //记录总条数
        var list: List<OrderListData>? = null
        var pageNum :Int = 1 //当前页
        var pageSize : Int = 10 //分页大小
        var size: Long = 3 //当前页的记录数 (当前页数中，记录条数)
        var startRow: Long = 0 //开始行号
        var endRow: Long = 0 //结束行号
        var pages: Int = 0//总共有多少页
        var prePage: Int = 0//前一页的页号
        var nextPage: Int = 0//后一页的页号
        var isFirstPage: Boolean = false//是否是第一页
        var isLastPage: Boolean = true //是否是最后一页
        var hasPreviousPage: Boolean = false//是否有前一页
        var hasNextPage: Boolean = false//是否有下一页
        var navigatePages: Int = 0//导航的页号数量
        var navigatepageNums : List<Int>? = null//页号导航
        var navigateFirstPage: Int = 1//首页的页号
        var navigateLastPage: Int = 1 //最后一页的页号
    }

    class OrderListData() : Parcelable{
        var createTime: String = "" //订单日期
        var updateTime: String = ""
        var id: Long = 0
        var startTime : String = "" //出站时间
        var routeName : String = ""
        var passengerId : Long = 0
        var passengerPhone: String = ""
        var startAddr: String = "" //起点
        var startLng: Double = 0.0 //起点经纬度
        var startLat: Double = 0.0
        var endAddr: String ="" //终点
        var endLng: Double = 0.0 //终点经纬度
        var endLat: Double = 0.0
        var upAddr : String = "" //上车地址
        var upLng: Double = 0.0 //上车经纬度
        var upLat: Double = 0.0
        var upTime: String ?= null //上车时间
        var downAddr : String = "" //下车地址
        var downLng: Double = 0.0 //下车经纬度
        var downLat: Double = 0.0
        var downTime: String ?= null //下车时间
        var status: Int = -1//订单状态[1:预约中,3:已上车,4:已下车,5:订单完成,-1:订单取消,-2:未上车]
        var routeId: Long = 0 //线路id
        var driverId: Long = 0//司机id
        var driverName: String = ""//司机姓名
        var driverPhone: String = ""//司机电话号码
        var vehicleNo: String = "" //车牌号
        var score: Float ?= null //订单评分
        var cmt: String ?= null //订单评价司机人很好，服务周到"

        constructor(parcel: Parcel) : this() {
            createTime = parcel.readString()
            updateTime = parcel.readString()
            id = parcel.readLong()
            startTime = parcel.readString()
            routeName = parcel.readString()
            passengerId = parcel.readLong()
            passengerPhone = parcel.readString()
            startAddr = parcel.readString()
            startLng = parcel.readDouble()
            startLat = parcel.readDouble()
            endAddr = parcel.readString()
            endLng = parcel.readDouble()
            endLat = parcel.readDouble()
            upAddr = parcel.readString()
            upLng = parcel.readDouble()
            upLat = parcel.readDouble()
            upTime = parcel.readString()
            downAddr = parcel.readString()
            downLng = parcel.readDouble()
            downLat = parcel.readDouble()
            downTime = parcel.readString()
            status = parcel.readInt()
            routeId = parcel.readLong()
            driverId = parcel.readLong()
            driverName = parcel.readString()
            driverPhone = parcel.readString()
            vehicleNo = parcel.readString()
            score = parcel.readValue(Float::class.java.classLoader) as? Float
            cmt = parcel.readString()
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(createTime)
            parcel.writeString(updateTime)
            parcel.writeLong(id)
            parcel.writeString(startTime)
            parcel.writeString(routeName)
            parcel.writeLong(passengerId)
            parcel.writeString(passengerPhone)
            parcel.writeString(startAddr)
            parcel.writeDouble(startLng)
            parcel.writeDouble(startLat)
            parcel.writeString(endAddr)
            parcel.writeDouble(endLng)
            parcel.writeDouble(endLat)
            parcel.writeString(upAddr)
            parcel.writeDouble(upLng)
            parcel.writeDouble(upLat)
            parcel.writeString(upTime)
            parcel.writeString(downAddr)
            parcel.writeDouble(downLng)
            parcel.writeDouble(downLat)
            parcel.writeString(downTime)
            parcel.writeInt(status)
            parcel.writeLong(routeId)
            parcel.writeLong(driverId)
            parcel.writeString(driverName)
            parcel.writeString(driverPhone)
            parcel.writeString(vehicleNo)
            parcel.writeValue(score)
            parcel.writeString(cmt)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<OrderListData> {
            override fun createFromParcel(parcel: Parcel): OrderListData {
                return OrderListData(parcel)
            }

            override fun newArray(size: Int): Array<OrderListData?> {
                return arrayOfNulls(size)
            }
        }
    }

}