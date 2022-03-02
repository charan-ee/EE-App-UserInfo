package com.android.userinfo

import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserInputActivityInstrumentedTest {
    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(UserInputActivity::class.java)

    @Before
    fun setUpInputField() {
        onView(withId(R.id.editTextViewUserName))
            .perform(ViewActions.typeText("Saicharan"), closeSoftKeyboard())
        onView(withId(R.id.editTextViewEmail))
            .perform(ViewActions.typeText("sai@gmail.com"), closeSoftKeyboard())
        onView(withId(R.id.editTextViewPhone))
            .perform(ViewActions.typeText("9000111222"), closeSoftKeyboard())
        onView(withId(R.id.editTextViewPincode))
            .perform(ViewActions.typeText("506001"), closeSoftKeyboard())
        onView(withId(R.id.editTextViewAddress))
            .perform(ViewActions.typeText("HNK"), closeSoftKeyboard())
    }

    @Test
    fun shouldTakeTheValidInputFieldsAndDisplayConfirmAndCancelButtonOnClickingValidate() {
        onView(withId(R.id.buttonValidate)).perform(click())

        onView(withId(R.id.buttonConfirm))
            .check(matches(isClickable()))
        onView(withId(R.id.buttonCancel))
            .check(matches(isClickable()))
        onView(withId(R.id.buttonValidate))
            .check(matches(isNotClickable()))

        onView(withId(R.id.editTextViewUserName))
            .check(matches(isNotEnabled()))
        onView(withId(R.id.editTextViewEmail))
            .check(matches(isNotEnabled()))
        onView(withId(R.id.editTextViewPhone))
            .check(matches(isNotEnabled()))
        onView(withId(R.id.editTextViewPincode))
            .check(matches(isNotEnabled()))
        onView(withId(R.id.editTextViewAddress))
            .check(matches(isNotEnabled()))

    }

    @Test
    fun shouldEnableEditTextOnClickingCancelButton(){
        onView(withId(R.id.buttonValidate))
            .perform(click())
        onView(withId(R.id.buttonCancel))
            .perform(click())

        onView(withId(R.id.editTextViewUserName))
            .check(matches(isEnabled()))
        onView(withId(R.id.editTextViewEmail))
            .check(matches(isEnabled()))
        onView(withId(R.id.editTextViewPhone))
            .check(matches(isEnabled()))
        onView(withId(R.id.editTextViewPincode))
            .check(matches(isEnabled()))
        onView(withId(R.id.editTextViewAddress))
            .check(matches(isEnabled()))
    }

//    @Test
//    fun shouldDisplayToastMessageForInvalidInputs() {
//
//    }
//
//    @Test
//    fun shouldPassIntentOnClickofConfirmButton() {
//
//    }
}