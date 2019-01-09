package com.example.android.habitracker.di;

import android.app.Application;

import androidx.room.Room;

import com.example.android.habitracker.data.HabitRepository;
import com.example.android.habitracker.db.HabiTrackerDatabase;
import com.example.android.habitracker.db.HabitDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module( includes = { ViewModelModule.class })
public abstract class AppModule {


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
