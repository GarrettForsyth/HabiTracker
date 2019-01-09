package com.example.android.habitracker.features;

import android.widget.TimePicker;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.example.android.habitracker.MainActivity;
import com.example.android.habitracker.R;

import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
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

    @BeforeClass
    public static void beforeClass() {
        InstrumentationRegistry.getInstrumentation()
                .getTargetContext().deleteDatabase("test_habits.db");
    }

    @Test
    public void userSuccessfullyAddsHabit() {

        String habitName = "Water plants.";
        String habitDescription = "Thirsty plants need water.";

        // click the add habit button
        onView(withId(R.id.fab)).perform(click());

        // check title of habit screen is displayed
        onView(withText("Habit")).check(matches(isDisplayed()));

        // add habit attributes
        onView(withId(R.id.habit_name)).perform(typeText(habitName));
        closeSoftKeyboard();
        onView(withId(R.id.habit_description)).perform(typeText(habitDescription));
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

        // return to the habits fragment
        onView(withText("Habits")).check(matches(isDisplayed()));

        // the new view is added to the bottom of the list
        onView(withId(R.id.habits_recycler_view))
                .perform(RecyclerViewActions.scrollToPosition(20));
        onView(withText(habitName)).check(matches(isDisplayed()));

    }

    @Test
    public void pressingBackReturnsToHabitsFragment() {
        // click the add habit button
        onView(withId(R.id.fab)).perform(click());

        // check title of habit screen is displayed
        onView(withText("Habit")).check(matches(isDisplayed()));

        pressBack();

        // check title of habit screen is displayed
        onView(withText("Habits")).check(matches(isDisplayed()));



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

        // REMINDER TIME NOT SET
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
