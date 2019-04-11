package dibu.bus.driver.model

import dibu.bus.driver.contract.LineListContract

/**
 * 线路列表
 */
class LineListModel : LineListContract.Model, BaseModel(){
    var data: Data ?= null  //该对象是可以为null的 这样写

    class Data  {
        var total : Long = 0 //记录总条数
        var list: List<ListData>? = null
        var pageNum = 1 //当前页
        var pageSize = 10 //分页大小
        var size: Long = 3 //当前页的记录数
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

    class ListData{
        var createTime: String = ""
        var updateTime: String = ""
        var id: Long = 0
        var driverId: Long = 0
        var title: String = ""
        var activeTime: String =""
        var startAddr: String = "" //起点
        var endAddr: String ="" //终点
        var trackName: String ="" //整体线路
        var trackPoint: String = "" //整体线路经纬度
        var startLng: Double = 0.0 //起点经纬度
        var startLat: Double = 0.0
        var endLng: Double = 0.0 //终点经纬度
        var endLat: Double = 0.0
        var status: Int = 0 //线路状态(1:停用，2:启用)
    }

}

//获取列表数据采用统一的分页模式
//
//{
//    "code": "0000",
//    "msg": "ok",
//    "data": {
//    "total": 13, //总的记录数
//    "list": [records], //数据的数组
//    "pageNum": 2, //当前页号
//    "pageSize": 10, //分页大小(分页的记录数)
//    "size": 3, //当前页的记录数
//    "startRow": 11, //开始行号
//    "endRow": 13, //结束行号
//    "pages": 2, //总共有多少页
//    "prePage": 1, //前一页的页号
//    "nextPage": 0,//后一页的页号
//    "isFirstPage": false,//是否是第一页
//    "isLastPage": true,//是否是最后一页
//    "hasPreviousPage": true,//是否有前一页
//    "hasNextPage": false,//是否有下一页
//    "navigatePages": 8,  //导航的页号数量
//    "navigatepageNums": [ //页号导航
//    1,
//    2
//    ],
//    "navigateFirstPage": 1, //首页的页号
//    "navigateLastPage": 2   //最后一页的页号
//}
//}


