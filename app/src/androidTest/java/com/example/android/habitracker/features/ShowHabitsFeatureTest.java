package com.example.android.habitracker.features;


import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.example.android.habitracker.MainActivity;
import com.example.android.habitracker.R;
import com.example.android.habitracker.testing.TestUtils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * As a user
 * I want to see a list of all my habits
 * So I can get an overview of my habits
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class ShowHabitsFeatureTest {

    @Rule
    public ActivityTestRule activityTestRule = new ActivityTestRule<>(MainActivity.class);

    /*
     * Note: Dagger injects a database that is prepopulated in the test configuration.
     */
    @Test
    public void habitAttributesAreDisplayed() {
        onView(withId(R.id.habits_recycler_view))
        .check(matches(hasDescendant(withText("Walk dog."))));

        onView(withId(R.id.habits_recycler_view))
                .check(matches(hasDescendant(withText("Take Bob around the block."))));

        onView(withId(R.id.habits_recycler_view))
                .check(matches(hasDescendant(withText("06:30"))));
    }

    @Test
    public void lastHabitIsDisplayed() {
        onView(withId(R.id.habits_recycler_view))
                .perform(RecyclerViewActions.scrollToPosition(19));
        onView(withText("Test Habit Name 19")).check(matches(isDisplayed()));
    }

}
