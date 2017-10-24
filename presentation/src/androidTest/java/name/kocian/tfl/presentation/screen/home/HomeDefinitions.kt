package name.kocian.tfl.presentation.screen.home

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import name.kocian.tfl.R
import name.kocian.tfl.presentation.base.MockWebServerRule
import name.kocian.tfl.presentation.base.UiTest
import name.kocian.tfl.presentation.data.ServerJson
import name.kocian.tfl.presentation.ui.sample.MainActivity
import org.junit.Rule

@Suppress("ClassName")
open class HomeDefinitions(feature: String, description: String)
    : UiTest(feature, description) {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Rule
    @JvmField
    val mockWebServerRule = MockWebServerRule()

    class Given {
        object server {
            fun willRespondWithSampleData() {
                MockWebServerRule.enqueue(ServerJson.SERVER_SAMPLE_RESPONSE)
            }
        }

        object user {
            fun hasOpenedHomeScreen() {
                Thread.sleep(50)
            }
        }
    }

    class When {
        object user {
            fun clicksOnSampleText() {
                onView(withId(R.id.welcome)).perform(click())
            }
        }
    }

    class Then {
        object user {
            fun willSeeSampleText() {
                onView(withId(R.id.welcome)).check(matches(withText("London transport")))
            }
        }
    }
}
