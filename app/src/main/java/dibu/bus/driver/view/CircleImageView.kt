package dibu.bus.driver.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.ImageView


@SuppressLint("AppCompatCustomView")
class CircleImageView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : ImageView(context, attrs, defStyle) {
    private val paint: Paint = Paint()

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {

        val drawable = drawable
        if (null != drawable) {
            var bitmap: Bitmap? = null
            //            if (drawable instanceof GlideBitmapDrawable) {
            //                bitmap = ((GlideBitmapDrawable) drawable).getBitmap();
            //            } else if (drawable instanceof SquaringDrawable) {
            //                bitmap = ((GlideBitmapDrawable)((SquaringDrawable)drawable).getCurrent()).getBitmap();
            //            } else {
            bitmap = (drawable as BitmapDrawable).bitmap
            //            }
            val diameter: Int = if (width > height) {
                height
            } else {
                width
            }
            val b = getCircleBitmap(bitmap!!, diameter)
            val rectSrc = Rect(0, 0, b.width, b.height)
            val rectDest = Rect(0, 0, width, height)
            paint.reset()
            canvas.drawBitmap(b, rectSrc, rectDest, paint)

        } else {
            super.onDraw(canvas)
        }
    }


    private fun getCircleBitmap(bitmap: Bitmap, pixels: Int): Bitmap {
        val scaledSrcBmp: Bitmap
        val diameter = pixels

        val bmpWidth = bitmap.width
        val bmpHeight = bitmap.height
        var squareWidth = 0
        var squareHeight = 0
        var x = 0
        var y = 0
        val squareBitmap: Bitmap
        if (bmpHeight > bmpWidth) {// 高大于宽
            squareHeight = bmpWidth
            squareWidth = squareHeight
            x = 0
            y = (bmpHeight - bmpWidth) / 2
            // 截取正方形图片
            squareBitmap = Bitmap.createBitmap(bitmap, x, y, squareWidth, squareHeight)
        } else if (bmpHeight < bmpWidth) {// 宽大于高
            squareHeight = bmpHeight
            squareWidth = squareHeight
            x = (bmpWidth - bmpHeight) / 2
            y = 0
            squareBitmap = Bitmap.createBitmap(bitmap, x, y, squareWidth, squareHeight)
        } else {
            squareHeight = bmpHeight
            squareWidth = squareHeight
            squareBitmap = bitmap
        }

        if (squareBitmap.width != squareWidth || squareBitmap.height != squareWidth) {
            scaledSrcBmp = Bitmap.createScaledBitmap(squareBitmap, squareWidth, squareWidth, true)

        } else {
            scaledSrcBmp = squareBitmap
        }
        val output = Bitmap.createBitmap(scaledSrcBmp.width, scaledSrcBmp.height, Bitmap.Config.ARGB_4444)
        val canvas = Canvas(output)

        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        val rect = Rect(0, 0, scaledSrcBmp.width, scaledSrcBmp.height)

        canvas.drawARGB(0, 0, 0, 0)
        canvas.drawCircle((scaledSrcBmp.width / 2).toFloat(), (scaledSrcBmp.height / 2).toFloat(), (scaledSrcBmp.width / 2).toFloat(), paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(bitmap, rect, rect, paint)
        return output
    }

}
