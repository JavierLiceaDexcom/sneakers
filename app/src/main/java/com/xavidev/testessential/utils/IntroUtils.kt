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
                image = R.drawable.air_jordan_mid_college,
                title = "Find the hottest sneakers",
                message = "Are you a sneakerhead? " +
                        "Then, you are in the right place",
                backgroundColor = R.color.white,
            ),
            IntroItem(
                image = R.drawable.adidas_forum_bad_bunny,
                title = "The best offers",
                message = "Daily offers in all the brands. " +
                        "Become a member to get exclusive offers",
                backgroundColor = R.color.gray_soft2,
            ),
            IntroItem(
                image = R.drawable.puma_sneaker,
                title = "Latest releases",
                message = "Fond the newest models, pre-order before the release date " +
                        "to be the first one to get them",
                backgroundColor = R.color.gray_soft,
            ),
            IntroItem(
                image = R.drawable.vans_old_school,
                title = "Exclusive models",
                message = "Exclusive models only in Sneakers App " +
                        "that you cannot fin in another place..",
                backgroundColor = R.color.gray_soft,
            ),
            IntroItem(
                image = R.drawable.sneaker_brands,
                title = "The best brands",
                message = "The best brands in the market in a single place. " +
                        "Guarantee of quality.",
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