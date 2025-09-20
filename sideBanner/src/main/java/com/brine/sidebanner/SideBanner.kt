package com.brine.sidebanner

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import androidx.core.graphics.withSave

class SideBanner @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
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
        context.withStyledAttributes(attrs, R.styleable.FloatImageView) {
            backgroundColor = getColor(R.styleable.FloatImageView_backgroundColor, Color.BLUE)
            textSizePx = getDimensionPixelSize(R.styleable.FloatImageView_textSize, 30)
            showText = getString(R.styleable.FloatImageView_text) ?: "DEBUG"
            textColor = getColor(R.styleable.FloatImageView_textColor, Color.WHITE)
            bannerPosition = getInt(R.styleable.FloatImageView_bannerPosition, 3)
        }

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
        canvas.withSave {

            paint.color = backgroundColor

            // Measure text height
            val fm = textPaint.fontMetrics
            val textHeight = (fm.descent - fm.ascent).toInt()

            // Margins
            val verticalMargin = dpToPx(10f).toInt()
            val horizontalMargin = dpToPx(5f).toInt()

            // Banner height = text height + vertical margin*2
            val bannerHeight = textHeight + verticalMargin * 2

            // Decide rectangle position based on bannerPosition
            rect = when (bannerPosition) {
                0, 1 -> Rect(
                    -200,
                    viewHeight / 2 - bannerHeight,  // move UP
                    viewWidth + 200,
                    viewHeight / 2
                )

                2, 3 -> Rect(
                    -200,
                    viewHeight / 2,
                    viewWidth + 200,
                    viewHeight / 2 + bannerHeight  // move DOWN
                )

                else -> Rect(
                    -200,
                    viewHeight / 2,
                    viewWidth + 200,
                    viewHeight / 2 + bannerHeight
                )
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
            rotate(rotation, (viewWidth / 2).toFloat(), (viewHeight / 2).toFloat())
            rect?.let { drawRect(it, paint) }

            // Draw text inside banner (centered with margins)
            showText?.let {
                rect?.let { r ->
                    val centerX = r.exactCenterX()
                    val centerY = r.exactCenterY()

                    // Baseline correction for vertical centering
                    val textY = centerY - (fm.ascent + fm.descent) / 2

                    drawText(it, centerX, textY, textPaint)
                }
            }


        }
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
        paint.color = color
        invalidate()
    }

    fun setTextColor(color: Int) {
        textColor = color
        textPaint.color = color
        invalidate()
    }

    fun setTextSize(size: Int) {
        textSizePx = size
        textPaint.textSize = size.toFloat()
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



