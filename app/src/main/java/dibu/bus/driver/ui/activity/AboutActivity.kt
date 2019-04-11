package dibu.bus.driver.ui.activity

import dibu.bus.driver.R
import dibu.bus.driver.ui.activity.base.BaseActivity

class AboutActivity : BaseActivity() {

    override fun getContentResId(): Int {
        return R.layout.activity_about
    }

    override fun initViews() {
        showTitleLeftTxt()
    }
}