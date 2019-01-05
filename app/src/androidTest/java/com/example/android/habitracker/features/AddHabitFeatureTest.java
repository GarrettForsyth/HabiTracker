package com.example.android.habitracker.features;

import android.widget.TimePicker;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.example.android.habitracker.MainActivity;
import com.example.android.habitracker.R;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.core.AllOf.allOf;


/**
 * As a user
 * I want to add a habit
 * So I can track it
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddHabitFeatureTest {

    @Rule
    public ActivityTestRule activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void userSuccessfullyAddsHabit() {

        // click the add habit button
        onView(withId(R.id.fab)).perform(click());

        // check title of habit screen is displayed
        onView(withText("Habit")).check(matches(isDisplayed()));

        // add habit attributes
        onView(withId(R.id.habit_name)).perform(typeText("Walk dog"));
        closeSoftKeyboard();
        onView(withId(R.id.habit_description)).perform(typeText("Take Bob around the block"));
        closeSoftKeyboard();

        onView(withId(R.id.habit_frequency_spinner)).perform(click());
        onData(anything()).atPosition(0).perform(click());
        onView(withId(R.id.habit_frequency_spinner)).check(matches(withSpinnerText("Daily")));

        onView(withId(R.id.habit_reminder_time)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName())))
                .perform(PickerActions.setTime(7, 30));
        onView(withId(android.R.id.button1)).perform(click());

        // save habit
        onView(withId(R.id.save_habit_button)).perform(click());

        //TODO
        // The habit is shown in the habit collection
    }

    @Test
    public void showsSnackBarWhenNameIsInvalid() {

        // click the add habit button
        onView(withId(R.id.fab)).perform(click());

        // check title of habit screen is displayed
        onView(withText("Habit")).check(matches(isDisplayed()));

        // add habit attributes
        // onView(withId(R.id.habit_name)).perform(typeText("Walk dog"));
        onView(withId(R.id.habit_description)).perform(typeText("Take Bob around the block"));
        closeSoftKeyboard();

        onView(withId(R.id.habit_frequency_spinner)).perform(click());
        onData(anything()).atPosition(0).perform(click());
        onView(withId(R.id.habit_frequency_spinner)).check(matches(withSpinnerText("Daily")));

        onView(withId(R.id.habit_reminder_time)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName())))
                .perform(PickerActions.setTime(7, 30));
        onView(withId(android.R.id.button1)).perform(click());

        // save habit
        onView(withId(R.id.save_habit_button)).perform(click());

        onView(allOf(
                withId(com.google.android.material.R.id.snackbar_text),
                withText(R.string.invalid_habit_name)
        )).check(matches(isDisplayed()));
    }

    @Test
    public void showsSnackBarWhenDescriptionIsBlank() {

        // click the add habit button
        onView(withId(R.id.fab)).perform(click());

        // check title of habit screen is displayed
        onView(withText("Habit")).check(matches(isDisplayed()));

        // add habit attributes
        onView(withId(R.id.habit_name)).perform(typeText("Walk dog"));
        closeSoftKeyboard();
        //onView(withId(R.id.habit_description)).perform(typeText("Take Bob around the block"));

        onView(withId(R.id.habit_frequency_spinner)).perform(click());
        onData(anything()).atPosition(0).perform(click());
        onView(withId(R.id.habit_frequency_spinner)).check(matches(withSpinnerText("Daily")));

        onView(withId(R.id.habit_reminder_time)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName())))
                .perform(PickerActions.setTime(7, 30));
        onView(withId(android.R.id.button1)).perform(click());

        // save habit
        onView(withId(R.id.save_habit_button)).perform(click());

        onView(allOf(
                withId(com.google.android.material.R.id.snackbar_text),
                withText(R.string.invalid_habit_description)
        )).check(matches(isDisplayed()));
    }

    @Test
    public void showsSnackBarWhenReminderTimeIsBlank() {

        // click the add habit button
        onView(withId(R.id.fab)).perform(click());

        // check title of habit screen is displayed
        onView(withText("Habit")).check(matches(isDisplayed()));

        // add habit attributes
         onView(withId(R.id.habit_name)).perform(typeText("Walk dog"));
        closeSoftKeyboard();
        onView(withId(R.id.habit_description)).perform(typeText("Take Bob around the block"));
        closeSoftKeyboard();


        onView(withId(R.id.habit_frequency_spinner)).perform(click());
        onData(anything()).atPosition(0).perform(click());
        onView(withId(R.id.habit_frequency_spinner)).check(matches(withSpinnerText("Daily")));

//        onView(withId(R.id.habit_reminder_time)).perform(click());
//        onView(withClassName(Matchers.equalTo(TimePicker.class.getName())))
//                .perform(PickerActions.setTime(7, 30));
//        onView(withId(android.R.id.button1)).perform(click());


        // save habit
        onView(withId(R.id.save_habit_button)).perform(click());

        onView(allOf(
                withId(com.google.android.material.R.id.snackbar_text),
                withText(R.string.invalid_habit_reminder_time)
        )).check(matches(isDisplayed()));
    }

    @Test
    public void showsSnackBarWhenMultipleBlankFields() {

        // click the add habit button
        onView(withId(R.id.fab)).perform(click());

        // check title of habit screen is displayed
        onView(withText("Habit")).check(matches(isDisplayed()));

        // save habit
        onView(withId(R.id.save_habit_button)).perform(click());

        onView(allOf(
                withId(com.google.android.material.R.id.snackbar_text),
                withText(R.string.invalid_habit_name)
        )).check(matches(isDisplayed()));
    }


}
