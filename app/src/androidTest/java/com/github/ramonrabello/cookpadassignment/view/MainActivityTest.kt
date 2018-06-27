package com.github.ramonrabello.cookpadassignment.view

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.runner.AndroidJUnit4
import com.github.ramonrabello.cookpadassignment.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumentation tests for class [MainActivity].
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityRule = IntentsTestRule(MainActivity::class.java)

    @Test
    fun whenClickOnAddButton_shouldOpenNewCandidateActivity() {
        onView(withId(R.id.fab)).perform(click())
        intended(hasComponent("com.github.ramonrabello.cookpadassignment.view.NewCandidateActivity"))
    }

    @Test
    fun whenActivityStarted_checkIfAllViewsAreVisible() {
        onView(withId(R.id.toolbar)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.candidate_list)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.fab)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }
}