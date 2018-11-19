package com.example.kmh.lockapp.circlelist

import android.content.Context
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Shader
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import android.widget.ImageView

/**
 *
 * @author Dean.Ding
 *
 * It override ImageView's onDraw , so , some ImageView's origin function can not work: 1.
 * DrawMatrix is disabled 2. CropToPadding is useless 3. It will be always work in
 * CenterCrop mode!!!!!!
 */
class RoundImageView : android.support.v7.widget.AppCompatImageView {

    private var xRadius = 10f
    private var yRadius = 10f
    private val paint = Paint()

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    fun getxRadius(): Float {
        return xRadius
    }

    fun setxRadius(xRadius: Float) {
        this.xRadius = xRadius
    }

    fun getyRadius(): Float {
        return yRadius
    }

    fun setyRadius(yRadius: Float) {
        this.yRadius = yRadius
    }

    override fun onDraw(canvas: Canvas) {
        // java.lang.ClassCastException: android.graphics.drawable.TransitionDrawable cannot be cast
        // to android.graphics.drawable.BitmapDrawable
        val shader: BitmapShader
        if (drawable is BitmapDrawable) {
            val bitmapDrawable = drawable as BitmapDrawable
            // clip
            shader = BitmapShader(bitmapDrawable.bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
            val rect = RectF(0.0f, 0.0f, width.toFloat(), height.toFloat())
            val width = bitmapDrawable.bitmap.width
            val height = bitmapDrawable.bitmap.height
            var src: RectF? = null
            if (width.toFloat() / height > 1) {
                src = RectF(0.0f, 0.0f, height.toFloat(), height.toFloat())
            } else {
                src = RectF(0.0f, 0.0f, width.toFloat(), width.toFloat())
            }
            val matrix = canvas.matrix
            matrix.setRectToRect(src, rect, Matrix.ScaleToFit.CENTER)
            shader.setLocalMatrix(matrix)

            // 抗锯齿
            paint.isAntiAlias = true
            paint.shader = shader
            // draw round circle for HeadImage or other
            canvas.drawRoundRect(rect, (this.width / 2).toFloat(), (this.height / 2).toFloat(), paint)
            // canvas.drawRoundRect(rect, xRadius, yRadius / 2, paint);
        }
    }
}
