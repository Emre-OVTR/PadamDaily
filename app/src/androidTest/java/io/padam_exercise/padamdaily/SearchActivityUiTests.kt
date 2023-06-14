package io.padam_exercise.padamdaily

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import io.padam_exercise.padamdaily.screens.SearchItineraryActivity
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class SearchActivityUiTests {

    @Before

    fun setUp(){
        ActivityScenario.launch(SearchItineraryActivity::class.java)
    }


    @Test
    fun checkArrivalSpinnerDisplaysArrivalListItems(){
        onView(withId(padam_exercise.padamdaily.R.id.spinner_arrival)).perform(click())
        onView(withText("Venezia")).check(matches(isDisplayed()))
        onView(withText("London")).check(matches(isDisplayed()))
        onView(withText("Surrey")).check(matches(isDisplayed()))
        onView(withText("Hannover")).check(matches(isDisplayed()))
    }

    @Test
    fun checkArrivalAndDepartureTitlesAreDisplayed(){
        onView(withText("Departure")).check(matches(isDisplayed()))
        onView(withText("Arrival")).check(matches(isDisplayed()))
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("io.padam_exercice.padamdaily", appContext.packageName)
    }
}