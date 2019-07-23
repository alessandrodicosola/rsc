package it.poliba.adicosola1.rsclient

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import it.poliba.adicosola1.rsclient.common.di.AlwaysOnline
import it.poliba.adicosola1.rsclient.common.util.IConnectivity
import it.poliba.adicosola1.rsclient.ui.login.LoginActivity
import it.poliba.adicosola1.rsclient.ui.presenter.PresenterActivity
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.qualifier.named
import org.koin.test.KoinTest
import org.koin.test.get


const val USER = "test_username"
const val PASSWD = "test_password"

@RunWith(AndroidJUnit4::class)
class UI_Login_Activity_Test : KoinTest {

    @get:Rule
    val rule = IntentsTestRule(LoginActivity::class.java)


    @Test
    fun test_IConnectivity_is_alwaysonline() {
        val con = get<IConnectivity>(named<LoginActivity>())
        Assert.assertTrue(con.toString(), con is AlwaysOnline)
    }

    @Test
    fun test_empty_username_and_password() {
        onView(withId(R.id.usernameText)).check(matches(withText("")))
        onView(withId(R.id.passwordText)).check(matches(withText("")))
    }

    @Test
    fun test_error_on_empty_username() {

        onView(withId(R.id.usernameText)).check(matches(withText("")))
        onView(withId(R.id.passwordText))
            .perform(replaceText(PASSWD))
            .perform(closeSoftKeyboard())
            .perform(pressImeActionButton())

        onView(withId(R.id.usernameText)).check(matches(hasErrorText("You must insert an username")))

    }

    @Test
    fun test_error_on_empty_password() {
        onView(withId(R.id.usernameText)).perform(typeText(USER))
        onView(withId(R.id.passwordText)).check(matches(withText("")))
            .perform(pressImeActionButton())

        onView(withId(R.id.passwordText)).check(matches(hasErrorText("You must insert a password")))

    }

    @Test
    fun test_text_login_activity() {
        onView(withId(R.id.usernameText)).perform(typeText(USER))
        onView(withId(R.id.passwordText))
            .perform(replaceText(PASSWD))
            .perform(closeSoftKeyboard())

        onView(withId(R.id.usernameText)).check(matches(withText(USER)))
        onView(withId(R.id.passwordText)).check(matches(withText(PASSWD)))

    }

    @Test
    fun test_login() {
        onView(withId(R.id.usernameText)).perform(typeText(USER))
        onView(withId(R.id.passwordText))
            .perform(replaceText(PASSWD))
            .perform(closeSoftKeyboard())
            .perform(pressImeActionButton())
        //-is launched PresenterActivity
        intended(hasComponent(PresenterActivity::class.java.name))
        //with id and name extras
        intended(hasExtraWithKey("id"))
        intended(hasExtraWithKey("name"))
    }


}

