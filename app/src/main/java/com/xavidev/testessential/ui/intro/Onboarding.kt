package com.xavidev.testessential.ui.intro

import android.content.Context
import android.widget.LinearLayout
import com.xavidev.testessential.ui.intro.adapters.IntroAdapter

interface Onboarding {
    fun getIntroItems(): List<IntroItem>
    fun setupIntroIndicator(adapter: IntroAdapter, layoutIndicators: LinearLayout, context: Context)
    fun setCurrentIndicator(index: Int, layoutIndicators: LinearLayout, context: Context)
}