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
import com.xavidev.testessential.ui.sneakers.Carousel
import com.xavidev.testessential.ui.sneakers.adapters.SneakerCarouselAdapter

class SneakerCarouselUtils : Carousel {

    override fun setupCarouselIndicator(
        adapter: SneakerCarouselAdapter,
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
        layoutParams.setMargins(0, 8, 8, 8)
        for (i in 0 until adapter.itemCount) {
            indicators[i] = ImageView(context)
            indicators[i].setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.carousel_indicator_active
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
            R.drawable.carousel_indicator_active
        )
        val inactiveIndicator = ContextCompat.getDrawable(
            context,
            R.drawable.carousel_indicator_inactive
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