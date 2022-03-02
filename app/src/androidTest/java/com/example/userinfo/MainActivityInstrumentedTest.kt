package com.example.userinfo

import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {
    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun shouldTakeTheValidInputFieldsAndDisplayConfirmButtonOnClickingValidate(){
        Espresso.onView(withId(R.id.editTextViewUserName)).perform(ViewActions.typeText("Saicharan"), closeSoftKeyboard())
        Espresso.onView(withId(R.id.editTextViewEmail)).perform(ViewActions.typeText("sai@gmail.com"), closeSoftKeyboard())
        Espresso.onView(withId(R.id.editTextViewPhone)).perform(ViewActions.typeText("9000111222"), closeSoftKeyboard())
        Espresso.onView(withId(R.id.editTextViewPincode))
            .perform(ViewActions.typeText("506001"), closeSoftKeyboard())
        Espresso.onView(withId(R.id.editTextViewAddress))
            .perform(ViewActions.typeText("HNK"), closeSoftKeyboard())
        Espresso.onView(withId(R.id.buttonValidate)).perform(ViewActions.click())

        Espresso.onView(withId(R.id.buttonConfirm))
            .check(ViewAssertions.matches(isClickable()))
        Espresso.onView(withId(R.id.buttonCancel))
            .check(ViewAssertions.matches(isClickable()))

        Espresso.onView(withId(R.id.editTextViewUserName)).check(ViewAssertions.matches(isNotEnabled()))
        Espresso.onView(withId(R.id.editTextViewEmail)).check(ViewAssertions.matches(isNotEnabled()))
        Espresso.onView(withId(R.id.editTextViewPhone)).check(ViewAssertions.matches(isNotEnabled()))
        Espresso.onView(withId(R.id.editTextViewPincode)).check(ViewAssertions.matches(isNotEnabled()))
        Espresso.onView(withId(R.id.editTextViewAddress)).check(ViewAssertions.matches(isNotEnabled()))

    }
}