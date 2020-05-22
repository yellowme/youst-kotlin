package mx.yellowme.youst.playground.navigation

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import mx.yellowme.youst.miniapps.R
import mx.yellowme.youst.miniapps.ui.contact.GreetingFragment
import mx.yellowme.youst.miniapps.ui.contact.GreetingFragmentDirections
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@MediumTest
@RunWith(AndroidJUnit4::class)
class GreetingFragmentTest {
    @Test
    fun clickNavigateButton_navigateToSecondDestinationFragment() {
        val scenario = launchFragmentInContainer<GreetingFragment>(Bundle(), R.style.SharedAppTheme)
        val navController = mock(NavController::class.java)

        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }

        onView(withId(R.id.navigateButton)).perform(click())

        verify(navController).navigate(
            GreetingFragmentDirections.toSecondDestination("")
        )
    }
}
