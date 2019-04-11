package dibu.bus.driver.ui.activity

import android.content.Intent
import android.view.View
import dibu.bus.driver.R
import dibu.bus.driver.ui.LoginActivity
import dibu.bus.driver.ui.activity.base.BaseActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity(), View.OnClickListener{

    override fun getContentResId(): Int {
        return R.layout.activity_splash
    }

    override fun initViews() {
        dissTitleLayout()

        test.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.test ->
                startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}