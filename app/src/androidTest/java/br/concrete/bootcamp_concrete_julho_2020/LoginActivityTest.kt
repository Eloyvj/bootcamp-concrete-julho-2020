package br.concrete.bootcamp_concrete_julho_2020

import android.app.Activity
import android.app.Instrumentation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    private val email = "eloy@teste.com.br"
    private val shortPassword = "Eloyv@1"
    private val justLowerPassword = "eloyvj@1"
    private val justUpperPasword = "ELO#\$#@1"
    private val hasNotNumberPassword = "Eloyvj@#$"
    private val hasNotSpecialCharPassword = "EloyvjP1"
    private val correctPassword = "Eloyvj@1"

    @get:Rule
    val rule = IntentsTestRule(LoginActivity::class.java, true, false)

    @Test
    fun givenInitialState_shouldShowEmailAndPasswordEmpty(){
        rule.launchActivity(null)

        Espresso.onView(withId(R.id.email))
            .check(matches(withText("")))
        Espresso.onView(withId(R.id.password))
            .check(matches(withText("")))
    }

    @Test
    fun givenPasswordShortterThanEight_whenClickLogin_shouldShowMessageAboutShortLength(){
        rule.launchActivity(null)

        Espresso.onView(withId(R.id.email))
            .perform(ViewActions.replaceText(email), ViewActions.closeSoftKeyboard())
        Espresso.onView(withId(R.id.password))
            .perform(ViewActions.replaceText(shortPassword), ViewActions.closeSoftKeyboard())
        Espresso.onView((withId(R.id.login)))
            .perform(ViewActions.click())
        Espresso.onView(withText(containsString("Oito caracteres."))).inRoot(isDialog()).check(matches(isDisplayed()))
    }

    @Test
    fun givenPasswordWithoutUpperCase_whenClickLogin_shouldShowMessageAboutUpperCase(){
        rule.launchActivity(null)

        Espresso.onView(withId(R.id.email))
            .perform(ViewActions.replaceText(email), ViewActions.closeSoftKeyboard())
        Espresso.onView(withId(R.id.password))
            .perform(ViewActions.replaceText(justLowerPassword), ViewActions.closeSoftKeyboard())
        Espresso.onView((withId(R.id.login)))
            .perform(ViewActions.click())
        Espresso.onView(withText(containsString("Uma letra maiúscula."))).inRoot(isDialog()).check(matches(isDisplayed()))
    }

    @Test
    fun givenPasswordWithoutLowerCase_whenClickLogin_shouldShowMessageAboutLowerCase(){
        rule.launchActivity(null)

        Espresso.onView(withId(R.id.email))
            .perform(ViewActions.replaceText(email), ViewActions.closeSoftKeyboard())
        Espresso.onView(withId(R.id.password))
            .perform(ViewActions.replaceText(justUpperPasword), ViewActions.closeSoftKeyboard())
        Espresso.onView((withId(R.id.login)))
            .perform(ViewActions.click())
        Espresso.onView(withText(containsString("Uma letra minúscula."))).inRoot(isDialog()).check(matches(isDisplayed()))
    }

    @Test
    fun givenPasswordWithoutNumber_whenClickLogin_shouldShowMessageAboutNumber(){
        rule.launchActivity(null)

        Espresso.onView(withId(R.id.email))
            .perform(ViewActions.replaceText(email), ViewActions.closeSoftKeyboard())
        Espresso.onView(withId(R.id.password))
            .perform(ViewActions.replaceText(hasNotNumberPassword), ViewActions.closeSoftKeyboard())
        Espresso.onView((withId(R.id.login)))
            .perform(ViewActions.click())
        Espresso.onView(withText(containsString("Um número."))).inRoot(isDialog()).check(matches(isDisplayed()))
    }

    @Test
    fun givenPasswordWithoutSpecialChar_whenClickLogin_shouldShowMessageAboutSpecialChar(){
        rule.launchActivity(null)

        Espresso.onView(withId(R.id.email))
            .perform(ViewActions.replaceText(email), ViewActions.closeSoftKeyboard())
        Espresso.onView(withId(R.id.password))
            .perform(ViewActions.replaceText(hasNotSpecialCharPassword), ViewActions.closeSoftKeyboard())
        Espresso.onView((withId(R.id.login)))
            .perform(ViewActions.click())
        Espresso.onView(withText(containsString("Um caracter especial"))).inRoot(isDialog()).check(matches(isDisplayed()))
    }

    @Test
    fun givenEmailAndPasswordFilled_whenClickClear_shouldClearAllFields(){
        rule.launchActivity(null)

        Espresso.onView(withId(R.id.email))
            .perform(ViewActions.replaceText(email), ViewActions.closeSoftKeyboard())
        Espresso.onView(withId(R.id.password))
            .perform(ViewActions.replaceText(hasNotSpecialCharPassword), ViewActions.closeSoftKeyboard())
        Espresso.onView((withId(R.id.clear)))
            .perform(ViewActions.click())
        Espresso.onView(withId(R.id.email))
            .check(matches(withText("")))
        Espresso.onView(withId(R.id.password))
            .check(matches(withText("")))
    }

    @Test
    fun givenEmailEmpty_whenClickLogin_shouldShowMessageEmailEmpty(){
        rule.launchActivity(null)

        Espresso.onView(withId(R.id.password))
            .perform(ViewActions.replaceText(correctPassword), ViewActions.closeSoftKeyboard())
        Espresso.onView((withId(R.id.login)))
            .perform(ViewActions.click())
        Espresso.onView(withText(containsString("O campo email está vazio."))).inRoot(isDialog()).check(matches(isDisplayed()))
    }

    @Test
    fun givenPasswordEmpty_whenClickLogin_shouldShowMessagePasswordEmpty(){
        rule.launchActivity(null)

        Espresso.onView(withId(R.id.email))
            .perform(ViewActions.replaceText(email), ViewActions.closeSoftKeyboard())
        Espresso.onView((withId(R.id.login)))
            .perform(ViewActions.click())
        Espresso.onView(withText(containsString("O campo password está vazio."))).inRoot(isDialog()).check(matches(isDisplayed()))
    }

    @Test
    fun givenCorrectPassword_whenClickLogin_shouldSendIntentForNextActivity(){

        rule.launchActivity(null)

        Intents.intending(IntentMatchers.hasComponent(SecondActivity::class.java.name))
            .respondWith(Instrumentation.ActivityResult(Activity.RESULT_CANCELED, null))

        Espresso.onView(withId(R.id.email))
            .perform(ViewActions.replaceText(email), ViewActions.closeSoftKeyboard())
        Espresso.onView(withId(R.id.password))
            .perform(ViewActions.replaceText(correctPassword), ViewActions.closeSoftKeyboard())
        Espresso.onView((withId(R.id.login)))
            .perform(ViewActions.click())
        Intents.intended(IntentMatchers.hasComponent(SecondActivity::class.java.name))
    }
}