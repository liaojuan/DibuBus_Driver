package dibu.bus.driver.contract

import dibu.bus.driver.model.MyOrderListModel

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
