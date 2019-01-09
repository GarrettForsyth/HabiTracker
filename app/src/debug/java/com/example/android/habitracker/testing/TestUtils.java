package com.example.android.habitracker.testing;

import com.example.android.habitracker.vo.Habit;
import com.example.android.habitracker.vo.HabitFrequency;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TestUtils {

    public static Habit createHabit() {
        return new Habit(
                "Test Habit Name",
                "Test Habit Description",
                HabitFrequency.DAILY,
                Calendar.getInstance().getTimeInMillis()
        );
    }

    public static Habit[] createHabitList(int listLength) {
        if (listLength <= 0) return null;
        Habit[] habits = new Habit[listLength];

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 6);
        calendar.set(Calendar.MINUTE, 30);

        habits[0] = new Habit(
                "Walk dog.",
                "Take Bob around the block.",
                HabitFrequency.DAILY,
                calendar.getTimeInMillis()
        );

        for(int i = 1; i < listLength; i++) {
            habits[i] = (new Habit(
                    "Test Habit Name " + i,
                    "Test Habit Description " + i,
                    HabitFrequency.DAILY,
                    Calendar.getInstance().getTimeInMillis()

            ));
        }

        return habits;
    }
}
