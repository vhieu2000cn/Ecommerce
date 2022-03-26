package com.example.ecommerce.presention.ui.page.util

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent

class ViewStartInsert(context: Context?, attrs: AttributeSet) : ViewStar(context, attrs) {
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x // The distance from the control itself
        when (event.action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                //case MotionEvent.ACTION_UP:
                w0 = width / 5
                numberStar = (x / w0).toInt()+1
                invalidate()
            }
        }
        return true
    }
}