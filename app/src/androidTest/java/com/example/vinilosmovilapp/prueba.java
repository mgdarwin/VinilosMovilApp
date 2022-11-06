package com.example.vinilosmovilapp;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.vinilosmovilapp.R;
import com.example.vinilosmovilapp.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class prueba {
    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void mainActivityTest2() {

        try {

            Thread.sleep(10000);

        } catch (InterruptedException e) {

        }

        ViewInteraction skipBtn = onView(allOf(withId(R.id.albumNametext), withText("Poeta del pueblo"),isDisplayed()));
        skipBtn.perform(click());


    }
}
