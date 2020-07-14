package br.concrete.bootcamp_concrete_julho_2020

import android.app.Activity
import android.app.Instrumentation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.CoreMatchers

class loginArrange(action: loginArrange.() -> Unit) {

    init {
        action.invoke(this)
    }

    fun mockHomeActivity(){
        Intents.intending(hasComponent(SecondActivity::class.java.name))
            .respondWith(Instrumentation.ActivityResult(Activity.RESULT_CANCELED, null))
    }

}

class loginAct(action: loginAct.() -> Unit) {

    init {
        action.invoke(this)
    }

    fun typeText(text: String, id: Int) {
        Espresso.onView(ViewMatchers.withId(id))
            .perform(ViewActions.replaceText(text), ViewActions.closeSoftKeyboard())

    }

    fun click(id: Int){
        Espresso.onView(ViewMatchers.withId(id)).perform(ViewActions.click())
    }
}

class loginAssert(action: loginAssert.() -> Unit) {

    init {
        action.invoke(this)
    }

    fun checkMessageShown(message: String){
        Espresso.onView(ViewMatchers.withText(message))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    fun checkAlertDialogContainsText(message: String) {
        Espresso.onView(ViewMatchers.withText(CoreMatchers.containsString(message))).inRoot(
            RootMatchers.isDialog()
        ).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    fun checkGoTo(activityName: String){
        Intents.intended(hasComponent(activityName))
    }

    fun checkTextShown(text: String, id: Int) {
        Espresso.onView(ViewMatchers.withId(id))
            .check(ViewAssertions.matches(ViewMatchers.withText(text)))
    }
}