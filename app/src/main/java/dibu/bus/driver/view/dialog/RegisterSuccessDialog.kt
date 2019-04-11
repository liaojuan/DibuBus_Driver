package dibu.bus.driver.view.dialog

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import dibu.bus.driver.R
import dibu.bus.driver.utils.ScreenUtil
import dibu.bus.driver.view.dialog.base.BaseDialog
import kotlinx.android.synthetic.main.dialog_register_success.*


class RegisterSuccessDialog(private val mContext: Context) : BaseDialog(mContext, R.style.dialog), View.OnClickListener{

    init {
        window.attributes.windowAnimations = R.style.ActionSheetDialogAnimation
        setContentView(layoutInflater.inflate(R.layout.dialog_register_success, null))

//        val adImageWidth = ScreenUtil.getScreenWidth(mContext) * 240 / 360
//        val adImageHeight = adImageWidth * 356 / 268
//        val params = register_dialog_layout.layoutParams
//        params.width = adImageWidth
//        params.height = adImageHeight
//
//        val margin = ScreenUtil.getScreenHeight(mContext) * 50 / 1280
//        val marginLayoutParams = register_dialog_close.layoutParams as ViewGroup.MarginLayoutParams
//        marginLayoutParams.topMargin = margin
//        marginLayoutParams.bottomMargin = margin

        val layoutParams = window.attributes
        layoutParams.width = (ScreenUtil.getScreenWidth(context) * 0.8).toInt()
        layoutParams.gravity = Gravity.CENTER
        layoutParams.windowAnimations = 0

        register_dialog_close.setOnClickListener(this)
        register_dialog_sure.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.register_dialog_close -> dismiss()
            R.id.register_dialog_sure -> dismiss()
        }
    }

}