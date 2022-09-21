package com.xavidev.testessential.ui.sneakerDetail

import android.content.Context
import android.widget.LinearLayout
import com.xavidev.testessential.ui.sneakerDetail.adapters.SneakerCarouselAdapter

interface Carousel {
    fun setupCarouselIndicator(adapter: SneakerCarouselAdapter, layoutIndicators: LinearLayout, context: Context)
    fun setCurrentIndicator(index: Int, layoutIndicators: LinearLayout, context: Context)
}