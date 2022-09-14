package com.xavidev.testessential

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import org.hamcrest.BaseMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

fun firstItem(matcher: Matcher<View>): Matcher<View> {
    return object : BaseMatcher<View>() {
        private var isFirst = true
        override fun describeTo(description: Description?) {
            description?.appendText("Must return the first item")
        }

        override fun matches(item: Any?): Boolean {
            if (matcher.matches(item) && isFirst) {
                isFirst = false
                return true
            }
            return false
        }
    }
}

fun isVisible(): Matcher<View> {
    return object : BaseMatcher<View>() {
        override fun describeTo(description: Description?) {
            description?.appendText("Verify if the view is visible")
        }

        override fun matches(item: Any?): Boolean {
            val view = item as View
            return view.visibility == View.VISIBLE
        }
    }
}

fun withDrawable(resourceId: Int): TypeSafeMatcher<View> {
    return object : TypeSafeMatcher<View>() {
        override fun describeTo(description: Description?) {
            description?.appendText("Verify the ImageView resource image")
        }

        override fun matchesSafely(item: View?): Boolean {
            if (!(item is ImageView)) return false
            if (resourceId < 0) return item.drawable == null
            val resources = item.context.resources
            val drawable = resources.getDrawable(resourceId) ?: return false
            val bitmap = getBitmap(item.drawable)
            val otherBitmap = getBitmap(drawable)
            return bitmap.sameAs(otherBitmap)
        }
    }
}

private fun getBitmap(drawable: Drawable): Bitmap {
    val bitmap = Bitmap.createBitmap(
        drawable.intrinsicWidth,
        drawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)
    return bitmap
}