package com.xavidev.testessential.intro

import android.content.Context
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.xavidev.testessential.ui.intro.IntroActivity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import com.xavidev.testessential.R
import com.xavidev.testessential.isVisible
import com.xavidev.testessential.withDrawable
import org.hamcrest.core.AllOf.allOf

@RunWith(JUnit4::class)
class IntroInstrumentedTest {

    private lateinit var appContext: Context

    @Before
    fun setup() {
        appContext = InstrumentationRegistry.getInstrumentation().targetContext
    }

    @Test
    fun introSlidersDisplayCorrectContent() {
        launchActivity<IntroActivity>()
        val imageId = R.id.img_sneaker_onboarding
        val titleId = R.id.tv_title_onboarding
        val descriptionId = R.id.tv_message_onboarding
        val buttonId = R.id.btn_onboarding

        onView(withId(R.id.lyt_indicators)).check(matches(isVisible()))
        // Slide 1
        onView(allOf(withId(imageId), isDisplayed())).check(matches(withDrawable(R.drawable.air_jordan_mid_college)))
        onView(allOf(withId(titleId), isDisplayed())).check(matches(withText(R.string.text_slide_1_title)))
        onView(allOf(withId(descriptionId), isDisplayed())).check(matches(withText(R.string.text_slide_1_description)))
        onView(allOf(withId(buttonId), isDisplayed())).check(matches(withText(R.string.text_next))).perform(click())
        // Slide 2
        onView(allOf(withId(imageId), isDisplayed())).check(matches(withDrawable(R.drawable.adidas_forum_bad_bunny)))
        onView(allOf(withId(titleId), isDisplayed())).check(matches(withText(R.string.text_slide_2_title)))
        onView(allOf(withId(descriptionId), isDisplayed())).check(matches(withText(R.string.text_slide_2_description)))
        onView(allOf(withId(buttonId), isDisplayed())).check(matches(withText(R.string.text_next))).perform(click())
        // Slide 3
        onView(allOf(withId(imageId), isDisplayed())).check(matches(withDrawable(R.drawable.puma_sneaker)))
        onView(allOf(withId(titleId), isDisplayed())).check(matches(withText(R.string.text_slide_3_title)))
        onView(allOf(withId(descriptionId), isDisplayed())).check(matches(withText(R.string.text_slide_3_description)))
        onView(allOf(withId(buttonId), isDisplayed())).check(matches(withText(R.string.text_next))).perform(click())
        // Slide 4
        onView(allOf(withId(imageId), isDisplayed())).check(matches(withDrawable(R.drawable.vans_old_school)))
        onView(allOf(withId(titleId), isDisplayed())).check(matches(withText(R.string.text_slide_4_title)))
        onView(allOf(withId(descriptionId), isDisplayed())).check(matches(withText(R.string.text_slide_4_description)))
        onView(allOf(withId(buttonId), isDisplayed())).check(matches(withText(R.string.text_next))).perform(click())
        // Slide 5
        onView(allOf(withId(imageId), isDisplayed())).check(matches(withDrawable(R.drawable.sneaker_brands)))
        onView(allOf(withId(titleId), isDisplayed())).check(matches(withText(R.string.text_slide_5_title)))
        onView(allOf(withId(descriptionId), isDisplayed())).check(matches(withText(R.string.text_slide_5_description)))
        onView(allOf(withId(R.id.btn_onboarding), isDisplayed())).check(matches(withText(R.string.text_finish)))
    }
}