package com.github.ramonrabello.cookpadassignment.view

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.closeSoftKeyboard
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withSpinnerText
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.runner.AndroidJUnit4
import com.github.ramonrabello.cookpadassignment.R
import com.github.ramonrabello.cookpadassignment.core.data.model.Candidate
import com.github.ramonrabello.cookpadassignment.view.NewCandidateActivity.Companion.EXTRA_CANDIDATE
import com.github.ramonrabello.cookpadassignment.view.NewCandidateActivity.Companion.EXTRA_IN_EDIT_MODE
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumentation tests for class [NewCandidateActivity].
 */
@RunWith(AndroidJUnit4::class)
class NewCandidateActivityTest {

    @get:Rule
    val activityRule = IntentsTestRule(NewCandidateActivity::class.java, true, false)

    @Test
    fun whenActivityStarted_shouldCheckIfAllViewsAreVisible() {
        activityRule.launchActivity(prepareDefaultIntent())
        onView(withId(R.id.toolbar)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.candidate_name_field)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.candidate_phone_number_field)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.candidate_email_field)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.assessment_grade_label)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.candidate_grade_spinner)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.save_button)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }

    private fun prepareIntentForEditMode(): Intent {
        // fill intent with extras needed to be in edit mode
        val intent = prepareDefaultIntent()
        val candidate = Candidate(1, "Ramon Rabello", "ramon.rabello@gmail.com", "+55119988998", 0)
        intent.putExtra(EXTRA_CANDIDATE, candidate)
        intent.putExtra(EXTRA_IN_EDIT_MODE, true)
        return intent
    }

    @Test
    fun whenActivityStartedWithExtras_inEditMode_checkIfAllFieldsAreFilled() {
        val intent = prepareIntentForEditMode()
        activityRule.launchActivity(intent)

        onView(withId(R.id.candidate_name_field)).check(matches(withText("Ramon Rabello")))
        onView(withId(R.id.candidate_phone_number_field)).check(matches(withText("+55119988998")))
        onView(withId(R.id.candidate_email_field)).check(matches(withText("ramon.rabello@gmail.com")))
        onView(withId(R.id.candidate_grade_spinner)).check(matches(withSpinnerText("A")))
    }

    @Test
    fun whenActivityStarted_fillAllFields() {
        val intent = prepareDefaultIntent()
        activityRule.launchActivity(intent)

        onView(withId(R.id.candidate_name_field)).perform(typeText("Ramon Rabello"), closeSoftKeyboard())
        onView(withId(R.id.candidate_phone_number_field)).perform(typeText("+55119988998"), closeSoftKeyboard())
        onView(withId(R.id.candidate_email_field)).perform(typeText("ramon.rabello@gmail.com"), closeSoftKeyboard())
        onView(withId(R.id.candidate_grade_spinner)).perform(click())
        onData(allOf(`is`(instanceOf(String::class.java)))).atPosition(3).perform(click())
    }

    private fun prepareDefaultIntent() =
            Intent(InstrumentationRegistry.getTargetContext(), NewCandidateActivity::class.java)
}