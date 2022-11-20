package com.example.vinilosmovilapp;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
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

import android.view.View;
import android.widget.TextView;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class testE2E_hu002_tittle {
    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void mainActivityTest2() throws InterruptedException {

        try {
            Thread.sleep(10000);

            onView(withId(R.id.albumsRv)).check(new RecyclerViewItemCountAssertion(1));

            final String[] title = {"",""};
            onView(withId(R.id.albumsRv)).check(new ViewAssertion() {
                 @Override
                 public void check(View view, NoMatchingViewException noViewFoundException) {
                     if (noViewFoundException != null) {
                         throw noViewFoundException;
                     }
                     RecyclerView recyclerView = (RecyclerView) view;
                     title[0] = ((TextView) recyclerView.findViewHolderForAdapterPosition(0).
                             itemView.findViewById(R.id.albumNametext)).getText().toString();

                     title[1] = ((TextView) recyclerView.findViewHolderForAdapterPosition(1).
                             itemView.findViewById(R.id.albumNametext)).getText().toString();
                 }
            }
            );
            onView(allOf(withId(R.id.albumsRv), isDisplayed()))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

            Thread.sleep(5000);

            ViewInteraction tittle = onView(allOf(withId(R.id.albumTittle), withText(title[0]),isDisplayed()));
            tittle.perform(click());

            Thread.sleep(5000);

            onView(isRoot()).perform(ViewActions.pressBack());
            onView(allOf(withId(R.id.albumsRv), isDisplayed()))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

            Thread.sleep(5000);

            ViewInteraction tittle2 = onView(allOf(withId(R.id.albumTittle), withText(title[1]),isDisplayed()));
            tittle2.perform(click());

            Thread.sleep(5000);

            onView(isRoot()).perform(ViewActions.pressBack());

            Thread.sleep(5000);
        } catch (Exception e) {
            throw e;
        }
    }
}
