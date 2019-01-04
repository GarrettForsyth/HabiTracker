package com.example.android.habitracker.data;

import com.example.android.habitracker.db.HabitDao;
import com.example.android.habitracker.vo.Habit;

public class HabitRepository {

    private HabitDao habitDao;

    public HabitRepository(HabitDao habitDao) {
        this.habitDao = habitDao;
    }

    public void addHabit(Habit habit) {
        habitDao.insert(habit);
    }
}
