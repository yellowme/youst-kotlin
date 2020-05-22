package mx.yellowme.youst.playground.navigation

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import mx.yellowme.youst.miniapps.R
import mx.yellowme.youst.miniapps.ui.contact.ContactUsActivity
import mx.yellowme.youst.playground.util.DataBindingIdlingResource
import mx.yellowme.youst.playground.util.monitorActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class NavigationActivityTest {
    private val dataBindingIdlingResource = DataBindingIdlingResource()

    @Test
    fun navigateWithText() {
        val scenario = ActivityScenario.launch(ContactUsActivity::class.java)
        dataBindingIdlingResource.monitorActivity(scenario)

        onView(withId(R.id.nameEditText)).perform(typeText("Cesar"), closeSoftKeyboard())
        onView(withId(R.id.navigateButton)).perform(click())

        // Verifying that the text is displayed
        onView(withText("Nice to meet you, Cesar! Tell us... what are you passionate about?")).check(
            matches(isDisplayed())
        )
    }
}
