package dibu.bus.driver.view

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

import dibu.bus.driver.R


/**
 * 五星控件
 */
class RatingView : View {
    //总的星数
    private var ratingNum = 5
    //当前星级
    private var rating = 1.7f
    //星星大小
    private var ratingSize = 10
    //星星间隔
    private var ratingMargin = 10
    //星星背景图片
    private var ratingDrawableResId: Int = 0
    //选中是的颜色
    private var selectColor: Int = 0
    //按下的时间
    private var downTime: Long = 0
    //是否只是整型
    private var isInteger: Boolean = false
    private var userEnable: Boolean = false

    private var onRatingChangeListener: OnRatingChangeListener? = null

    /**
     * 按比例缩放背景
     *
     * @return
     */
    private val ratingBg: Bitmap
        get() {
            val src = BitmapFactory.decodeResource(resources, ratingDrawableResId)
            val out = Bitmap.createBitmap(ratingSize, ratingSize, Bitmap.Config.ARGB_4444)
            val canvas = Canvas(out)
            canvas.drawBitmap(src, Rect(0, 0, src.width, src.height), Rect(0, 0, ratingSize, ratingSize), null)
            src.recycle()
            return out
        }

    fun setOnRatingChangeListener(onRatingChangeListener: OnRatingChangeListener) {
        this.onRatingChangeListener = onRatingChangeListener
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs, context)
    }

    private fun init(attrs: AttributeSet, context: Context) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RatingView)
        ratingNum = typedArray.getInteger(R.styleable.RatingView_ratingNum, 5)
        rating = typedArray.getFloat(R.styleable.RatingView_rating, 1.7f)
        ratingSize = typedArray.getDimensionPixelSize(R.styleable.RatingView_ratingSize, 10)
        ratingMargin = typedArray.getDimensionPixelOffset(R.styleable.RatingView_ratingMargin, 10)
        ratingDrawableResId = typedArray.getResourceId(R.styleable.RatingView_ratingDrawable, -1)
        selectColor = typedArray.getColor(R.styleable.RatingView_ratingSlectColor, Color.RED)
        userEnable = typedArray.getBoolean(R.styleable.RatingView_userEnable, true)
        isInteger = typedArray.getBoolean(R.styleable.RatingView_isInteger, false)
        typedArray.recycle()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        //绘制星星背景
        for (i in 0 until ratingNum) {
            canvas.drawBitmap(ratingBg, (paddingLeft + i * (ratingSize + ratingMargin)).toFloat(), paddingTop.toFloat(), paint)
        }
        //绘制选中星级
        val selectNum = rating.toInt()
        val reNum = rating % selectNum
        if (selectNum > 0) {
            for (i in 0 until selectNum) {
                canvas.drawBitmap(createSelectRate(1f), (paddingLeft + i * (ratingSize + ratingMargin)).toFloat(), paddingTop.toFloat(), paint)
            }
            if (reNum > 0) {
                if (isInteger) {
                    canvas.drawBitmap(createSelectRate(1f), (paddingLeft + selectNum * (ratingSize + ratingMargin)).toFloat(), paddingTop.toFloat(), paint)
                } else {
                    canvas.drawBitmap(createSelectRate(reNum), (paddingLeft + selectNum * (ratingSize + ratingMargin)).toFloat(), paddingTop.toFloat(), paint)
                }
            }
        } else {
            if (rating > 0) {
                if (isInteger) {
                    canvas.drawBitmap(createSelectRate(1f), (paddingLeft + selectNum * (ratingSize + ratingMargin)).toFloat(), paddingTop.toFloat(), paint)
                } else {
                    canvas.drawBitmap(createSelectRate(rating), (paddingLeft + selectNum * (ratingSize + ratingMargin)).toFloat(), paddingTop.toFloat(), paint)
                }
            }


        }


    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (!userEnable) return super.onTouchEvent(event)
        if (event.action == MotionEvent.ACTION_DOWN) {
            downTime = System.currentTimeMillis()
        } else if (event.action == MotionEvent.ACTION_UP || event.action == MotionEvent.ACTION_CANCEL) {
            if (System.currentTimeMillis() - downTime < 100) {
                //点击事件
                val x = event.x
                val currentRating = (x - paddingLeft) / (ratingSize + ratingMargin)
                if (currentRating != rating) {
                    if (onRatingChangeListener != null) {
                        if (isInteger) {
                            onRatingChangeListener!!.onChange(getIntRating(currentRating).toFloat())
                        } else
                            onRatingChangeListener!!.onChange(currentRating)
                    }
                    rating = currentRating
                    invalidate()
                }
            }
        }
        return true
    }

    /**
     * 生成选择的图片
     *
     * @return
     */
    private fun createSelectRate(percent: Float): Bitmap {
        val bit = ratingBg
        val src = Bitmap.createBitmap(ratingSize, ratingSize, Bitmap.Config.ARGB_4444)
        val canvas = Canvas(src)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        canvas.drawBitmap(bit, 0f, 0f, paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(createBitmap(selectColor, percent), 0f, 0f, paint)
        bit.recycle()
        return src
    }

    /**
     * 按照比例生成 覆盖纯色矩形
     *
     * @param color
     * @param precent
     * @return
     */
    private fun createBitmap(color: Int, precent: Float): Bitmap {
        val bitmap = Bitmap.createBitmap((ratingSize * precent).toInt(), ratingSize, Bitmap.Config.ARGB_4444)
        val canvas = Canvas(bitmap)
        canvas.drawColor(color)
        return bitmap
    }

    /**
     * 测量控件大小
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (ratingDrawableResId != -1) {
            //计算宽度
            val totalWidth = ratingSize * ratingNum + (ratingNum - 1) * ratingMargin + paddingLeft + paddingRight
            //计算高度
            val totalHeight = ratingSize + paddingTop + paddingBottom
            setMeasuredDimension(View.MeasureSpec.makeMeasureSpec(totalWidth, View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(totalHeight, View.MeasureSpec.EXACTLY))
        } else
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    fun getRating(): Float {
        return if (isInteger)
            getIntRating(rating).toFloat()
        else rating
    }

    fun setRating(rating: Float) {
        this.rating = rating
        invalidate()
    }

    interface OnRatingChangeListener {
        fun onChange(rating: Float)
    }

    /**
     * float 转换成int
     *
     * @param rat
     * @return
     */
    private fun getIntRating(rat: Float): Int {
        var num = rat.toInt()
        if (rat - num > 0) {
            num++
        }
        return num
    }

}

