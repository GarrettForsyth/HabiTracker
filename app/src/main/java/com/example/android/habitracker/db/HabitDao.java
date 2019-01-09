package com.example.android.habitracker.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.android.habitracker.vo.Habit;

import java.util.List;

@Dao
public abstract class HabitDao {

    @Insert
    public abstract void insert(Habit habit);

    @Insert
    public abstract void insert(Habit... habits);

    @Query("SELECT * FROM habit")
    public abstract LiveData<List<Habit>> getHabits();
}
