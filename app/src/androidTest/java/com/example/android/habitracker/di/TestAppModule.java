package com.example.android.habitracker.di;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.android.habitracker.MainActivity;
import com.example.android.habitracker.data.HabitRepository;
import com.example.android.habitracker.db.HabiTrackerDatabase;
import com.example.android.habitracker.db.HabitDao;
import com.example.android.habitracker.testing.TestUtils;

import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module(includes = {ViewModelModule.class})
public abstract class TestAppModule {

    @ContributesAndroidInjector(modules = {FragmentBuildersModule.class})
    abstract MainActivity contributeActivityInjector();

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
    static HabiTrackerDatabase provideDb(Application app) {
        final HabiTrackerDatabase[] habiTrackerDatabases = new HabiTrackerDatabase[1];
        habiTrackerDatabases[0] = Room.databaseBuilder(
                app,
                HabiTrackerDatabase.class,
                "test_habits.db"
        ).allowMainThreadQueries()
                .addCallback(new RoomDatabase.Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                habiTrackerDatabases[0]
                                        .habitDao()
                                        .insert(TestUtils.createHabitList(20));
                            }
                        });
                    }
                })
                .build();
        return habiTrackerDatabases[0];
    }

}
