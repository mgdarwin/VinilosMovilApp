package com.example.vinilosmovilapp;

import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.example.vinilosmovilapp.ui.MainActivity;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.adevinta.android.barista.interaction.BaristaMenuClickInteractions.clickMenu;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class testE2E_hu005_items {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() throws InterruptedException {

        try {
            Thread.sleep(10000);

            clickMenu(R.id.ico_collectors);

            Thread.sleep(1000);

            onView(withId(R.id.collectorsRv)).check(new RecyclerViewItemCountAssertion(1));
        }
        catch (Exception e){
            throw e;
        }
    }
}
