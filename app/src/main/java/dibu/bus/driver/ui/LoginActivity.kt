package dibu.bus.driver.ui

import android.app.Activity
import android.content.Intent
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import dibu.bus.driver.R
import dibu.bus.driver.contract.LoginContract
import dibu.bus.driver.model.LoginModel
import dibu.bus.driver.presenter.LoginPresenter
import dibu.bus.driver.ui.activity.MainActivity
import dibu.bus.driver.ui.activity.RegisterActivity
import dibu.bus.driver.ui.activity.base.BaseActivity
import dibu.bus.driver.utils.sharedpreutils.SharedpreApi
import dibu.bus.driver.utils.sharedpreutils.SharedpreferencesUtil
import dibu.bus.driver.view.dialog.RegisterSuccessDialog
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() , LoginContract.View, View.OnClickListener{
    lateinit var presenter: LoginPresenter

    override fun getContentResId(): Int {
        return R.layout.activity_login
    }

    override fun initViews() {
        presenter = LoginPresenter(this, mContext)
        dissTitleLayout()

        btnLogin.setOnClickListener(this)
        user_click_register_txt.setOnClickListener(this)
        username_txt.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                btnLogin.isEnabled = s.toString().isNotEmpty() && s.toString().length > 6 && pass_word_txt.text.toString().length > 6
                btnLogin.isSelected = btnLogin.isEnabled
            }

        })
        pass_word_txt.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                btnLogin.isEnabled = s.toString().isNotEmpty() && s.toString().length >= 6 && username_txt.text.toString().length >= 6
                btnLogin.isSelected = btnLogin.isEnabled
            }

        })
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btnLogin ->{
                showProgress()
                presenter.login(username_txt.text.toString(), pass_word_txt.text.toString(), 2)
            }
            R.id.user_click_register_txt ->{
//                startActivity(Intent(this, RegisterActivity::class.java))
                var data:LoginModel.LoginData =LoginModel.LoginData()
                data.age = 1
                data.avatarUrl = "sjajg"
                data.actionType = 2
                SharedpreferencesUtil.saveObject(SharedpreApi.LOGINMODEL,data)
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }

    override fun showLoginSuccess(loginData: LoginModel.LoginData) {
        dismissProgress()
//        var data:LoginModel.LoginData =LoginModel.LoginData()
//        data.age = 1
//        data.avatarUrl = "sjajg"
//        SharedpreferencesUtil.saveObject(SharedpreApi.LOGINMODEL, data)
        SharedpreferencesUtil.saveObject(SharedpreApi.LOGINMODEL, loginData)
        startActivity(Intent(mContext, MainActivity::class.java))
        finish()
    }

    override fun showLoginFail(msg: String?) {
        normalFail(msg)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestory()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){
            val dialog = RegisterSuccessDialog(mContext)
            dialog.show()
        }
    }
}