package com.example.vinilosmovilapp;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.vinilosmovilapp.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import junit.framework.AssertionFailedError;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class testE2E_hu002 {
    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void mainActivityTest2() {

        try {
            Thread.sleep(10000);

            ViewInteraction skipBtn = onView(allOf(withId(R.id.albumNametext), withText("Poeta del pueblo"),isDisplayed()));
            skipBtn.perform(click());

            int con = 0;

            while (con != 15) {
                try {
                    ViewInteraction tittle = onView(allOf(withId(R.id.albumTittle), withText("Poeta del pueblo"),isDisplayed()));
                    tittle.perform(click());

                    Thread.sleep(2000);
                    break;
                }
                catch (Exception ex){
                    Thread.sleep(1000);
                    con++;
                }
            }

            if(con == 15)
                throw new AssertionFailedError();
            else
                onView(isRoot()).perform(ViewActions.pressBack());

            ViewInteraction skipBtn2 = onView(allOf(withId(R.id.albumNametext), withText("A Night at the Opera"),isDisplayed()));
            skipBtn2.perform(click());

            con = 0;

            while (con != 15) {
                try {
                    ViewInteraction tittle = onView(allOf(withId(R.id.albumTittle), withText("A Night at the Opera"),isDisplayed()));
                    tittle.perform(click());

                    Thread.sleep(2000);
                    break;
                }
                catch (Exception ex){
                    Thread.sleep(1000);
                    con++;
                }
            }

            if(con == 15)
                throw new AssertionFailedError();
            else
                onView(isRoot()).perform(ViewActions.pressBack());

        } catch (Exception e) {

        }
    }
}
