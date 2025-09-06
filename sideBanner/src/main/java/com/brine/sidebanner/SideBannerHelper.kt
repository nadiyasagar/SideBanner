package com.brine.sidebanner

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout

class SideBannerHelper {

    companion object {

        fun attachBanner(
            activity: Activity,
            text: String = "DEBUG",
            backgroundColor: Int = Color.BLUE,
            textColor: Int = Color.WHITE,
            textSize: Int = 30,
            position: Int = 3, // 0=top-left, 1=top-right, 2=bottom-left, 3=bottom-right
            sizeDp: Int = 100
        ) {
            val rootView = activity.findViewById<ViewGroup>(android.R.id.content)

            val sideBanner = SideBanner(activity).apply {
                setText(text)
                setBackGroundColor(backgroundColor)
                setBannerPosition(position)
                setTextColor(textColor)
                setTextSize(textSize)
            }
            // Convert dp to pixels
            val sizePx = dpToPx(activity, sizeDp)
            val layoutParams = FrameLayout.LayoutParams(sizePx, sizePx).apply {
                gravity = when(position){
                    0 -> Gravity.TOP or Gravity.START
                    1 -> Gravity.TOP or Gravity.END
                    2 -> Gravity.BOTTOM or Gravity.START
                    3 -> Gravity.BOTTOM or Gravity.END
                    else -> Gravity.BOTTOM or Gravity.END
                }
//                marginEnd = dpToPx(activity, 16) // optional margin
//                bottomMargin = dpToPx(activity, 16)
            }

            rootView.addView(sideBanner, layoutParams)
        }

        private fun dpToPx(context: Context, dp: Int): Int {
            return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp.toFloat(),
                context.resources.displayMetrics
            ).toInt()
        }
    }
}
