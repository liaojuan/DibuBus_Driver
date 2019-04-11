package dibu.bus.driver.ui.activity

import com.amap.api.location.AMapLocation
import dibu.bus.driver.R
import dibu.bus.driver.model.MyOrderListModel
import dibu.bus.driver.ui.activity.base.BaseActivity
import dibu.bus.driver.utils.maputils.LocationController

class OrderDetailsActivity : BaseActivity() ,LocationController.LocationCallBack{
    var orderItem : MyOrderListModel.OrderListData ?= null

    private var locationController : LocationController ?= null

    override fun getContentResId(): Int {
        return R.layout.activity_order_details
    }

    override fun initViews() {
        orderItem = intent.getParcelableExtra("orderItem") //接受对象

        locationController = LocationController(applicationContext)
        if (orderItem != null){
            when(orderItem!!.status){
                1 -> mContext.getString(R.string.pick_up_passengers)
                3 -> mContext.getString(R.string.trip_ing)
                4 -> mContext.getString(R.string.order_over)
                5 -> mContext.getString(R.string.order_over)
                -1 -> mContext.getString(R.string.order_cancel)
                -2 -> mContext.getString(R.string.order_cancel)
                else -> mContext.getString(R.string.wait_up_car)
            }
        }
    }

    /**
     * 定位成功
     */
    override fun onLocationSuccess(aMapLocation: AMapLocation?) {

    }

    /**
     * 定位失败
     */
    override fun onLocationFailure() {
    }

    override fun onDestroy() {
        super.onDestroy()
        if (locationController != null)
            locationController!!.destroyLocation()
    }
}