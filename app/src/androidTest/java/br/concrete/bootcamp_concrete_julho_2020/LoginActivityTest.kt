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
    val loginActivityTest = IntentsTestRule(LoginActivity::class.java)

    @get:Rule
    val disableAnimationRule = DisableAnimationRule()

    @Test
    fun givenInitialState_shouldShowEmailAndPasswordEmpty(){

        loginArrange {
            mockHomeActivity()
        }

        loginAssert {
            checkTextShown("", R.id.email)
            checkTextShown("", R.id.password)
        }
    }

    @Test
    fun givenPasswordShortterThanEight_whenClickLogin_shouldShowMessageAboutShortLength(){

        loginArrange {
            mockHomeActivity()
        }

        loginAct {
            typeText(email, R.id.email)
            typeText(shortPassword, R.id.password)
            click(R.id.login)
        }

        loginAssert {
            checkAlertDialogContainsText("Oito caracteres.")
        }
    }

    @Test
    fun givenPasswordWithoutUpperCase_whenClickLogin_shouldShowMessageAboutUpperCase(){

        loginArrange {
            mockHomeActivity()
        }

        loginAct {
            typeText(email, R.id.email)
            typeText(justLowerPassword, R.id.password)
            click(R.id.login)
        }

        loginAssert {
            checkAlertDialogContainsText("Uma letra maiúscula.")
        }
    }

    @Test
    fun givenPasswordWithoutLowerCase_whenClickLogin_shouldShowMessageAboutLowerCase(){

        loginArrange {
            mockHomeActivity()
        }

        loginAct {
            typeText(email, R.id.email)
            typeText(justUpperPasword, R.id.password)
            click(R.id.login)
        }

        loginAssert {
            checkAlertDialogContainsText("Uma letra minúscula.")
        }
    }

    @Test
    fun givenPasswordWithoutNumber_whenClickLogin_shouldShowMessageAboutNumber(){

        loginArrange {
            mockHomeActivity()
        }

        loginAct {
            typeText(email, R.id.email)
            typeText(hasNotNumberPassword, R.id.password)
            click(R.id.login)
        }

        loginAssert {
            checkAlertDialogContainsText("Um número.")
        }
    }

    @Test
    fun givenPasswordWithoutSpecialChar_whenClickLogin_shouldShowMessageAboutSpecialChar(){

        loginArrange {
            mockHomeActivity()
        }

        loginAct {
            typeText(email, R.id.email)
            typeText(hasNotSpecialCharPassword, R.id.password)
            click(R.id.login)
        }

        loginAssert {
            checkAlertDialogContainsText("Um caracter especial.")
        }
    }

    @Test
    fun givenEmailAndPasswordFilled_whenClickClear_shouldClearAllFields(){

        loginArrange {
            mockHomeActivity()
        }

        loginAct {
            typeText(email, R.id.email)
            typeText(correctPassword, R.id.password)
            click(R.id.clear)
        }

        loginAssert {
            checkTextShown("", R.id.email)
            checkTextShown("", R.id.password)
        }
    }

    @Test
    fun givenEmailEmpty_whenClickLogin_shouldShowMessageEmailEmpty(){

        loginArrange {
            mockHomeActivity()
        }

        loginAct {
            typeText(correctPassword, R.id.password)
            click(R.id.login)
        }

        loginAssert {
            checkAlertDialogContainsText("O campo email está vazio.")
        }
    }

    @Test
    fun givenPasswordEmpty_whenClickLogin_shouldShowMessagePasswordEmpty(){

        loginArrange {
            mockHomeActivity()
        }

        loginAct {
            typeText(email, R.id.email)
            click(R.id.login)
        }

        loginAssert {
            checkAlertDialogContainsText("O campo password está vazio.")
        }
    }

    @Test
    fun givenCorrectPassword_whenClickLogin_shouldSendIntentForNextActivity(){

        loginArrange {
            mockHomeActivity()
        }

        loginAct {
            typeText(email, R.id.email)
            typeText(correctPassword, R.id.password)
            click(R.id.login)
        }

        loginAssert {
            checkGoTo(SecondActivity::class.java.name)
        }
    }
}