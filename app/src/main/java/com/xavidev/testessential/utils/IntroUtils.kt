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
    override fun getIntroItems(context: Context): List<IntroItem> {
        return listOf(
            IntroItem(
                image = R.drawable.air_jordan_mid_college,
                title = context.getString(R.string.text_slide_1_title),
                message = context.getString(R.string.text_slide_1_description),
                backgroundColor = R.color.white,
            ),
            IntroItem(
                image = R.drawable.adidas_forum_bad_bunny,
                title = context.getString(R.string.text_slide_2_title),
                message = context.getString(R.string.text_slide_2_description),
                backgroundColor = R.color.gray_soft2,
            ),
            IntroItem(
                image = R.drawable.puma_sneaker,
                title = context.getString(R.string.text_slide_3_title),
                message = context.getString(R.string.text_slide_3_description),
                backgroundColor = R.color.gray_soft,
            ),
            IntroItem(
                image = R.drawable.vans_old_school,
                title = context.getString(R.string.text_slide_4_title),
                message = context.getString(R.string.text_slide_4_description),
                backgroundColor = R.color.gray_soft,
            ),
            IntroItem(
                image = R.drawable.sneaker_brands,
                title = context.getString(R.string.text_slide_5_title),
                message = context.getString(R.string.text_slide_5_description),
                backgroundColor = R.color.white,
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
        for (i in 0 until adapter.itemCount) {
            indicators[i] = ImageView(context)
            indicators[i].setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.onbording_indicator_active
                )
            )
            layoutParams.setMargins(8, 0, 8, 0)
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