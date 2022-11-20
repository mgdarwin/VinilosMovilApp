package com.example.vinilosmovilapp;

import androidx.test.espresso.action.ViewActions;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.example.vinilosmovilapp.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.adevinta.android.barista.interaction.BaristaMenuClickInteractions.clickMenu;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class testE2E_hu003_nav {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() throws InterruptedException {

        try {
            Thread.sleep(10000);

            clickMenu(R.id.ico_artists);

            Thread.sleep(1000);

            onView(withId(R.id.artistsRv)).check(new RecyclerViewItemCountAssertion(1));

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

            Thread.sleep(5000);
        }
        catch (Exception e){
            throw  e;
        }
    }
}
