package com.example.android.habitracker.db;

import androidx.room.TypeConverter;

import com.example.android.habitracker.vo.HabitFrequency;

public class HabiTrackerConverters {

    @TypeConverter
    public HabitFrequency fromValue(int value) {
        HabitFrequency habitFrequency;

        switch (value) {
            case 0:
                habitFrequency = HabitFrequency.DAILY;
                break;
            case 1:
                habitFrequency = HabitFrequency.WEEKLY;
                break;
            case 2:
                habitFrequency = HabitFrequency.MONTHLY;
                break;
            default:
                habitFrequency = null;
        }

        return habitFrequency;
    }

    @TypeConverter
    public int fromHabitFrequency(HabitFrequency habitFrequency) {
        int value = -1;

        switch (habitFrequency) {
            case DAILY:
                value = 0;
                break;
            case WEEKLY:
                value = 1;
                break;
            case MONTHLY:
                value = 2;
                break;
            default:
                value = -1;
        }

        return value;
    }
}
