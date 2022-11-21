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
import static com.adevinta.android.barista.interaction.BaristaMenuClickInteractions.clickMenu;

import static org.hamcrest.Matchers.allOf;

import android.view.View;
import android.widget.TextView;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class testE2E_hu004_nav {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() throws InterruptedException {

        try {
            Thread.sleep(10000);

            clickMenu(R.id.ico_artists);

            Thread.sleep(1000);

            onView(withId(R.id.artistsRv)).check(new RecyclerViewItemCountAssertion(1));

            final String[] title = {""};
            onView(withId(R.id.artistsRv)).check(new ViewAssertion() {
                 @Override
                 public void check(View view, NoMatchingViewException noViewFoundException) {
                     if (noViewFoundException != null) {
                         throw noViewFoundException;
                     }
                     RecyclerView recyclerView = (RecyclerView) view;
                     title[0] = ((TextView) recyclerView.findViewHolderForAdapterPosition(0).
                             itemView.findViewById(R.id.artistName)).getText().toString();
                 }
            }
            );
            onView(allOf(withId(R.id.artistsRv), isDisplayed()))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

            Thread.sleep(1000);

            ViewInteraction tittle = onView(allOf(withId(R.id.artistTittle), withText(title[0]),isDisplayed()));
            tittle.perform(click());

            Thread.sleep(1000);

            clickMenu(R.id.ico_collectors);
            onView(withId(R.id.collectorsRv)).check(new RecyclerViewItemCountAssertion(1));

            Thread.sleep(1000);

            onView(isRoot()).perform(ViewActions.pressBack());

            Thread.sleep(1000);

            clickMenu(R.id.ico_albums);
            onView(withId(R.id.albumsRv)).check(new RecyclerViewItemCountAssertion(1));

            Thread.sleep(1000);

            onView(isRoot()).perform(ViewActions.pressBack());

            Thread.sleep(1000);

            ViewInteraction tittle2 = onView(allOf(withId(R.id.artistTittle), withText(title[0]),isDisplayed()));
            tittle2.perform(click());

            Thread.sleep(5000);
        }
        catch (Exception e){
            throw  e;
        }
    }
}
