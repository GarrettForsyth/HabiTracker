package com.example.android.habitracker.db;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.android.habitracker.vo.Habit;

@Dao
public abstract class HabitDao {

    @Insert
    public abstract void insert(Habit habit);
}
