package com.driver.bus.dibu.dibubus_driver.presenter

import android.content.Context
import com.driver.bus.dibu.dibubus_driver.contract.MyOrderListContract
import com.driver.bus.dibu.dibubus_driver.utils.httpUtils.CallManager

class MyOrderListPresenter(val v: MyOrderListContract.View, val mContext: Context) : MyOrderListContract.Presenter,CallManager(){

}
