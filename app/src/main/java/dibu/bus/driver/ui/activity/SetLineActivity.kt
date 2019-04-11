package dibu.bus.driver.ui.activity

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import dibu.bus.driver.R
import dibu.bus.driver.ui.activity.base.BaseActivity
import dibu.bus.driver.ui.adapter.SetSaveLineAdapter
import dibu.bus.driver.utils.logutils.LogUtils
import kotlinx.android.synthetic.main.activity_set_line.*

class SetLineActivity : BaseActivity(), View.OnClickListener {
    var list = ArrayList<String>()
    var position: Int = 0
    private var setSaveLineAdapter: SetSaveLineAdapter? = null

    override fun getContentResId(): Int {
        return R.layout.activity_set_line
    }

    override fun initViews() {
        showTitleLeftTxt()
        showTitleRightTxt(resources.getString(R.string.add_line)) //设置右边标题栏为新增线路

        add_address_txt.setOnClickListener(this)

        setSaveLineAdapter = SetSaveLineAdapter(mContext, list)
        val linearLayoutManager = LinearLayoutManager(mContext)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        set_line_list_item_recycler.layoutManager = linearLayoutManager
        set_line_list_item_recycler.adapter = setSaveLineAdapter
        setSaveLineAdapter!!.setOnclicklistener(object : SetSaveLineAdapter.MyInter {
            override fun onItemClick(positions: Int) { //点击整体线路里面列表的minus图片，减少一个地址
//                setSaveLineAdapter?.notifyItemRemoved(positions)
//                list.remove(positions)
                list.removeAt(positions)
                setSaveLineAdapter!!.notifyDataSetChanged()
                this@SetLineActivity.position -= 1
                LogUtils.e("SetLineActivity", "------------SetLineActivity-----我点击了图片----$positions---------$position")
            }
        })
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.add_address_txt -> {
                setSaveLineAdapter!!.notifyItemInserted(position)
                list.add(position, "我是测试$position")
//                setSaveLineAdapter!!.notifyDataSetChanged()
//                list.add(position, "我是测试$position")
                position += 1
            }
        }
    }


}