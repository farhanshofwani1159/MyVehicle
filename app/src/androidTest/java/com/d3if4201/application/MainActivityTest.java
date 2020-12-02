package com.d3if4201.application;

import androidx.test.core.app.ActivityScenario;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Test
    public void cameraTest() {
        ActivityScenario activityScenario = ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.cameraImg)).perform(click());
        onView(withId(R.id.etLocation)).check(matches(isDisplayed()));
        onView(withId(R.id.etLocation)).perform(click());
        onView(withId(R.id.etLocation)).perform(typeText("DESKRIPSI_DUMMY"));
//        onView(withId(R.id.btnSubmit)).perform(click());
//        onView(withId(R.id.switch1)).check(matches(isDisplayed()));
        activityScenario.close();
    }

    public void searchTest() {
//        code here...
    }
    @Test
    public void bookTest() {
        ActivityScenario activityScenario = ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.bukuImg)).perform(click());
        onView(withId(R.id.imgUser)).check(matches(isDisplayed()));
        activityScenario.close();
    }
    @Test
    public void modeTest() {
        ActivityScenario activityScenario = ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.switch1)).perform(click());
        onView(withId(R.id.switch1)).perform(click());
        onView(withId(R.id.switch1)).perform(click());
        onView(withId(R.id.switch1)).perform(click());
        onView(withId(R.id.switch1)).perform(click());
        onView(withId(R.id.switch1)).perform(click());
        onView(withId(R.id.switch1)).perform(click());
        onView(withId(R.id.switch1)).check(matches(isDisplayed()));
        activityScenario.close();
    }
}