package com.brine.sidebanner

import android.graphics.Color
import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.Nullable

class SideBanner @JvmOverloads constructor(
    context: Context,
    @Nullable attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {

    private var paint: Paint = Paint()
    private var rect: Rect? = null
    private var textPaint: Paint = Paint()

    private var viewHeight: Int = 0
    private var viewWidth: Int = 0
    private var index: Int = 0

    private var showText: String? = null
    private var backgroundColor: Int = Color.BLUE
    private var textSizePx: Int = 30
    private var textColor: Int = Color.BLACK
    private var bannerPosition : Int = 3 // bottom right

    init {
        val t = context.obtainStyledAttributes(attrs, R.styleable.FloatImageView)
        backgroundColor = t.getColor(R.styleable.FloatImageView_backgroundColor, Color.BLUE)
        textSizePx = t.getDimensionPixelSize(R.styleable.FloatImageView_textSize, 30)
        showText = t.getString(R.styleable.FloatImageView_text) ?: "DEBUG"
        textColor = t.getColor(R.styleable.FloatImageView_textColor, Color.WHITE)
        bannerPosition = t.getInt(R.styleable.FloatImageView_bannerPosition,3)
        t.recycle()

        initPaint()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        viewHeight = measuredHeight
        viewWidth = measuredWidth
        index = (viewHeight / 3) / 2
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.save()

        paint.color = backgroundColor

        // Decide rectangle position based on bannerPosition
        rect = when (bannerPosition) {
            0, 1 -> Rect(-200, viewHeight / 2 - index, viewWidth + 200, viewHeight / 2) // TOP mirrored
            else -> Rect(-200, viewHeight / 2, viewWidth + 200, viewHeight / 2 + index) // BOTTOM
        }

        // Rotation based on position
        val rotation = when (bannerPosition) {
            0 -> -45f // top-left
            1 -> 45f  // top-right
            2 -> 45f  // bottom-left
            3 -> -45f // bottom-right
            else -> -45f
        }

        // Rotate and draw banner
        canvas.rotate(rotation, (viewWidth / 2).toFloat(), (viewHeight / 2).toFloat())
        rect?.let { canvas.drawRect(it, paint) }

        // Draw text inside banner
        showText?.let {
            val textY = when (bannerPosition) {
                0, 1 -> (viewHeight / 2 - (index / 4f)) // vertically center in mirrored rectangle
                else -> (viewHeight / 2 + (index * 3) / 4).toFloat()
            }

            canvas.drawText(
                it,
                (viewWidth / 2).toFloat(),
                textY,
                textPaint
            )
        }

        canvas.restore()
    }


    private fun initPaint() {
        paint = Paint()

        textPaint = Paint().apply {
            textSize = textSizePx.toFloat()
            color = textColor
            typeface = Typeface.DEFAULT_BOLD
            textAlign = Paint.Align.CENTER // Center horizontally
        }

        elevation = 1f
    }

    fun setText(msg: String) {
        showText = msg
        invalidate()
    }

    fun setBackGroundColor(color: Int) {
        backgroundColor = color
        invalidate()
    }

    fun setTextColor(color : Int) {
        textColor = color
        invalidate()
    }

    fun setTextSize(size : Int){
        textSizePx = size
        invalidate()
    }

    fun setBannerPosition(position : Int = 3){
        bannerPosition = position
        invalidate()
    }

    private fun dpToPx(dp: Float): Float {
        return dp * resources.displayMetrics.density
    }

}



