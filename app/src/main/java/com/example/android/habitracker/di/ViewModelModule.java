package com.example.android.habitracker.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.android.habitracker.ui.habit.HabitViewModel;
import com.example.android.habitracker.ui.habit.HabitsViewModel;
import com.example.android.habitracker.viewmodel.HabiTrackerViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HabitsViewModel.class)
    public abstract ViewModel bindHabitsViewModel(HabitsViewModel habitsViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(HabitViewModel.class)
    public abstract ViewModel bindHabitViewModel(HabitViewModel habitViewModel);

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(HabiTrackerViewModelFactory factory);
}
