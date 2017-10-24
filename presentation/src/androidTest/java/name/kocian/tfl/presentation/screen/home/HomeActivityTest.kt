package name.kocian.tfl.presentation.screen.home

import android.support.test.runner.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest : HomeDefinitions(
        feature = "Home screen",
        description = "Sample usage") {

    @Test
    fun displayHomeScreen() {
        scenario = "Opens the application screen and displays sample text."

        Given.server.willRespondWithSampleData()
        Given.user.hasOpenedHomeScreen()

        When.user.clicksOnSampleText()
        Then.user.willSeeSampleText()
    }
}
