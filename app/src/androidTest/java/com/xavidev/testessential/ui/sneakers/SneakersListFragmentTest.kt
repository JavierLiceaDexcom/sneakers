package com.xavidev.testessential.ui.sneakers

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.xavidev.testessential.R
import com.xavidev.testessential.RecyclerviewItemCount
import com.xavidev.testessential.atPosition
import com.xavidev.testessential.firstItem
import org.hamcrest.core.AllOf.allOf
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SneakersListFragmentTest {

    lateinit var testScenario: FragmentScenario<SneakersListFragment>

    @Before
    fun setUp() {
        testScenario = launchFragmentInContainer(themeResId = R.style.Base_Theme_TestEssential)
        Thread.sleep(3000)
    }

    @After
    fun tearDown() {
        testScenario.close()
    }

    // Verify sneakers title
    @Test
    fun sneakersToolbarTitleShouldShowSneakers() {
        onView(withId(R.id.tbr_sneakers)).check(matches(isDisplayed()))
        onView(withId(R.id.tbr_sneakers)).check(matches(hasDescendant(withText("Sneakers"))))
    }

    // Verify list of brands is present and count is correct
    @Test
    fun brandsListFilterShouldBeDisplayedAndCountEight() {
        val expectedBrandsCount = 8
        onView(withId(R.id.rv_sneaker_brands)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_sneaker_brands)).check(RecyclerviewItemCount(expectedBrandsCount))
    }

    // Verify first element in the list
    @Test
    fun sneakersListShouldBePresentAndFirstElementIsCorrect() {
        val firstPosition = 0
        onView(withId(R.id.rv_sneakers)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_sneakers)).check(matches(atPosition(firstPosition, hasDescendant(withText("Nike")))))
        onView(withId(R.id.rv_sneakers)).check(matches(atPosition(firstPosition, hasDescendant(withText("Air Jordan 1 Mid")))))
        onView(withId(R.id.rv_sneakers)).check(matches(atPosition(firstPosition, hasDescendant(withText("$ 2999.00 MXN")))))
    }

    // Verify element in list of sneakers (texts, image, colors, discount, favorite)
    // Verify filter by brands show desired sneakers
    // Verify sneakers with discount
    // Add and remove sneaker from favorites and check result
}