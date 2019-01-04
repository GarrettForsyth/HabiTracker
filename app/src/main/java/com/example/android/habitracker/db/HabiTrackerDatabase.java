package com.example.android.habitracker.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.android.habitracker.vo.Habit;

@Database(
        entities = { Habit.class },
        version = 1,
        exportSchema = false
)
@TypeConverters(HabiTrackerConverters.class)
public abstract class HabiTrackerDatabase extends RoomDatabase {

    public abstract HabitDao habitDao();
}
