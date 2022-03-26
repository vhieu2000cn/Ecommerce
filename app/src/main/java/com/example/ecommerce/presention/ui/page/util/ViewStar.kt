package com.example.ecommerce.presention.ui.page.util


import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import com.example.ecommerce.R


open class ViewStar(context: Context?, attrs: AttributeSet) :
    View(context, attrs) {
    private var starOutline: Bitmap
    private var star: Bitmap
    private var w1 = 0  //width star
    private var h1 = 0  //height star
    private var marginLeft = 0
    private var marginTop = 0
    private var marginBottom = 0
    private var marginRight = 0
    private var mHeight: Int = 0  //height group star
    private var mWidth: Int = 0   //width group star
    private var p = 0             // width between star
    var numberStar = 0// star focus
    var w0:Int =0

    init {
        val array = context!!.obtainStyledAttributes(attrs, R.styleable.ViewStar)
        numberStar = array.getInteger(R.styleable.ViewStar_starNumber, 0)
        starOutline = getBitmapFromVectorDrawable(context, R.drawable.rating_star_outline)
        star = getBitmapFromVectorDrawable(context, R.drawable.rating_star)
    }
    fun setNumber(number: Int){
        numberStar = number
        invalidate()
    }

    fun getBitmapFromVectorDrawable(context: Context?, drawableId: Int): Bitmap {
        val drawable = ContextCompat.getDrawable(context!!, drawableId)
        val bitmap = Bitmap.createBitmap(
            drawable!!.intrinsicWidth,
            drawable.intrinsicHeight, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        w1 = 80
        h1 = 46
        p = 10
        marginTop = 10
        marginBottom = 20
        marginLeft = 0
        marginRight = 30
        mHeight = h1 + marginTop + marginBottom;
        mWidth = w1 * 5 + (5 - 1) * p + marginLeft + marginRight;
        setMeasuredDimension(mWidth, mHeight);
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (i in 0..4) {
            if (i < numberStar) {
                canvas.drawBitmap(
                    star,
                    (i * w1 + marginLeft + i * p).toFloat(),
                    marginTop.toFloat(),
                    null
                )
            } else {
                canvas.drawBitmap(
                    starOutline,
                    (i * w1 + marginLeft + i * p).toFloat(),
                    marginTop.toFloat(),
                    null
                )
            }
        }
    }
//    override fun onTouchEvent(event: MotionEvent): Boolean {
//        val x = event.x
//        when (event.action) {
//            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
//                w0 = width / 5
//                numberStar = (x / w0) as Int
//                if (mGrade === numberStar + 1) {
//                    return true
//                }
//                invalidate()
//            }
//        }
//        return true
}