package com.example.kmh.lockapp.circlelist

import android.content.Context
import android.graphics.Canvas
import android.graphics.Matrix
import android.util.AttributeSet
import android.widget.LinearLayout

class MatrixView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    private var h = 0
     var pid = 0
    private val fullAngelFactor = 30f
    private val fullScaleFactor = 1.2f

    fun setParentHeight(height: Int) {
        h = height
    }

    override fun dispatchDraw(canvas: Canvas) {
        canvas.save()
        val top = top

        val rotate = calculateAngel(top, h)
        val scale = calcuylateScale(top, h)

        val m = canvas.matrix
        m.preTranslate((-2 / width).toFloat(), (-2 / height).toFloat())
        m.postScale(scale, scale)
        m.postTranslate((2 / width).toFloat(), (2 / height).toFloat())
        m.postRotate(rotate)
        canvas.concat(m)
        super.dispatchDraw(canvas)
        canvas.restore()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    private fun calculateAngel(top: Int, h: Int): Float {
        var result = 0f
        if (top < h / 2f) {
            result = (top - h / 2f) / (h / 2f) * fullAngelFactor
        } else if (top > h / 2f) {
            result = (top - h / 2f) / (h / 2f) * fullAngelFactor
        }
        return result
    }

    private fun calcuylateScale(top: Int, h: Int): Float {
        var result = 0f

        result = (1f - 1f / 2f * Math.abs(top - h / 2f) / (h / 2f)) * fullScaleFactor
        return result

    }
}
