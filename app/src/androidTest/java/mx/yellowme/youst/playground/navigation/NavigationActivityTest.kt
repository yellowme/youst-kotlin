package mx.yellowme.youst.playground.navigation

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import mx.yellowme.youst.R
import mx.yellowme.youst.util.DataBindingIdlingResource
import mx.yellowme.youst.util.monitorActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class NavigationActivityTest {

    private val dataBindingIdlingResource = DataBindingIdlingResource()

    @Test
    fun navigateWithText() {
        val scenario = ActivityScenario.launch(NavigationActivity::class.java)
        dataBindingIdlingResource.monitorActivity(scenario)

        onView(withId(R.id.nameEditText)).perform(typeText("Cesar"), closeSoftKeyboard())
        onView(withId(R.id.navigateButton)).perform(click())

        //Verifying that the text is displayed
        onView(withText("Nice to meet you, Cesar! Tell us... what are you passionate about?")).check(
            matches(isDisplayed())
        )
    }
}
