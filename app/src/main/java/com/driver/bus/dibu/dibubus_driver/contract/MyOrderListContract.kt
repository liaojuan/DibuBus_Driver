package com.driver.bus.dibu.dibubus_driver.contract

import com.driver.bus.dibu.dibubus_driver.model.MyOrderListModel

interface MyOrderListContract {
    interface Model

    interface View{
        fun getDataSuccess(data : MyOrderListModel.Data)
        fun getDataFailure(msg: String?)
    }

    interface Presenter{
        fun getDriverOrderList(status: Int, pageNum : Int, pageSize: Int)
    }
}
