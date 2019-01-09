package com.example.android.habitracker.di;

import com.example.android.habitracker.ui.habit.EditHabitFragment;
import com.example.android.habitracker.ui.habit.HabitsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    public abstract EditHabitFragment contributeEditHabitFragment();

    @ContributesAndroidInjector
    public abstract HabitsFragment contributeHabitsFragment();

}
