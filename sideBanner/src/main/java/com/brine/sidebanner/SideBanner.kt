package com.brine.sidebanner

import android.app.Activity
import android.graphics.Color
import android.graphics.Typeface
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView

object SideBanner {

    fun attach(activity: Activity) {
        if (!BuildConfig.DEBUG) return

        val context = activity

        // Convert 100dp to px
        val size = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 100f, context.resources.displayMetrics
        ).toInt()

        // Square container
        val container = FrameLayout(context).apply {
            layoutParams = FrameLayout.LayoutParams(size, size)
        }

        // DEBUG text across the square
        val textView = TextView(context).apply {
            text = "DEBUG"
            setBackgroundColor(Color.rgb(165, 42, 42)) // brown-red
            setTextColor(Color.WHITE)
            textSize = 12f
            typeface = Typeface.DEFAULT_BOLD
            gravity = Gravity.CENTER
            rotation = 45f
            setPadding(30, 6, 30, 6)
        }

        val textParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT,
            Gravity.CENTER
        )
        container.addView(textView, textParams)

        // Stick container to top-right with overlap
        val params = FrameLayout.LayoutParams(size, size).apply {
            gravity = Gravity.TOP or Gravity.END
            topMargin = -size / 2    // move upward so it touches the top
            marginEnd = -size / 2    // move right so it touches the edge
        }

        (activity.window.decorView as ViewGroup).addView(container, params)
    }
}



