package com.pajato.acc

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.Visibility.VISIBLE
import android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    /** Define the component under test using a JUnit rule. */
    @Rule @JvmField var activityRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    /** Nop test provided by AS. */
    @Test fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.pajato.acc", appContext.packageName)
    }

    /** Check that the initial display shows the main activity view. */
    @Test fun testInitialState() {
        onView(withId(R.id.helloWorldText)).check(matches(withEffectiveVisibility(VISIBLE)))
    }
}
