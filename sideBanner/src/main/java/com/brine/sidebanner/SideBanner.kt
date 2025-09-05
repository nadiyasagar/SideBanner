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
    private var textSizePx: Int = 20
    private var textColor: Int = Color.BLACK

    init {
        val t = context.obtainStyledAttributes(attrs, R.styleable.FloatImageView)
        backgroundColor = t.getColor(R.styleable.FloatImageView_backgroundColor, Color.BLUE)
        textSizePx = t.getDimensionPixelSize(R.styleable.FloatImageView_textSize, 20)
        showText = t.getString(R.styleable.FloatImageView_text) ?: "DEBUG"
        textColor = t.getColor(R.styleable.FloatImageView_textColor, Color.BLACK)
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
        rect = Rect(-200, viewHeight / 2, viewWidth + 200, viewHeight / 2 + index)

        canvas.rotate(-45f, (viewWidth / 2).toFloat(), (viewHeight / 2).toFloat())
        rect?.let { canvas.drawRect(it, paint) }

        showText?.let {
            canvas.drawText(
                it,
                (viewWidth / 2).toFloat(),
                (viewHeight / 2 + (index * 3) / 4).toFloat(),
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
            textAlign = Paint.Align.CENTER // Center horizontally
        }

        if (Build.VERSION.SDK_INT > 21) {
            elevation = 1f
        }
    }

    fun setText(msg: String) {
        showText = msg
        invalidate()
    }

    fun setBackGroundColor(color: Int) {
        backgroundColor = color
        invalidate()
    }
}



