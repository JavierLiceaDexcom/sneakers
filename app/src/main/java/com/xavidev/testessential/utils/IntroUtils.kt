package com.xavidev.testessential.utils

import android.content.Context
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.xavidev.testessential.R
import com.xavidev.testessential.ui.intro.IntroItem
import com.xavidev.testessential.ui.intro.Onboarding
import com.xavidev.testessential.ui.intro.adapters.IntroAdapter

class IntroUtils : Onboarding {
    override fun getIntroItems(): List<IntroItem> {
        return listOf(
            IntroItem(
                R.drawable.sneaker1,
                "Train to your own peace",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                        "Donec porta pulvinar mi, id sollicitudin tortor tempus ut."
            ),
            IntroItem(
                R.drawable.sneaker2,
                "Plan your routine",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                        "Donec porta pulvinar mi, id sollicitudin tortor tempus ut."
            ),
            IntroItem(
                R.drawable.sneaker3,
                "Reach your goals",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                        "Donec porta pulvinar mi, id sollicitudin tortor tempus ut."
            ),
            IntroItem(
                R.drawable.sneaker4,
                "Reach your goals",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                        "Donec porta pulvinar mi, id sollicitudin tortor tempus ut."
            ),
        )
    }

    override fun setupIntroIndicator(
        adapter: IntroAdapter,
        layoutIndicators: LinearLayout,
        context: Context
    ) {
        val indicators = mutableListOf<ImageView>()
        for (i in 0..adapter.itemCount) {
            indicators.add(ImageView(context))
        }
        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(8, 0, 8, 0)
        for (i in 0 until adapter.itemCount) {
            indicators[i] = ImageView(context)
            indicators[i].setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.onbording_indicator_active
                )
            )
            indicators[i].layoutParams = layoutParams
            layoutIndicators.addView(indicators[i])
        }
    }

    override fun setCurrentIndicator(index: Int, layoutIndicators: LinearLayout, context: Context) {
        val childCount = layoutIndicators.childCount
        val activeIndicator = ContextCompat.getDrawable(
            context,
            R.drawable.onbording_indicator_active
        )
        val inactiveIndicator = ContextCompat.getDrawable(
            context,
            R.drawable.onbording_indicator_inactive
        )
        for (i in 0 until childCount) {
            val imageView = layoutIndicators.getChildAt(i) as ImageView
            if (i == index) {
                imageView.setImageDrawable(activeIndicator)
            } else {
                imageView.setImageDrawable(inactiveIndicator)
            }
        }
    }
}