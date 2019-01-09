package com.example.android.habitracker.data;

import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.android.habitracker.db.HabitDao;
import com.example.android.habitracker.vo.Habit;

import java.util.List;

import javax.inject.Inject;

public class HabitRepository {

    private HabitDao habitDao;

    @Inject
    public HabitRepository(HabitDao habitDao) {
        this.habitDao = habitDao;
    }

    public void addHabit(Habit habit) {
        new InsertHabitAsyncTask(habitDao).execute(habit);
    }

    public LiveData<List<Habit>> getHabits() {
        return habitDao.getHabits();
    }

    private static class InsertHabitAsyncTask extends AsyncTask<Habit, Void, Void> {

        private HabitDao habitDao;

        InsertHabitAsyncTask(HabitDao habitDao) {
            this.habitDao = habitDao;
        }

        @Override
        protected Void doInBackground(Habit... habits) {
            habitDao.insert(habits[0]);
            return null;
        }
    }
}
