package com.example.android.habitracker.di;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.example.android.habitracker.MainActivity;
import com.example.android.habitracker.data.HabitRepository;
import com.example.android.habitracker.db.HabiTrackerDatabase;
import com.example.android.habitracker.db.HabitDao;
import com.example.android.habitracker.ui.habit.HabitViewModel;
import com.example.android.habitracker.vo.Habit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AppModule {

    @ContributesAndroidInjector
    abstract MainActivity contributeActivityInjector();

    @Provides
    static ViewModel provideHabitViewModel(HabitRepository habitRepo) {
        return new HabitViewModel(habitRepo);
    }

    @Provides
    @Singleton
    static HabitRepository provideHabitRepository(HabitDao habitDao) {
        return new HabitRepository(habitDao);
    }

    @Provides
    @Singleton
    static HabitDao provideHabitDao(HabiTrackerDatabase db) {
        return db.habitDao();
    }

    @Provides
    @Singleton
    static HabiTrackerDatabase provideDb(Application app){
        return Room.databaseBuilder(
                app,
                HabiTrackerDatabase.class,
                "habits.db"
        ).build();
    }

}
