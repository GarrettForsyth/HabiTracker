package com.example.android.habitracker;

import com.example.android.habitracker.vo.Habit;
import com.example.android.habitracker.vo.HabitFrequency;

import java.util.Calendar;

public class TestUtils {

    public static Habit createHabit() {
        return new Habit(
                "Test Habit Name",
                "Test Habit Description",
                HabitFrequency.DAILY,
                Calendar.getInstance().getTimeInMillis()
        );
    }
}
